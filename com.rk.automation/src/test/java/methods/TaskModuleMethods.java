package methods;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import driver.DriverScript;
import locators.ObjectLocators;

public class TaskModuleMethods extends DriverScript implements ObjectLocators{
	/******************************************
	 * Method Name		: createCustomer()
	 * 
	 *******************************************/
	public String createCustomer(WebDriver oDriver, Map<String, String> data, ExtentTest test)
	{
		String strStatus = null;
		String userName = null;
		try {
			strStatus+= appInd.clickObject(oDriver, objTaskMenu, test);
			strStatus+= appInd.clickObject(oDriver, objAddNew, test);
			Thread.sleep(2000);
			
			if(appInd.verifyElementExist(oDriver, objTextContain, test)) {
				reports.writeResult(oDriver, "Pass", "The Add User page has opened successful", test);
				strStatus+= appInd.setObject(oDriver, objNewCustomer, data.get("FN"), test);
				strStatus+= appInd.setObject(oDriver, objLastNameEdit, data.get("LN"), test);
				strStatus+= appInd.setObject(oDriver, objEmailEdit, data.get("Email"), test);
				strStatus+= appInd.setObject(oDriver, objUser_UNEdit, data.get("User_UN"), test);
				strStatus+= appInd.setObject(oDriver, objUser_PwdEdit, data.get("User_PWD"), test);
				strStatus+= appInd.setObject(oDriver, objUser_RetypePWdEdit, data.get("User_RetypePWD"), test);
				strStatus+= appInd.clickObject(oDriver, objCreateUserBtn, test);
				Thread.sleep(2000);
				
				userName = data.get("LN")+", "+data.get("FN");
				
				strStatus+= appInd.verifyElementExist(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+userName+"'"+"]"), test);
				
				if(strStatus.contains("false")){
					reports.writeResult(oDriver, "Fail", "Failed to create the user in the actiTime", test);
					return null;
					
				}else {
					reports.writeResult(oDriver, "screenshot", "User created Successful", test);
					reports.writeResult(oDriver, "Pass", "The user is created successful", test);
					return userName;
				}
			}else {
				reports.writeResult(oDriver, "Fail", "Failed to open the Add User page.", test);
				return null;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception in createUser() method. "+ e, test);
			return null;
		}
	}
	


}
