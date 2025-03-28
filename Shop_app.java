package My_Shop;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Shop_app {

	WebDriver driver = new ChromeDriver();
	WebDriverWait myTime = new WebDriverWait(driver, Duration.ofSeconds(10));//Explicit wait


	//Launch the browser 
	@BeforeClass
	public void launchUrl() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //implicit wait
		driver.manage().window().maximize();
		driver.get("http://www.automationpractice.pl/index.php");
		Thread.sleep(2000);
	}

	//Login in to the website
	@Test(priority=1) 
	void userLogin(){
		WebElement signIn = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));
		signIn.click();
		
		WebElement mail = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		mail.sendKeys("Vinayak12@gmail.com");
		
		WebElement passWord = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwd")));
		passWord.sendKeys("vinayak12");
		
		WebElement submitBtn = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
		submitBtn.click();		
	}

	//Selecting category
	@Test(priority=2)
	void gotoSection(){
		WebElement section = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("WOMEN")));
		Actions sec = new Actions(driver);
		sec.moveToElement(section).build().perform();

		WebElement link = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Summer Dresses")));
		link.click();
	}
	
	//Select Item and add to Cart
	@Test(priority=3)
	void addToCart() throws InterruptedException {
		
		WebElement selectDress = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line last-line first-item-of-tablet-line first-item-of-mobile-line last-mobile-line']//a[@title='Printed Summer Dress'][normalize-space()='Printed Summer Dress']")));
		selectDress.click();
		
		WebElement Size = driver.findElement(By.xpath("//select[@name='group_1']"));
		Select st = new Select(Size);
		st.selectByVisibleText("L");
		
		WebElement selectColor = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.name("Blue")));
		selectColor.click();
		
		WebElement quantity = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btn btn-default button-plus product_quantity_up']")));
		Actions act = new Actions(driver);
		act.doubleClick(quantity).build().perform();
		
		WebElement cartBtn = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
		cartBtn.click();	
		
		WebElement checkOut = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btn btn-default button button-medium']")));
		checkOut.click();
	}

	//Payment Process
	@Test(priority=4)
	void paymentProcess() {
		
		WebElement checkout = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']")));
		checkout.click();
		
		WebElement CheckOut = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button btn btn-default button-medium']")));
		CheckOut.click();
		
		WebElement radioBtn = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv")));
		radioBtn.click();
		
		WebElement finalOut = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='processCarrier']")));
		finalOut.click();
		
		WebElement pay = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.className("cheque")));
		pay.click();
		
		WebElement confirmOrder = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button btn btn-default button-medium']")));
		confirmOrder.click();
	}

	//Logout
	@AfterClass
	void logOut() throws InterruptedException{
		
		WebElement orderHistory = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='button-exclusive btn btn-default']")));
		orderHistory.click();
		
		WebElement backToHome = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='btn btn-default button button-small'])[5]")));
		backToHome.click();
		
		WebElement signOut = myTime.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign out")));
		signOut.click();
		
		Thread.sleep(2000);
		driver.close();
	}
}
