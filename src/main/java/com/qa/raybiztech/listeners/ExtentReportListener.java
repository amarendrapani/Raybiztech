package com.qa.raybiztech.listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.UploadSessionFinishErrorException;
import com.qa.raybiztech.factory.DriverFactory;
import com.qa.raybiztech.utils.Constants;

public class ExtentReportListener extends DriverFactory implements ITestListener {
    private static final String OUTPUT_FOLDER = "./build/";
    private static final String FILE_NAME = "TestExecutionReport.html";
    //private static final String DROPBOX_ACCESS_TOKEN = "YOUR_DROPBOX_ACCESS_TOKEN";

    private static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private static ExtentReports extentReports;

    private static ExtentReports init() {
        Path path = Paths.get(OUTPUT_FOLDER);
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        reporter.config().setReportName("Automation Test Results");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System", "Windows");
        extentReports.setSystemInfo("Author", "Amarendra Pani");
        extentReports.setSystemInfo("Build#", "1.1");
        extentReports.setSystemInfo("Team", "raybiztech");
        extentReports.setSystemInfo("ENV NAME", System.getProperty("env"));
        extentReports.setSystemInfo("Base Url", "https://raybiztech.com");

        return extentReports;
    }

    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Test Suite is ending!"));
        extent.flush();
        uploadReportToDropbox();
        test.remove();
    }

    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println(methodName + " started!");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());

        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        String url = super.getDriver().getCurrentUrl();
        test.get().info("Current URL: " + url);
        test.get().pass("Test passed");
        test.get().pass(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        test.get().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().skip(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    private void uploadReportToDropbox() {
        try (InputStream in = new FileInputStream(OUTPUT_FOLDER + FILE_NAME)) {
            DbxRequestConfig config = DbxRequestConfig.newBuilder("raybiztech").build();
            DbxClientV2 client = new DbxClientV2(config, Constants.DROPBOX_ACCESS_TOKEN);

            String destinationPath = "/TestExecutionReport.html";

            FileMetadata metadata = client.files().uploadBuilder(destinationPath)
                    .uploadAndFinish(in);

            String sharedLink = null;
			try {
				sharedLink = client.sharing().createSharedLinkWithSettings(destinationPath)
				        .getUrl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            System.out.println("Report uploaded to Dropbox successfully!");
            System.out.println("Shared link: " + sharedLink);
        } catch (IOException | UploadErrorException | UploadSessionFinishErrorException e) {
            e.printStackTrace();
        } catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}