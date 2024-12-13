package com.qa.raybiztech.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.testng.annotations.Test;


public class CsvUtils {
	
	public static List<Map<String, String>> readAllCsvDatanew(String filePath) {
	    List<Map<String, String>> allCsvData = new ArrayList<>();

	    // Use the default CSV format with the first record as the header
	    CSVFormat format = CSVFormat.DEFAULT.builder()
	            .setIgnoreHeaderCase(true)
	            .setTrim(true)
	            .build()
	            .withFirstRecordAsHeader();

	    try (FileReader fileReader = new FileReader(filePath);
	         CSVParser parser = new CSVParser(fileReader, format)) {

	        for (CSVRecord record : parser) {
	            Map<String, String> recordData = new HashMap<>();
	            record.toMap().forEach(recordData::put);
	            allCsvData.add(recordData);
	        }

	    } catch (IOException e) {
	        // Log the error or throw a custom exception with a meaningful message
	        e.printStackTrace();
	    }

	    return allCsvData;
	}
	
	public static List<Map<String, String>> readAllCsvData(String filePath) {
        List<Map<String, String>> allCsvData = new ArrayList<>();
        
        // Use the default CSV format with the first record as the header
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build()
                .withFirstRecordAsHeader();

        try (FileReader fileReader = new FileReader(filePath);
             CSVParser parser = new CSVParser(fileReader, format)) {
            
            for (CSVRecord record : parser) {
                Map<String, String> recordData = new HashMap<>();
                record.toMap().forEach(recordData::put);
                allCsvData.add(recordData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return allCsvData;
    }
	
	
	@Test
    public void testPrintCSVData() {
        String csvFilePath = "C:\\Users\\mprag\\Downloads\\4645251_1100742_0656230000_20230404.pdf.87\\4607444_1100742_1236220000_20230321.87.csv" ; 

        // Call the method to read all data from the CSV
        List<Map<String, String>> allData = CsvUtils.readAllCsvDatanew(csvFilePath);

        // Print the data to the console
        for (Map<String, String> rowData : allData) {
            System.out.println("----- New Row -----");
            for (Map.Entry<String, String> entry : rowData.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

}
