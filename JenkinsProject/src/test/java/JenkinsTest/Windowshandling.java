package JenkinsTest;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Windowshandling 
{
	WebDriver driver;
	WebElement userName;
	WebElement password;
	WebElement selectCompany;
	
	ExtentReports report = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\Tania\\Desktop\\souvik\\Extent_data.html");
	
	ExtentTest ts;

	@Test (priority =1)
	public void url()
	{
		report.attachReporter(spark);
		ts = report.createTest("WindowsHandling");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Tania\\Desktop\\New folder\\chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		ts= ts.info("Chrome browser opened");
		driver.get("http://frontaccounting.squadinfotech.in/");
		ts= ts.info("FA url opened");
	}
	
	@Test (priority =2)
	public void login()
	{
		userName = driver.findElement(By.name("user_name_entry_field"));
		userName.sendKeys("frontuser1");
		ts= ts.info("Username Entered");
		
		password = driver.findElement(By.name("password"));
		password.sendKeys("frontuser1");
		ts= ts.info("Password Entered");
		
		selectCompany = driver.findElement(By.name("company_login_name"));
		Select company = new Select(selectCompany);
		company.selectByIndex(1);
		ts= ts.info("Company selected");
		
		driver.findElement(By.name("SubmitUser")).click();
		ts= ts.info("Login Has Done Sucessfully");
	}
	
	@Test (priority =3)
	public void newPage() throws InterruptedException
	{
		driver.findElement(By.linkText("Help")).click();
		ts= ts.info("Help Linked Clicked");
		
		String mainWin = driver.getWindowHandle();
		System.out.println(mainWin);
		
		Set<String> chWin = driver.getWindowHandles();
		Thread.sleep(3000);
		for(String window:chWin)
		{
			System.out.println(window);
			
			if(!mainWin.equals(window))
			{
				driver.switchTo().window(window);
				driver.manage().window().maximize();
			}
		}
		
//		driver.findElement(By.id("reload-button")).click();
//		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div/div[2]/button[3]")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("General")).click();
		ts= ts.info("General Linked Clicked");
		
		report.flush();
	}
	
	

}
