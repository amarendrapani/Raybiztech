package com.qa.raybiztech.utils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;



public class PdfUtils {
	
	public static Map<String, String> extractPdfData(String filePath) {
        Map<String, String> pdfData = new HashMap<>();

        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            PDDocument pdfDocument = PDDocument.load(fis);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String pdfText = pdfTextStripper.getText(pdfDocument);

            // Define regex patterns for key-value pairs
           // Pattern pattern = Pattern.compile("(\\w+):\\s*((?:.*?)(?:\\n(?:.*?))*)(?=(\\w+:|$))");
            Pattern pattern = Pattern.compile("([^:\\n]+):\\s*([^:\\n]*(?:\\n\\s*[^:\\n]+)*)");
            Matcher matcher = pattern.matcher(pdfText);

            // Extract key-value pairs and populate the map
            while (matcher.find()) {
                String key = matcher.group(1).trim();
                String value = matcher.group(2).replaceAll("\\s+", " ").trim(); // Replace multiple spaces with a single space
                pdfData.put(key, value);
            }

            // Close the PDF document
            pdfDocument.close();

           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pdfData;
    }
	
	
	//---------------------------------------
	
	public static Map<String, String> extractKeyValues(String text) {
        Map<String, String> keyValues = new HashMap<>();

        // Split the text into lines
        String[] lines = text.split("\\r?\\n");

        // Define regex pattern for key-value pairs
        Pattern pattern = Pattern.compile("^(.*?):\\s*(.*)$");

        // Extract key-value pairs and populate the map
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                String key = matcher.group(1).trim();
                String value = matcher.group(2).trim();
                keyValues.put(key, value);
            }
        }

        return keyValues;
    }
	
	
	//\C:\Users\mprag\Downloads\4645251_1100742_0656230000_20230404.pdf.87
	//String filePath = "C:\\Users\\mprag\\Downloads\\4645251_1100742_0656230000_20230404.pdf.87\\4645251_1100742_0656230000_20230404.pdf";
	@Test
	public static String readPDFFile(String filePath) throws Exception {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        PDDocument pdfDocument = null;
        String docText;

        try {
            pdfDocument = PDDocument.load(fis);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            docText = pdfTextStripper.getText(pdfDocument);
        } finally {
            if (pdfDocument != null) {
                pdfDocument.close();
            }
            fis.close();
        }

        return docText;
    }
	@Test
	public void testPdfDataExtraction() throws Exception {
        // Replace the path with the actual path to your PDF file
        String pdfFilePath = "C:\\Users\\mprag\\Downloads\\4645251_1100742_0656230000_20230404.pdf.87\\4645251_1100742_0656230000_20230404.pdf";

        // Extract data from PDF
         Map<String, String> extractedData = extractPdfData(pdfFilePath);

        // Verify that the extracted data is not null
        Assert.assertNotNull(extractedData);

        // Add your validation checks here
        // Example: Assert.assertEquals(extractedData.get("ACCOUNTCODE"), "0324961000");
        // ...

        // Print or use the extracted data (optional)
        extractedData.forEach((key, value) -> System.out.println(key + ": " + value));
     //   for (Map.Entry<String, String> entry : result.entrySet()) {
          //  System.out.println(entry.getKey() + ": " + entry.getValue());
   

    }
	
	//@Test
	public void ReadPDFFile() throws Exception {
		// if the file is available in local machine
		File file = new File("C:\\Users\\mprag\\Downloads\\4645251_1100742_0656230000_20230404.pdf.87\\4645251_1100742_0656230000_20230404.pdf");
		FileInputStream fis = new FileInputStream(file);		
		PDDocument pdfDocument = PDDocument.load(fis);

		System.out.println("Number of Pages: " +pdfDocument.getPages().getCount());

		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		//pdfTextStripper.setStartPage(1); // comment this line if you want to read the entire document
		//pdfTextStripper.setEndPage(4); // comment this line if you want to read the entire document
		String docText = pdfTextStripper.getText(pdfDocument);

		System.out.println(docText);

		//Assert.assertTrue(docText.contains("Maecenas"));

		pdfDocument.close();
		fis.close();


}
}
