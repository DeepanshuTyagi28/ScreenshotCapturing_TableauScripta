package scripta.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InitialSetup {
	public static WebDriver wb;

	public static WebDriver setDriver(String browserName) {
		// String bName = browserName.toLowerCase();
		switch (browserName) {
		case "firefox": {
			WebDriverManager.firefoxdriver().setup();
			wb = new FirefoxDriver();
			break;
		}
		case "chrome": {
			WebDriverManager.chromedriver().setup();
			wb = new ChromeDriver();
			break;
		}
		case "ie": {
			WebDriverManager.iedriver().setup();
			wb = new InternetExplorerDriver();
			break;
		}
		case "headless": {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("window-size=1200x600");
			wb = new FirefoxDriver(options);
			break;
		}
		case "headless1": {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("window-size=1200x600");
			wb = new ChromeDriver(options);
			break;
		}

		}
		Reporter.log(browserName + " launched", CommonMethods.enableLog);

		wb.manage().window().maximize();
		wb.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return wb;
	}

	public static void launchBrowser() throws InterruptedException {
		wb.manage().window().maximize();
		Thread.sleep(5000);
	}
	

	public static void HardWait(long Second) throws InterruptedException {
		Thread.sleep(Second);
	}
	
	public static void launchUrl(String url) throws InterruptedException {
		wb.manage().window().maximize();
		Thread.sleep(5000);
		wb.get(url);
		Thread.sleep(2000);
	}
}
