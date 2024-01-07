package driverFactory;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.Functionlibrary;
import utilities.ExcelFileUtil;

public class DriverScript {
public static WebDriver driver;
String inputpath="./FileInput/Controller_lyst2620.xlsx";
String outputpath="./FileOutput/HybridResults.xlsx";
String TestCases="MasterTestCases";
ExtentReports report;
ExtentTest logger;
public void startTest() throws Throwable
{
	String Module_status="";
	//create object for excelfileutil class
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//iterate all rows in TestCase sheet
	for (int i=1;i<=xl.rowCount(TestCases);i++)
	{
		
		//read execution cell
		String Excution_Status=xl.getCellData(TestCases, i, 2);
		if(Excution_Status.equalsIgnoreCase("y"))
		{
			//store all sheets into TcModule
			String TCModule=xl.getCellData(TestCases, i, 1);
			//define path to generate html report
			report=new ExtentReports("./target/Reports/"+TCModule+Functionlibrary.generateDate()+"");
			logger=report.startTest(TCModule);
			//iterate all roes in TCModule
			for (int j=1;j<=xl.rowCount(TCModule);j++) 
			{
				//read cell from TCModule sheet
				String Description=xl.getCellData(TCModule, i, 0);
				String Object_Type=xl.getCellData(TCModule, j, 1);
				String Locator_Type=xl.getCellData(TCModule, j, 2);
				String Locator_value=xl.getCellData(TCModule, j, 3);
				String Test_Data=xl.getCellData(TCModule, j, 4);
				try {
					if(Object_Type.equalsIgnoreCase("startBrowser")) 
					{
						driver=Functionlibrary.startBrowser();
						logger.log(LogStatus.INFO, Description);
						
					}
					if(Object_Type.equalsIgnoreCase("openUrl"))
					{
						Functionlibrary.openUrl(driver);
						logger.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("waitForElement"))
					{
						Functionlibrary.waitForElement(driver, Locator_Type, Locator_value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("typeAction"))
					{
						Functionlibrary.typeAction(driver, Locator_Type, Locator_value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("clickAction"))
					{
						Functionlibrary.clickAction(driver, Locator_Type, Locator_value);
						logger.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("waitForElement"))
					{
						Functionlibrary.waitForElement(driver, Locator_Type, Locator_value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("validateTitle"))
					{
						Functionlibrary.validateTitle(driver, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("closeBrowser"))
					{
						Functionlibrary.closeBrowser(driver);
						logger.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("moseClick"))
					{
						Functionlibrary.moseClick(driver);
						logger.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("categoryTable"))
					{
						Functionlibrary.categoryTable(driver, Test_Data);
						logger.log(LogStatus.INFO, Description);
						
					}
					if(Object_Type.equalsIgnoreCase("dropDownAction"))
					{
				    Functionlibrary.dropDownAction(driver, Locator_Type, Locator_value, Test_Data);
				    logger.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("captureStock"))
					{
						Functionlibrary.captureStock(driver, Locator_Type, Locator_value);
						logger.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("stockTable"))
					{
						Functionlibrary.stockTable(driver);
						logger.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("captureSupplier"))
					{
						Functionlibrary.captureSupplier(driver, Locator_Type, Locator_value);
						logger.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("suppliertable"))
					{
						Functionlibrary.suppliertable(driver);
						logger.log(LogStatus.INFO, Description);
						
					}
					
					//write as pass into status cell in TCModule
					xl.setCellData(TCModule, j, 5, "Pass", outputpath);
					logger.log(LogStatus.PASS, Description);
					Module_status="True";
					}catch(Exception e)
				    {
					System.out.println(e.getMessage());
					//write as fail into status cell in TCModule
					xl.setCellData(TCModule, j, 5, "Fail", outputpath);
					logger.log(LogStatus.FAIL, Description);
					Module_status="Fail";
					
				}
				if(Module_status.equalsIgnoreCase("True"))
				{
					//write as pass into TestCase sheet
					xl.setCellData(TestCases, i, 3, "Pass", outputpath);
					
				}
			if(Module_status.equalsIgnoreCase("Fail"))
			{
				//write as fail into TestCase sheet
				xl.setCellData(TestCases, i, 4, "Fail", outputpath);
			}
			report.endTest(logger);
			report.flush();
					
				}
				
			}
			
		
		else
		{
			//write as blocked into status cell in TestCasesheet  for Flag N
			xl.setCellData(TestCases, i, 3, "Blocked", outputpath);
		}
		
	}
		
	
}

	
	
	
	
	
	
	
	
	
}