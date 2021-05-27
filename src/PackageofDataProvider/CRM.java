package PackageofDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CRM {

	WebDriver driver;
	@Test
	public void VerifyTest()
	{
	System.setProperty("webdriver.chrome.driver", "d:/Automation/chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://www.freecrm.com/");
	driver.manage().window().maximize();
	//create object for webdriverwait class
	WebDriverWait wait = new WebDriverWait(driver, 300);
	//wait for login btn to click
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//header/div[1]/nav[1]/div[2]/div[1]/a[1]")));
	driver.findElement(By.xpath("//header/div[1]/nav[1]/div[2]/div[1]/a[1]")).click();
	//wait until textbox is visible
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
	driver.findElement(By.name("email")).sendKeys("pranga2010@gmail.com");
//wait for password textbox
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
	driver.findElement(By.name("password")).sendKeys("test543421");
	//wait unti login btn clickable
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[@id='ui']/div[1]/div[1]/form[1]/div[1]/div[3]")));
	driver.findElement(By.xpath("//body/div[@id='ui']/div[1]/div[1]/form[1]/div[1]/div[3]")).click();
	
}
}