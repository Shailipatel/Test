package automationFramework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TestNG {
	public WebDriver driver;
 
  @BeforeMethod
  public void beforeMethod() {
	 // System.setProperty("webdriver.firefox.marionette", "./geckodriver.exe");
	  System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("http://localhost/Test/test.html");   //Replace with acutal file path
  }

  
  @Test
//  Check The Required fields by not filling any data
  public void requiredFields() {
	  driver.findElement(By.id("submit")).click(); // clicking on submit button 
	  WebElement firstname = driver.findElement(By.id("fname-error")); // locating html element by its id
	  WebElement lastname = driver.findElement(By.id("lname-error"));
	  WebElement phone = driver.findElement(By.id("phone-error"));
	  Assert.assertEquals(firstname.getText(),"First name is a required field"); //geting text of the html element and comaparing with the expected result 
	  Assert.assertEquals(lastname.getText(),"Last name is a required field");
	  Assert.assertEquals(phone.getText(),"Phone number is a required input");
  }
  @Test
//  Check the Required field by not filling First Name data
  public void FirstNameRequiredField() { 
	  driver.findElement(By.id("lname")).sendKeys("ABC"); // sending last name value in textfield 
	  driver.findElement(By.id("phone")).sendKeys("123-456-4545");
	  driver.findElement(By.id("submit")).click();
	  WebElement firstname = driver.findElement(By.id("fname-error"));
	  Assert.assertEquals(firstname.getText(),"First name is a required field");
  }
  @Test
//  Check the Required field by not filling Last Name data
  public void LastNameRequiredField() {
	  driver.findElement(By.id("fname")).sendKeys("ABC");
	  driver.findElement(By.id("phone")).sendKeys("123-456-4545");
	  driver.findElement(By.id("submit")).click();
	  WebElement lastname = driver.findElement(By.id("lname-error"));
	  Assert.assertEquals(lastname.getText(),"Last name is a required field");
  }
  @Test
//  Check the Required fields by not filling Phone Number data
  public void PhoneNumberRequiredField() {
	  driver.findElement(By.id("fname")).sendKeys("ABC");
	  driver.findElement(By.id("lname")).sendKeys("XYZ");
	  driver.findElement(By.id("submit")).click();
	  WebElement phone = driver.findElement(By.id("phone-error"));
	  Assert.assertEquals(phone.getText(),"Phone number is a required input");
  }
  @Test
//  Check that special characters are not allowed for first name and last name input
  public void OnlyCharacter() {
	  driver.findElement(By.id("fname")).sendKeys("@#$%");
	  driver.findElement(By.id("lname")).sendKeys("@#$%");
	  driver.findElement(By.id("phone")).sendKeys("123-456-4545");
	  driver.findElement(By.id("submit")).click();
	  WebElement firstname = driver.findElement(By.id("fname-error"));
	  WebElement lastname = driver.findElement(By.id("lname-error"));
	  Assert.assertEquals(firstname.getText(),"First name can only be characters");
	  Assert.assertEquals(lastname.getText(),"Last name can only be characters");
  }
  @Test
//  Check that Max Length is 20 for First Name And Last Name input 
  public void MaxLength() {
	  driver.findElement(By.id("fname")).sendKeys("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	  driver.findElement(By.id("lname")).sendKeys("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	  driver.findElement(By.id("phone")).sendKeys("123-456-4545");
	  driver.findElement(By.id("submit")).click();
	  WebElement firstname = driver.findElement(By.id("fname-error"));
	  WebElement lastname = driver.findElement(By.id("lname-error"));
	  Assert.assertEquals(firstname.getText(),"The max length of first name is 20");
	  Assert.assertEquals(lastname.getText(),"The max length of last name is 20");
  }
  @Test
//  Check that phone input match the phone number format 
  public void PhoneNumberFormat() {
	  driver.findElement(By.id("fname")).sendKeys("ABC");
	  driver.findElement(By.id("lname")).sendKeys("XYZ");
	  driver.findElement(By.id("phone")).sendKeys("123456#$4!@!@@  ");
	  driver.findElement(By.id("submit")).click();
	  WebElement phone = driver.findElement(By.id("phone-error"));
	  Assert.assertEquals(phone.getText(),"Phone is incorrect");
  }
  @Test
//  Check if correct input entered , form submitted message and input values should be printed.
  public void SubmittedSuccess() {
	  driver.findElement(By.id("fname")).sendKeys("ABC");
	  driver.findElement(By.id("lname")).sendKeys("XYZ");
	  driver.findElement(By.id("phone")).sendKeys("1234567890");
	  driver.findElement(By.id("submit")).click();
	  Assert.assertEquals((driver.findElement(By.id("fname-submit")).getText()),"ABC");
	  Assert.assertEquals((driver.findElement(By.id("lname-submit")).getText()),"XYZ");
	  Assert.assertEquals((driver.findElement(By.id("phone-submit")).getText()),"1234567890");
  }
  @Test
//  Check if you click on clear Form Button it will clear the text box
  public void ClearForm() {
	  driver.findElement(By.id("fname")).sendKeys("ABC");
	  driver.findElement(By.id("lname")).sendKeys("XYZ");
	  driver.findElement(By.id("phone")).sendKeys("1234567890");
	  WebElement clearbutton =  driver.findElement(By.xpath("html/body/form/button[2]"));
	  clearbutton.click();
	  String firstname = driver.findElement(By.id("fname")).getAttribute("value");
	  String lastname = driver.findElement(By.id("lname")).getAttribute("value");
	  String phonenumber = driver.findElement(By.id("phone")).getAttribute("value");
	  Assert.assertEquals(firstname, "");
	  Assert.assertEquals(lastname, "");
	  Assert.assertEquals(phonenumber, "");
  }
  @AfterMethod
  public void afterMethod() throws Exception{
	//Close the driver
	 driver.manage().timeouts().implicitlyWait(240, TimeUnit.MINUTES);
	  driver.quit(); 
  }
}

