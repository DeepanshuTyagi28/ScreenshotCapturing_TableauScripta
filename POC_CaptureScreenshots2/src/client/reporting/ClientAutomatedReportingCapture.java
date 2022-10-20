package client.reporting;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Sceenshot.ImageResize;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import scripta.common.BaseTestSuite;
import scripta.common.CommonMethods;

public class ClientAutomatedReportingCapture extends BaseTestSuite {
	@BeforeSuite
	@Parameters("browser")
	public void beforeSuite(String browser) {
		Reporter.log(("ClientAutomatedReportingCapture: before suite " + browser), CommonMethods.enableLog);
	}

	public static final String MCGRIFF = "/html/body/div[1]/div/div[1]/div[2]/div/div[2]/div/div[2]/div[3]/span/span";
	public static final String EXPLORE = "/html/body/div[1]/div/div[1]/div/div/div/div[1]/nav/ul/li[9]/div/a";
	public static final String MCGRIFF_FOLDER = "/html/body/div[1]/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/div[2]/div[1]/div/div[1]/section/div[3]/div/div/div/div[1]/div/div/a";
	public static final String SAVINGS_DASHBOARD = "/html/body/div[1]/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/div[2]/div[1]/div/div[1]/section/div[2]/div/div/div/div[1]/div/div/a";
	public static final String LANDING_PAGE = "/html/body/div[1]/div/div[1]/div/div/div/div[2]/div[2]/div/div[3]/div/div/div[2]/div[1]/div/div[1]/section/div[1]/div/div/div/div[1]/div/div/a";
	public static final String PageSelectionarrow = "/html/body/div[2]/div[2]/div[2]/div[1]/div/div[1]/div/div/span[1]/span/span/button";
	public static final String ScriptaImage = "/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[17]/div/div/div/div/img";
	public static final String ZoomoutArrow = "/html/body/div[2]/div[2]/div[2]/div[2]/div/div[1]/div/div/span[1]/span/span/button";

	// Login with valid credentials
	@Test(groups = { "smokeTest","regressionTest" }, priority = 1, description = "Capture SS from Client Reporting on tableau server")
	@Parameters({ "tableauUrl" })
	
	public static void capture(String tableauUrl) throws InterruptedException, IOException, AWTException {
		Thread.sleep(3000);
		browser.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys("dtyagi@scriptainsights.com");
		Thread.sleep(2000);
		browser.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span")).click();
		Thread.sleep(2000);
		browser.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("Winter#282828");
		Thread.sleep(2000);
		browser.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/span")).click();
		Thread.sleep(3000);
		browser.findElement(By.xpath(MCGRIFF)).click();
		Thread.sleep(3000);
		browser.findElement(By.xpath(EXPLORE)).click();;
		Thread.sleep(6000);
		browser.findElement(By.xpath(MCGRIFF_FOLDER)).click();
		Thread.sleep(4000);
		browser.findElement(By.xpath(SAVINGS_DASHBOARD)).click();
		Thread.sleep(5000);
		browser.findElement(By.xpath(LANDING_PAGE)).click();
		Thread.sleep(9000);	
		
		
		System.out.println("Execution is Paused to Fill the Start Date,End Date & Client Selection(BY Default ALL Client Selected) ");
		Thread.sleep(120000);	//2 Minutes
		System.out.println("Execution is going to start in Next 2 Seconds so  Return on the Window handled by Chrome");
		Thread.sleep(2000);	// WIll wait again for 2 Seconds 
		
		
		browser.switchTo().frame(0);

		browser.findElement(By.xpath(PageSelectionarrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[3]/div")).click();		
		Thread.sleep(8000);
		

		WebDriverWait Executive_Image_Load = new WebDriverWait(browser,50);
		Executive_Image_Load.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[1]/div/div/div/div[1]/div/span/div/span")));

		
		// Minimizing the brResolution of Screen to capture Graph
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);		
		for(int i=0 ; i<4 ; i++) 
		{		
		robot.keyPress(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_MINUS);
		}
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(8000);
		
		
		String Filename ="Executive Summarys.png";
		WebDriverWait wait1 = new WebDriverWait(browser,30);
		wait1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[95]/div/div/div/div/img")));
		Screenshot screenshot1 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot1.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename ));	
		ImageResize.Crop(Filename);
		Thread.sleep(3000);
		

		Filename ="COST VS CLAIM COMPARISON.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[4]/div")).click();		
		Thread.sleep(8000);
		WebDriverWait wait2 = new WebDriverWait(browser,30);
		wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ScriptaImage)));
		Screenshot screenshot2 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot2.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		Filename ="TOTAL LIVES-UTILIZERS Vs NON-UTILIZERS.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[5]/div")).click();		
		Thread.sleep(8000);
		WebDriverWait wait3 = new WebDriverWait(browser,30);
		wait3.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ScriptaImage)));
		Screenshot screenshot3 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot3.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);

		Filename ="SPEND BY QUARTERS.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[6]/div")).click();		
		Thread.sleep(8000);
		WebDriverWait wait4 = new WebDriverWait(browser,30);
		wait4.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ScriptaImage)));
		Screenshot screenshot4 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot4.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="PMPM SPENT PER QUATER.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[7]/div")).click();		
		Thread.sleep(8000);
		WebDriverWait wait5 = new WebDriverWait(browser,30);
		wait5.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[15]/div/div/div/div/img")));
		Screenshot screenshot5 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot5.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		System.out.println("5 Screenshot Taken , Please check the Screenshot folder of the Directory");
		
		
		Filename ="PLAN AND MEMBER SAVINGS AND SWITCHES.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[8]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait6 = new WebDriverWait(browser,30);
		wait6.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[15]/div/div/div/div/img")));
		Screenshot screenshot6 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot6.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="RECURRENT VS FIRST SWITCHES BY MONTH.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[9]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait7 = new WebDriverWait(browser,30);
		wait7.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[23]/div/div/div/div/img")));
		Screenshot screenshot7 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot7.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="TOTAL SAVINGS BY STRATEGY AND SWITCHES YTD.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[10]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait8 = new WebDriverWait(browser,30);
		wait8.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[21]/div/div/div/div/img")));
		Screenshot screenshot8 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot8.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="TOTAL SAVINGS BY STRATEGY AND SWITCHES.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[11]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait9 = new WebDriverWait(browser,30);
		wait9.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[22]/div/div/div/div/img")));
		Screenshot screenshot9 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot9.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="SAVINGS BY STRATEGY.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[12]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait10 = new WebDriverWait(browser,30);
		wait10.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[14]/div/div/div/div/img")));
		Screenshot screenshot10 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot10.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		System.out.println("10 Screenshot Taken , Please check the Screenshot folder of the Workspace");

		
		Filename ="TOP SWITCHES YEAR TO DATE-1-10.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[13]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait11 = new WebDriverWait(browser,30);
		wait11.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[18]/div/div/div/div/img")));
		Screenshot screenshot11 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot11.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="TOP SWITCHES YEAR TO DATE-11-20.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[14]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait12 = new WebDriverWait(browser,30);
		wait12.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[17]/div/div/div/div/img")));
		Screenshot screenshot12 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot12.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="TOP SWITCHES YEAR TO DATE-21-30.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[15]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait13 = new WebDriverWait(browser,30);
		wait13.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[16]/div/div/div/div/img")));
		Screenshot screenshot13 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot13.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="TOP SWITCHES-1-10.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[16]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait14 = new WebDriverWait(browser,30);
		wait14.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[18]/div/div/div/div/img")));
		Screenshot screenshot14 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot14.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="TOP SWITCHES-11-20.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[17]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait15 = new WebDriverWait(browser,30);
		wait15.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[18]/div/div/div/div/img")));
		Screenshot screenshot15 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot15.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		System.out.println("15 Screenshot Taken , Please check the Screenshot folder of the Directory");

		
		Filename ="TOP SWITCHES-21-30.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[18]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait16 = new WebDriverWait(browser,30);
		wait16.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[18]/div/div/div/div/img")));
		Screenshot screenshot16 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot16.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="MEMBER UTILIZATION BREAKDOWN.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[19]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait17 = new WebDriverWait(browser,30);
		wait17.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[35]/div/div/div/div/img")));
		Screenshot screenshot17 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot17.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="SAVINGS OPPORTUNITY BREAKDOWN.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[20]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait18 = new WebDriverWait(browser,30);
		wait18.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[16]/div/div/div/div/img")));
		Screenshot screenshot18 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot18.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="MAX DOLLAR VALUE SAVINGS OPPORTUNITY BREAKDOWN.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[21]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait19 = new WebDriverWait(browser,30);
		wait19.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[18]/div/div/div/div/img")));
		Screenshot screenshot19 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot19.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="MAX DOLLAR VALUE SAVINGS OPPORTUNITIES BY PURPLE DOT CATEGORY.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[22]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait20 = new WebDriverWait(browser,30);
		wait20.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[12]/div/div/div/div/img")));
		Screenshot screenshot20 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot20.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		System.out.println("20 Screenshot Taken , Please check the Screenshot folder of the Workspace");

		
		
		Filename ="MAX DOLLAR VALUE SAVINGS OPPORTUNITY MOVEMENT.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[23]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait21 = new WebDriverWait(browser,30);
		wait21.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[18]/div/div/div/div/img")));
		Screenshot screenshot21 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot21.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="SAVINGS OPPORTUNITY MOVEMENT.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[24]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait22 = new WebDriverWait(browser,30);
		wait22.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[18]/div/div/div/div/img")));
		Screenshot screenshot22 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot22.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="MAX DOLLAR VALUE SAVINGS OPPORTUNITIES BY CATEGORY.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[25]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait23 = new WebDriverWait(browser,30);
		wait23.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[16]/div/div/div/div/img")));
		Screenshot screenshot23 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot23.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="MAX DOLLAR VALUE SAVINGS OPPORTUNITY BY PLAN TYPE.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[26]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait24 = new WebDriverWait(browser,30);
		wait24.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[11]/div/div/div/div/img")));
		Screenshot screenshot24 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot24.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="TOTAL # OF MEMBERS BY # OF SAVINGS OPPORTUNITIES.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[27]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait25 = new WebDriverWait(browser,30);
		wait25.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[11]/div/div/div/div/img")));
		Screenshot screenshot25 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot25.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		System.out.println("25 Screenshot Taken , Please check the Screenshot folder of the Directory");

		
		
		Filename ="TOP 10 MAX DOLLAR VALUE SAVINGS OPPORTUNITIES.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[28]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait26 = new WebDriverWait(browser,30);
		wait26.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[20]/div/div/div/div/img")));
		Screenshot screenshot26 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot26.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="TOP 11-20 MAX DOLLAR VALUE SAVINGS OPPORTUNITIES.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[29]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait27 = new WebDriverWait(browser,30);
		wait27.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[19]/div/div/div/div/img")));
		Screenshot screenshot27 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot27.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="TOP 21-30 MAX DOLLAR VALUE SAVINGS OPPORTUNITIES.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[30]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait28 = new WebDriverWait(browser,30);
		wait28.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[19]/div/div/div/div/img")));
		Screenshot screenshot28 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot28.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="MEMBER ENGAGEMENT.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[31]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait29 = new WebDriverWait(browser,30);
		wait29.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[33]/div/div/div/div/img")));
		Screenshot screenshot29 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot29.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="UTILIZATION TRENDS.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[32]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait30 = new WebDriverWait(browser,30);
		wait30.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[11]/div/div/div/div/img")));
		Screenshot screenshot30 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot30.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		System.out.println("30 Screenshot Taken , Please check the Screenshot folder of the Workspace");

		
		
		Filename ="TOP 1-10 MEMBER ENGAGEMENT SAVINGS OPPORTUNITIES BY INDICATION.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[33]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait31 = new WebDriverWait(browser,30);
		wait31.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[18]/div/div/div/div/img")));
		Screenshot screenshot31 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot31.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="TOP 11-20 MEMBER ENGAGEMENT SAVINGS OPPORTUNITIES BY INDICATION.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[34]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait32 = new WebDriverWait(browser,30);
		wait32.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[18]/div/div/div/div/img")));
		Screenshot screenshot32 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot32.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="TOP 21-30 MEMBER ENGAGEMENT SAVINGS OPPORTUNITIES BY INDICATION.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[35]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait33 = new WebDriverWait(browser,30);
		wait33.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[18]/div/div/div/div/img")));
		Screenshot screenshot33 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot33.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		
		Filename ="PLAN AND MEMBER MONTHLY SAVINGS BREAKOUT-1.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[36]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait34 = new WebDriverWait(browser,30);
		wait34.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[117]/div/div/div/div/img")));
		Screenshot screenshot34 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot34.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
		
		Filename ="PLAN AND MEMBER MONTHLY SAVINGS BREAKOUT-2.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[37]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait35 = new WebDriverWait(browser,30);
		wait35.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[12]/div/div/div/div/img")));
		Screenshot screenshot35 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot35.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		
				
		Filename ="COVER SLIDE.png";
		browser.findElement(By.xpath(ZoomoutArrow)).click();	
		Thread.sleep(8000);
		browser.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/span")).click();		
		Thread.sleep(8000);
		WebDriverWait wait36 = new WebDriverWait(browser,30);
		wait36.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[9]/div/div/div/div/img")));
		Screenshot screenshot36 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(browser);
		ImageIO.write(screenshot36.getImage(), "PNG",new File(System.getProperty("user.dir") + "\\Screenshot\\" + Filename));
		ImageResize.Crop(Filename);
		System.out.println("36 Screenshot Taken , Please check the Screenshot folder of the Directory");

		
		System.out.println("TASK COMPLETED FOR CAPTURING SCREENSHOT");
		
	}
	
	
	@AfterSuite(alwaysRun = true, description = "Tear down web driver")
	public void tearDown() throws InterruptedException {
	browser.quit();
	}
}