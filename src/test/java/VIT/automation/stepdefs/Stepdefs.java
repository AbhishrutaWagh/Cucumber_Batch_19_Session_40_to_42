package VIT.automation.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Stepdefs {
	
	private static final Logger logger = LogManager.getLogger(Stepdefs.class);

	Scenario scn;  //Scenario is class
	WebDriver driver;
	String baseUrl="https://www.amazon.in/";
	int implicit_wait_timeout_in_sec=20;
	WebDriverWait wait;
	int webDriver_wait_in_sec=20;

	@Before     //import from io.cucumber.java ...@Before and @After are Hooks import from io.cucumber.java
	public void setUp(Scenario scn)
	{
		this.scn=scn;         //cucumber-picocontainer dependency should be in pom.xml
		driver=new ChromeDriver();
		logger.info("Browser got set"); //this logger print statement is print over the console(logger is low level logging)
		driver.manage().window().maximize();
		logger.info("Browser got maximized");
		driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
		logger.info("Browser implicite timeout set to "+implicit_wait_timeout_in_sec);
		scn.log("Browser got invok");    //this print statement use(print in)in cucumber report and also over console
		
	}
	@After    //import from io.cucumber.java
	public void tearDown()
	{
		driver.quit();
		logger.info("Browser got closed");
		scn.log("Browser got closed");
		
	}

	//@Given("User open the browser")
	//public void user_open_the_browser() {
	//	
	//	driver=new ChromeDriver();
	//	driver.manage().window().maximize();
	//	driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
	//}

	@Given("User navigated to the landing page of the application")
	public void user_navigated_to_the_landing_page_of_the_application() {

		driver.get(baseUrl);
		logger.info("Browser got invoked with URL as-> "+baseUrl);
		String expectedTitle="Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle=driver.getTitle();

		Assert.assertEquals("Page title is not macthing",expectedTitle, actualTitle);
		logger.info("Assertion for page title validation is pass with expected as-> "+expectedTitle+" and actual as-> "+actualTitle);
		scn.log("User navigated to the landing page of the application");
	}

	@When("User search for product {string}")
	public void user_search_for_product(String productName) {

		//wait and search product
		wait = new WebDriverWait(driver,webDriver_wait_in_sec);
		logger.info("WebDriverWait time set to-> "+webDriver_wait_in_sec);
		WebElement searchBarEle = driver.findElement(By.id("twotabsearchtextbox"));
		WebElement elementSearchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBarEle));
		logger.info("Waiting for webelement->elementsearch box  to be clickable");
		elementSearchBox.sendKeys(productName);
		logger.info("Sending keys in search box");
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		logger.info("Clicking on search button");
		scn.log("User searched for product");

	}

	@Then("Search result is displayed {string}")
	public void search_result_is_displayed(String productName) {

		wait.until(ExpectedConditions.titleIs("Amazon.in : "+productName));
		logger.info("waiting until title is getting");
		//Assertion for page title
		Assert.assertEquals("Page title is not maching","Amazon.in : "+productName,driver.getTitle());
		//if assertion get fail then scn.log print statetement will not print
		logger.info("Assertion for checking page title validation is pass with expected as-> "+"Amazon.in : "+productName+" and actual as-> "+driver.getTitle());
		scn.log("Search result is displayed");
	}

	@When("User click on any product")
	public void user_click_on_any_product() {
		//listOfProducts will have all the links displayed in the search box
		List<WebElement> listOfProducts = driver.findElements(By.xpath("//span[text()='RESULTS']//ancestor::div[@class='s-main-slot s-result-list s-search-results sg-row']//span[@class='a-size-medium a-color-base a-text-normal']"));

		//But as this step asks click on any link, we can choose to click on Index 0 of the list
		listOfProducts.get(0).click();

		scn.log("User clicked on any product");
	}

	@Then("Product Description is displayed in new tab")
	public void product_description_is_displayed_in_new_tab() {
		
		//As product description click will open new tab, we need to switch the driver to the new tab
		//If we do not switch, we can not access the new tab html elements
		//This is how we do it
		
		Set<String> windowsId = driver.getWindowHandles(); // get all the open windowsId
		Iterator<String> it = windowsId.iterator(); // get the iterator to iterate the elements in set
		String original = it.next();//gives the parent window id
		String prodDescp = it.next();//gives the child window id

		driver.switchTo().window(prodDescp); // switch to product Descp

		//Now driver can access new driver window (means prodDescp window), but can not access the orignal tab
		//Check product title is displayed
		WebElement productTitle = driver.findElement(By.id("productTitle"));
		Assert.assertEquals("Product Title",true,productTitle.isDisplayed());

		WebElement addToCartButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		Assert.assertEquals("Product Title",true,addToCartButton.isDisplayed());

		//Switch back to the Original Window, however no other operation to be done
		driver.switchTo().window(original);

		scn.log("Product Description is displayed in new tab");
	}

	//@Then("browser is closed")
	//public void browser_is_closed() {
	//
	//	driver.quit();
}


