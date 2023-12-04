package Tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneFile {

	public static void main(String[] args) throws InterruptedException, AWTException {
		//Setting up the Webdriver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--allow-remote-origins=*");
		
		//variables
		String countryName = "🇮🇳 India";
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String formattedDateTime = currentDateTime.format(formatter);
		String slugifiedDateTime = formattedDateTime.replaceAll(":", "_").replaceAll("\\s", "T");
		String fileName = "phonenumber.txt";
        long phoneNumber;
        String resumeFilePath = "C:\\Users\\ronak\\OneDrive\\Desktop\\My Resume.pdf";
        
        String jobRole = "Backend Developer";
        String[] primarySkills = {"Python", "Django", "REST API"};
        String[] secondarySkills = {"HTML", "CSS"};

        // Read the last generated phone number from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String lastPhoneNumber = reader.readLine();
            phoneNumber = Long.parseLong(lastPhoneNumber) + 1;
        } catch (IOException | NumberFormatException e) {
            // Handle file read or number format exception
            phoneNumber = 1000000000L; // Starting phone number if file read fails
        }

        // Save the new phone number back to the file for the next run
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.valueOf(phoneNumber));
        } catch (IOException e) {
            // Handle file write exception
            e.printStackTrace();
        }
		//LocalDate currentDate = LocalDate.now();
		
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://dev.zelevate.io/");
		
		//Sign-up a candidate
		driver.findElement(By.xpath("//button[text()=' Create Your Profile']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		driver.findElement(By.id("name")).sendKeys("Ronak " + slugifiedDateTime);
		driver.findElement(By.id("email")).sendKeys("Ronak" + slugifiedDateTime + "@gmail.com");
		WebElement phoneCode = driver.findElement(By.cssSelector(".flex-nowrap .pr-1"));
		phoneCode.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder = 'Search Country']")));
		List<WebElement> countryOptions = driver.findElements(By.cssSelector(".cursor-pointer"));
		WebElement countryOpt = countryOptions.stream().filter(countryOption
				->countryOption.findElement(By.xpath("//div[text()='🇮🇳 India']")).getText().equals(countryName)).findFirst().orElse(null);
		countryOpt.findElement(By.xpath("//div[text()='🇮🇳 India']")).click();
		driver.findElement(By.id("mobile__form_value.phoneNumber")).sendKeys(phoneNumber + "");
		driver.findElement(By.xpath("//span[text()='Request OTP']")).click();
		
		//OTP Verification button
		WebElement verifyBtn = driver.findElement(By.xpath("//button[text()='Verify']"));
		wait.until(ExpectedConditions.elementToBeClickable(verifyBtn));
		verifyBtn.click();
		
		//Upload Resume
		driver.findElement(By.xpath("//button[text()='browse files']")).click();
		Thread.sleep(2000);
		Robot robot = new Robot();
		StringSelection stringSelection = new StringSelection(resumeFilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Upload 1 file']")));
        driver.findElement(By.xpath("//button[text()='Upload 1 file']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        //send social profile link 
        driver.findElement(By.id("linkedinURL")).sendKeys("www.linkedin.com/in/ronakvala");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        //click next button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button button-base button-primary-outline']")));
        try {
            driver.findElement(By.xpath("//button[@class='button button-base button-primary-outline']")).click();
        } catch (StaleElementReferenceException e) {
            // Re-locate the element before interacting with it again
            driver.findElement(By.xpath("//button[@class='button button-base button-primary-outline']")).click();
        }
        
        //job role dropdown
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Search job role']")));
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.xpath("//div[text()='Search job role']"))).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Backend Developer']")));
        driver.findElement(By.xpath("//label[text()='Backend Developer']")).click();
        
        //Primary skills dropdown - adding 3 skills 
        actions.click(driver.findElement(By.xpath("//div[text()='Search skill']"))).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()= 'Rust']")));
        driver.findElement(By.xpath("//label[text()='Python']")).click();
        
        actions.click(driver.findElement(By.xpath("//div[text()='Search skill']"))).build().perform();
        actions.sendKeys(driver.findElement(By.xpath("//div[text()='Search skill']")),"Django").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()= 'Django']")));
        driver.findElement(By.xpath("//label[text()='Django']")).click();
        
        actions.click(driver.findElement(By.xpath("//div[text()='Search skill']"))).build().perform();
        actions.sendKeys(driver.findElement(By.xpath("//div[text()='Search skill']")),"REST API").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()= 'REST API']")));
        driver.findElement(By.xpath("//label[text()='REST API']")).click();
        
        //Secondary skills dropdown - adding 1 skill
        actions.click(driver.findElement(By.id("react-select-other_skills-placeholder"))).build().perform();
        actions.sendKeys(driver.findElement(By.id("react-select-other_skills-placeholder")),"CSS").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()= 'CSS']")));
        driver.findElement(By.xpath("//label[text()='CSS']")).click();
        
        //Scroll by 250 pixels
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");
        
        //Adding experience
        driver.findElement(By.xpath("//button[text()=' Add Experience']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Internship']")));
        js.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.xpath("//input[@value='Internship']")).click();
        driver.findElement(By.id("experiences[0].job_title")).sendKeys("Backend intern");
        
        //company name dropdown in experience 
        actions.click(driver.findElement(By.id("react-select-experiences[0].company_detail-placeholder"))).build().perform();
        actions.sendKeys(driver.findElement(By.id("react-select-experiences[0].company_detail-placeholder")),"ABC Tech").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='ABC Tech']")));
        driver.findElement(By.xpath("//label[text()='ABC Tech']")).click();
        
        //company location dropdown in experience
        actions.click(driver.findElement(By.id("react-select-experiences[0].location_details-placeholder"))).build().perform();
        actions.sendKeys(driver.findElement(By.id("react-select-experiences[0].location_details-placeholder")),"Mumbai").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Mumbai']")));
        driver.findElement(By.xpath("//label[text()='Mumbai']")).click();
        
        //experience start date calendar
        actions.click(driver.findElement(By.id("experiences[0].start_date__form_value"))).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@aria-label='January 1, 2023']")));
        actions.click(driver.findElement(By.xpath("//span[@aria-label='January 1, 2023']"))).build().perform();
        
        //actions.click(driver.findElement(By.id("experiences[0].end_date__form_value"))).build().perform();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text='January']")));
        //actions.click(driver.findElement(By.xpath("//span[text='April']"))).build().perform();
        driver.findElement(By.id("experiences[0].is_currently_working")).click();
        js.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.xpath("//button[text()=' Add Education']")).click();
        js.executeScript("window.scrollBy(0,1250)");
        actions.click(driver.findElement(By.id("react-select-educations[0].institution_details-placeholder"))).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-educations[0].institution_details-option-0")));
        actions.click(driver.findElement(By.xpath("//div[@id='react-select-educations[0].institution_details-option-0']"))).build().perform();
        
        actions.click(driver.findElement(By.id("react-select-educations[0].degree_details-placeholder"))).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='energy systems engg.']")));
        actions.click(driver.findElement(By.xpath("//label[text()='energy systems engg.']"))).build().perform();
        js.executeScript("window.scrollBy(0,250)");
        
        actions.click(driver.findElement(By.id("react-select-educations[0].location_details-placeholder"))).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-educations[0].location_details-option-0")));
        actions.click(driver.findElement(By.id("react-select-educations[0].location_details-option-0"))).build().perform();
        
        actions.click(driver.findElement(By.id("educations[0].start_date__form_value"))).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@aria-label='Month']")));
        //driver.findElement(By.xpath("(//span[text()='1'])[1]")).click();
        actions.click(driver.findElement(By.xpath("(//span[text()='1'])[1]"))).build().perform();
        
        actions.click(driver.findElement(By.id("educations[0].end_date__form_value"))).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='1'])[3]")));
        actions.click(driver.findElement(By.xpath("(//span[text()='1'])[3]"))).build().perform();
        js.executeScript("window.scrollBy(0,500)");
        
        driver.findElement(By.xpath("//button[text()='Add']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("links[0].url")));
        driver.findElement(By.id("links[0].url")).sendKeys("www.linkedin.com/in/ronakvala");
        driver.findElement(By.xpath("(//button[text()='Next'])[1]")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-desired_industries_details-placeholder")));
        actions.click(driver.findElement(By.id("react-select-desired_industries_details-placeholder"))).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Accounting & Auditing']")));
        driver.findElement(By.xpath("//label[text()='Accounting & Auditing']")).click();
        js.executeScript("window.scrollBy(0,500)");
        
        driver.findElement(By.id("current_ctc")).sendKeys("5");
        driver.findElement(By.id("expected_ctc")).sendKeys("8");
        driver.findElement(By.id("offer_in_hand_ctc")).sendKeys("6");
        js.executeScript("window.scrollBy(0,500)");
        
        driver.findElement(By.id("location_preference_new__form_value[Onsite]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-preferred_location_new-placeholder")));
        actions.click(driver.findElement(By.id("react-select-preferred_location_new-placeholder"))).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-preferred_location_new-option-1")));
        actions.click(driver.findElement(By.id("react-select-preferred_location_new-option-1"))).build().perform();
        js.executeScript("window.scrollBy(0,250)");
        
        driver.findElement(By.xpath("//div[text()='1-10']")).click();
        driver.findElement(By.xpath("//div[text()='Seed Stage']")).click();
        driver.findElement(By.xpath("(//button[text()='Create'])[1]")).click();   
		}
}
