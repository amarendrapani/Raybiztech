package com.qa.raybiztech.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.raybiztech.utils.CsvUtils;
import com.qa.raybiztech.utils.PdfUtils;

import java.util.List;
import java.util.Map;

public class DataValidationTest {
	
	//@Test
    public void validateCsvDataAgainstPdf() {
        // Paths to the PDF and CSV files
        String pdfFilePath = "C:\\Users\\mprag\\Downloads\\4645251_1100742_0656230000_20230404.pdf.87\\4607444_1100742_1236220000_20230321.pdf";
        String csvFilePath = "C:\\Users\\mprag\\Downloads\\4645251_1100742_0656230000_20230404.pdf.87\\4607444_1100742_1236220000_20230321.87.csv" ; // Replace with your CSV file path
      
        // Extract data from PDF into a Map
       // Map<String, String> pdfData = PdfUtils.readPDFFile("\\C:\\Users\\mprag\\Downloads\\4645251_1100742_0656230000_20230404.pdf.87\\4645251_1100742_0656230000_20230404.pdf"); // Implement this method
         //String pdfData = new PdfUtils().readPDFFile(pdfFilePath);
        // Read all data from CSV into a List of Maps
        Map<String, String> pdfData = PdfUtils.extractPdfData(pdfFilePath);
      // String pdfData = PdfUtils.readPDFFile(pdfFilePath);
        List<Map<String, String>> csvDataList = CsvUtils.readAllCsvDatanew(csvFilePath); // Implement this method

        // Validate that each field in the CSV matches the PDF data
        for (Map<String, String> csvRecord : csvDataList) {
            for (String field : csvRecord.keySet()) {
                String csvValue = csvRecord.get(field).trim(); // Trim the value for accurate comparison

                // Check if the field exists in the pdfData map
                if (pdfData.containsKey(field)) {
                    String pdfValue = pdfData.get(field).trim(); // Assume pdfData also needs trimming

                    // Print the field being compared and the values from both sources
                    System.out.printf("Field '%s' - CSV: '%s', PDF: '%s'%n", field, csvValue, pdfValue);

                    // Assert that the CSV value matches the PDF value
                    Assert.assertEquals(csvValue, pdfValue, String.format("Value for field '%s' does not match between CSV and PDF.", field));
                } else {
                    // Handle the case where the field is not present in the pdfData map
                    System.out.printf("Field '%s' does not exist in the PDF data.%n", field);
                    // You can choose to skip or handle it according to your needs
                }
            }
        }
    }
    @Test
	public void validateCsvDataAgainstPdfnew() throws Exception {
	    // Paths to the PDF and CSV files
	    String pdfFilePath = "C:\\Users\\mprag\\Downloads\\4645251_1100742_0656230000_20230404.pdf.87\\4645251_1100742_0656230000_20230404.pdf";
	    String csvFilePath = "C:\\Users\\mprag\\Downloads\\4645251_1100742_0656230000_20230404.pdf.87\\4645251_1100742_0656230000_20230404.87.csv"; // Replace with your CSV file path

	    // Extract data from PDF into a formatted string
	    String pdfDataAsString = PdfUtils.readPDFFile(pdfFilePath);

	    // Read all data from CSV into a List of Maps
	    List<Map<String, String>> csvDataList = CsvUtils.readAllCsvDatanew(csvFilePath); // Implement this method

	    // Validate that each field in the CSV matches the PDF data
	    for (Map<String, String> csvRecord : csvDataList) {
	        for (String field : csvRecord.keySet()) {
	            String csvValue = csvRecord.get(field).trim();

	            // Check if the CSV key-value pair exists in the PDF data string
	            Assert.assertTrue(pdfDataAsString.contains(field + ":" + csvValue),
	                    String.format("Field '%s' with value '%s' does not match in the PDF data.", field, csvValue));

	            // Print the field being compared and the values from both sources
	            System.out.printf("Field '%s' - CSV: '%s', PDF: '%s'%n", field, csvValue, csvValue);
	        }
	    }
	}
	
}
