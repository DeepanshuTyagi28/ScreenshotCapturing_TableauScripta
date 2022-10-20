package scripta.common;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods extends BaseTestSuite {
	public static final String filepath = "./test.xlsx";
	public static String SelectedOption;
	public static final boolean enableLog = true;

	public static void clearAndSendData(String element_id, String value) throws InterruptedException {
		Thread.sleep(1000);
		browser.findElement(By.id(element_id)).clear();
		Thread.sleep(2000);
		browser.findElement(By.id(element_id)).sendKeys(value);
		Thread.sleep(2000);
	}

	public static void clearAndSendDataXpath(String xpath, String value) throws InterruptedException {
		browser.findElement(By.xpath(xpath)).clear();
		Thread.sleep(2000);
		browser.findElement(By.xpath(xpath)).sendKeys(value);
		Thread.sleep(2000);
	}

	public static void SwitchToActiveElements(String value) throws InterruptedException {
		browser.switchTo().activeElement().sendKeys(Keys.chord(value, Keys.ENTER));
		Thread.sleep(2000);
		browser.switchTo().activeElement().click();
		browser.switchTo().activeElement().sendKeys(Keys.TAB);
		browser.switchTo().activeElement().sendKeys(Keys.TAB);
		Thread.sleep(2000);
	}

	public static Sheet Getsheets(String sheets1) throws FileNotFoundException, IOException {

		String sh = sheets1;
		File abc = new File(filepath);
		@SuppressWarnings("resource")
		Workbook wrkbook = new XSSFWorkbook(new FileInputStream(abc));
		Sheet sheet = wrkbook.getSheet(sh);
		return sheet;
	}

	public static void scrollView(WebElement Element) {
		JavascriptExecutor je = (JavascriptExecutor) browser;
		je.executeScript("arguments[0].scrollIntoView();", Element);
	}

	public static void scrollView(int xcoord, int ycoord) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) browser;
		js.executeScript("javascript:window.scrollBy(" + xcoord + "," + ycoord + ")");
		Thread.sleep(2000);
	}

	public static void commonSelectContainer(String id, String value) throws InterruptedException {
		browser.findElement(
				By.xpath("//span[@data-select2-id='" + id + "']//li[@class='select2-search select2-search--inline']"))
				.click();
		Thread.sleep(2000);
		browser.switchTo().activeElement().sendKeys(Keys.chord(value, Keys.ENTER));
		Thread.sleep(2000);

	}

	public static void webElement(String id, String value) throws InterruptedException {
		WebElement elem = browser.findElement(By.id(id));
		Select slct = new Select(elem);
		slct.selectByVisibleText(value);
		Thread.sleep(2000);

	}

	public static void webElementByIndex(String id, int index) throws InterruptedException {
		WebElement elem = browser.findElement(By.id(id));
		Select slct = new Select(elem);
		slct.selectByIndex(index);
		SelectedOption = slct.getFirstSelectedOption().getText();
		Thread.sleep(2000);

	}

	public static void commonToggle(String id, String togglePath) throws InterruptedException {
		WebElement elem2 = browser.findElement(By.id(id));
		browser.findElement(By.xpath(togglePath)).click();
		Thread.sleep(2000);
		if (elem2.isEnabled()) {
		} else {
			browser.findElement(By.xpath(togglePath)).click();
			Thread.sleep(2000);
		}
	}

	public static void Iwait() throws InterruptedException {
		browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public static void Ewait(String xpath) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(browser, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public static void CommonForValidations(String id, String value, String value1, String xpath, String SaveXpath)
			throws InterruptedException {
		CommonMethods.clearAndSendData(id, value);
		Thread.sleep(1000);
		browser.findElement(By.xpath(SaveXpath)).click();
		Thread.sleep(1000);
		assertTrue(browser.findElement(By.xpath(xpath)).isDisplayed());
		Thread.sleep(2000);
		CommonMethods.clearAndSendData(id, value1);

	}

}
