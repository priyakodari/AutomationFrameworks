package DriverFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelFileUtil;
import CommonFunLibrary.FunctionLibrary1;
import Constant.PBConstant1;


public class KeyWordDriverScript1 extends PBConstant1{
	String inputpath="D:\\Automation\\Automation_Frameworks\\TestInput\\Controller.xlsx";
	String outputpath="D:\\Automation\\Automation_Frameworks\\TestOutput\\KeywordResults.xlsx";
	String TCSheet="TestCases";
	String TSSheet="TestSteps";
	ExtentReports report;
	ExtentTest test;
	@Test
	public void startTest()throws Throwable
	{
		//generate html report
		report= new ExtentReports("./ExtentReport/"+FunctionLibrary1.generateDate()+"  "+"Keyword.html");
		boolean res =false;
		String tcres="";
		//accessing excel methods
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in TCSheet
		int TCCount =xl.rowCount(TCSheet);
		//count no of rows in TSSheet
		int TSCount =xl.rowCount(TSSheet);
		Reporter.log("No of rows in TCSheet::"+TCCount+"  "+"No of rows in TSSheet::"+TCCount,true);
		//iterate all rows in TCSheet
		for(int i=1; i<=TCCount; i++)
		{
			//start test case
			test= report.startTest("KeywordFrmework");
			//read execute cell
			String Execute =xl.getCellData(TCSheet, i, 2);
			if(Execute.equalsIgnoreCase("Y"))
			{
			//read tcid cell
				String tcid =xl.getCellData(TCSheet, i, 0);
				//iterate all rows in TSSheet
				for(int j=1; j<=TSCount; j++)
				{
					//read description
					String Desription=xl.getCellData(TSSheet, j, 2);
					//read tsid cell
					String tsid = xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						//get keyword cell
						String keyword = xl.getCellData(TSSheet, j, 3);
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
							res=FunctionLibrary1.verifyLogin("Admin", "Admin");
							test.log(LogStatus.INFO, Desription);
						}
						else if(keyword.equalsIgnoreCase("NewBranchCreation"))
						{
							FunctionLibrary1.clickBranches();
							res=FunctionLibrary1.verifyNewBranch("Kadiri5", "Anantapur90", "Madanapalli45", "Tanakal", "Kadiri", "45678", "UK", "England", "LONDON");
							test.log(LogStatus.INFO, Desription);
						}
						else if(keyword.equalsIgnoreCase("UpdateBranch"))
						{
							FunctionLibrary1.clickBranches();
							res=FunctionLibrary1.verifyBranchUpdate("Srnagar", "Kukatpalli", "98765");
							test.log(LogStatus.INFO, Desription);
						}
						else if(keyword.equalsIgnoreCase("AdminLogout"))
						{
							res=FunctionLibrary1.verifyLogout();
							test.log(LogStatus.INFO, Desription);
						}
						String tsres="";
						if(res)
						{
							//if res true write as pass into results cell
							tsres="Pass";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							test.log(LogStatus.PASS, Desription);
						}
						else
						{
							//if res false write as fail into results cell
							tsres="fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							test.log(LogStatus.FAIL, Desription);
						}
						tcres = tsres;
					}
					report.endTest(test);
					report.flush();
				}
				//write as tcres into TCSheet
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			}
			else
			{
				//write as blocked into status cell in TCSheet
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
			
		}
	}
	}
