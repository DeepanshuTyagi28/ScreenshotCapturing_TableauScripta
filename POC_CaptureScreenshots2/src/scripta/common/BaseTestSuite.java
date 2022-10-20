package scripta.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestSuite {
	protected static WebDriver browser;
	protected static final String LOGIN_SUBMIT = "//*[@id=\"root\"]/div/div/div/div[2]/div/div[5]/div/div/button/span[1]";
	protected static final String SIGNUP_LINK = "//a[@href='/signup']";
	protected static final String SIGNUP_BUTTON = "//button[text()='SIGN UP']";

	@BeforeSuite(alwaysRun = true, description = "Initialize & launch web driver")
	@Parameters({ "browser", "tableauUrl" })
	public static void initialize(String browserName, String url) throws InterruptedException {
		browserName = browserName.toLowerCase();
		switch (browserName) {
		case "firefox": {
			WebDriverManager.firefoxdriver().setup();
			browser = new FirefoxDriver();
			break;
		}
		case "chrome": {
			WebDriverManager.chromedriver().setup();
			browser = new ChromeDriver();
			break;
		}
		
		case "edge": {
			WebDriverManager.edgedriver().setup();
			browser = new EdgeDriver();
			break;
		}
		
		case "ie": {
			WebDriverManager.iedriver().setup();
			browser = new InternetExplorerDriver();
			break;
		}
		case "headless": {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("window-size=1200x600");
			browser = new FirefoxDriver(options);
			break;
		}
		case "headless1": {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("window-size=1200x600");
			browser = new ChromeDriver(options);
			break;
		}
		}

		Reporter.log(browserName + " launched", CommonMethods.enableLog);
		browser.manage().window().maximize();
		browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		launchUrl(url);
		Thread.sleep(5000);
	}

	public static void login(String url, String email, String password) throws InterruptedException {
		browser.get(url);
		browser.findElement(By.id("email")).sendKeys(email);
		browser.findElement(By.id("password")).sendKeys(password);
		browser.findElement(By.xpath(LOGIN_SUBMIT)).click();
		Thread.sleep(3000);
	}

	protected void hardWait(long Second) throws InterruptedException {
		Thread.sleep(Second);
	}

	protected static void launchUrl(String url) throws InterruptedException {
		browser.manage().window().maximize();
		Thread.sleep(5000);
		browser.get(url);
		Thread.sleep(2000);
	}

	protected static void launchTableauUrl(String tableauUrl) throws InterruptedException {
		browser.manage().window().maximize();
		Thread.sleep(5000);
		browser.get(tableauUrl);
		Thread.sleep(2000);
	}

	@AfterSuite(description = "Tear down web driver")
	public void tearDown() throws InterruptedException {
		browser.quit();
	}
}