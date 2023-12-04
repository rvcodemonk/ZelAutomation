package Tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPageCopy {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--allow-remote-origins=*");
				
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://dev.zelevate.io/");
				
		String countryName = "🇮🇳 India";
		long phoneNumber = 9119926737L;
		int jobNumber;
		String fileName = "jobnumber2.txt";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		// Read the last generated phone number from the file
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String lastJobNumber = reader.readLine();
            jobNumber = (int) (Long.parseLong(lastJobNumber) + 1);
        } catch (IOException | NumberFormatException e) {
            // Handle file read or number format exception
            jobNumber = 1; // Starting phone number if file read fails
        }

        // Save the new phone number back to the file for the next run
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.valueOf(jobNumber));
        } catch (IOException e) {
            // Handle file write exception
            e.printStackTrace();
        }
		
		
		//Login button click on homepage
		driver.findElement(By.xpath("(//button[text()='Login'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mobile__form_value.phoneNumber")));
		WebElement phoneCode = driver.findElement(By.cssSelector(".flex-nowrap .pr-1"));
		phoneCode.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder = 'Search Country']")));
		List<WebElement> countryOptions = driver.findElements(By.cssSelector(".cursor-pointer"));
		WebElement countryOpt = countryOptions.stream().filter(countryOption
				->countryOption.findElement(By.xpath("//div[text()='🇮🇳 India']")).getText().equals(countryName)).findFirst().orElse(null);
		countryOpt.findElement(By.xpath("//div[text()='🇮🇳 India']")).click();
		driver.findElement(By.id("mobile__form_value.phoneNumber")).sendKeys(phoneNumber + "");
		driver.findElement(By.xpath("//button[text()='Request OTP']")).click();
		
		Thread.sleep(5000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Jobs']")));
		String xpathApply = "(//button[text()='View Details'])["+ jobNumber +"]";
		System.out.println(xpathApply);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flex-1.overflow-auto")));
		WebElement elementToBeScroll = driver.findElement(By.cssSelector(".flex-1.overflow-auto"));
		int factorForScroll = 400 * jobNumber ;
		//js.executeScript("window.scrollBy(0,"+factorForScroll+")");
		//js.executeScript("window.scrollBy(0,250)");
		js.executeScript("arguments[0].scrollTop += "+factorForScroll+";", elementToBeScroll);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathApply)));
		driver.findElement(By.xpath(xpathApply)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Apply']")));
		driver.findElement(By.xpath("//span[text()='Apply']")).click();
		driver.findElement(By.cssSelector(".iconify.iconify--mdi :first-child")).click();
		driver.findElement(By.xpath("(//div[@class='flex items-center h-full'])[2]")).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Sign out']")));
		//driver.findElement(By.xpath("//div[text()='Sign out']")).click();
	}

}
