package com.qa.raybiztech.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.raybiztech.utils.Constants;
import com.qa.raybiztech.utils.ExcelUtils;

public class ContactPageTest extends BaseTest{
	
	@BeforeClass
	public void contactSetup(){
		contactPage = home.goToContactPage();
	}
	
	@DataProvider
	public Object[][] getContactRegisterData() {
		Object contData[][] = ExcelUtils.getTestData(Constants.Contacts_Sheet_Name);
		return contData;
	}
	
	public String getRandomNumber() {
		Random random = new Random();
		String email = "testAutomation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	@Test(dataProvider = "getContactRegisterData")
	public void contactRegisterReset(String name,String phonnumber,String country,String message,String button) {
		
		Assert.assertTrue(contactPage.getInTouchRegisterResetFunction(name,getRandomNumber(),phonnumber,country,message,button));
	}
	

}
