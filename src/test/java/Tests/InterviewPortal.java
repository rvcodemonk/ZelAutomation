package Tests;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InterviewPortal {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//code to send to the code editor
		Random random = new Random();
		int randomCode = random.nextInt(3);
		 String code1 = "import React, { useState, useEffect } from 'react';\r\n"
		 		+ "function Home() {\r\n"
		 		+ "  const [count, setCount] = useState(0);\r\n"
		 		+ "  useEffect(() => {\r\n"
		 		+ "    const intervalId = setInterval(() => {\r\n"
		 		+ "      setCount((prevCount) => prevCount + 1);\r\n"
		 		+ "    }, 1000);\r\n"
		 		+ "    return () => {\r\n"
		 		+ "      clearInterval(intervalId); // Clean up the interval when the component unmounts\r\n"
		 		+ "    };\r\n"
		 		+ "  }, []); // The empty dependency array ensures this effect runs only once\r\n"
		 		+ "  return (\r\n"
		 		+ "<div>\r\n"
		 		+ "<h1>Home Page</h1>\r\n"
		 		+ "<p>Welcome to the home page of our SPA!</p>\r\n"
		 		+ "<p>Count: {count}</p>\r\n"
		 		+ "</div>\r\n"
		 		+ "  );\r\n"
		 		+ "}\r\n"
		 		+ "export default Home;";
		 String code2 = "import React, { useState, useEffect } from 'react';\r\n"
		 		+ "function RandomNumberGenerator() {\r\n"
		 		+ "  const [randomNumber, setRandomNumber] = useState(null);\r\n"
		 		+ "  useEffect(() => {\r\n"
		 		+ "    const intervalId = setInterval(() => {\r\n"
		 		+ "      const newRandomNumber = Math.floor(Math.random() * 100); // Generates a random number between 0 and 99\r\n"
		 		+ "      setRandomNumber(newRandomNumber);\r\n"
		 		+ "    }, 2000); // Generates a new random number every 2 seconds\r\n"
		 		+ "    return () => {\r\n"
		 		+ "      clearInterval(intervalId); // Clean up the interval when the component unmounts\r\n"
		 		+ "    };\r\n"
		 		+ "  }, []); // The empty dependency array ensures this effect runs only once\r\n"
		 		+ "  return (\r\n"
		 		+ "    <div>\r\n"
		 		+ "      <h1>Random Number Generator</h1>\r\n"
		 		+ "      {randomNumber !== null && <p>Random Number: {randomNumber}</p>}\r\n"
		 		+ "      {randomNumber === null && <p>Generating a random number...</p>}\r\n"
		 		+ "    </div>\r\n"
		 		+ "  );\r\n"
		 		+ "}\r\n"
		 		+ "export default RandomNumberGenerator;\r\n";
		 String code3 = "import React, { useState, useEffect } from 'react';\r\n"
		 		+ "function WeatherApp() {\r\n"
		 		+ "  const [weather, setWeather] = useState(null);\r\n"
		 		+ "  useEffect(() => {\r\n"
		 		+ "    fetch('https://api.weatherapi.com/v1/current.json?key=YOUR_API_KEY&q=New York')\r\n"
		 		+ "      .then(response => response.json())\r\n"
		 		+ "      .then(data => {\r\n"
		 		+ "        setWeather(data.current);\r\n"
		 		+ "      })\r\n"
		 		+ "      .catch(error => {\r\n"
		 		+ "        console.error('Error fetching weather data: ', error);\r\n"
		 		+ "      });\r\n"
		 		+ "  }, []); // The empty dependency array ensures this effect runs only once\r\n"
		 		+ "  return (\r\n"
		 		+ "    <div>\r\n"
		 		+ "      <h1>Weather App</h1>\r\n"
		 		+ "      {weather !== null && (\r\n"
		 		+ "        <div>\r\n"
		 		+ "          <p>Temperature: {weather.temp_c}°C</p>\r\n"
		 		+ "          <p>Condition: {weather.condition.text}</p>\r\n"
		 		+ "        </div>\r\n"
		 		+ "      )}\r\n"
		 		+ "      {weather === null && <p>Loading weather data...</p>}\r\n"
		 		+ "    </div>\r\n"
		 		+ "  );\r\n"
		 		+ "}\r\n"
		 		+ "export default WeatherApp;\r\n";
		String codeToSend;
		switch(randomCode) {
		case 0: 
			codeToSend = code1;
			break;
		case 1:
			codeToSend = code2;
			break;
		case 2:
			codeToSend = code3;
			break;
		default:
			codeToSend = code1;
			break;
		}
		String countryName = "🇮🇳 India";
		long phoneNumber = 6396102630L;
		
		//setup the web driver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--allow-remote-origins=*");
		Actions actions = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(90));
		
		//candidate interview link navigation
		driver.get("https://devconnect.zelevate.io/s/DMqDWv84U2pG5TUSToGtTq?utm_source=email");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open()");

        // Get the handles of all open windows/tabs
        Set<String> allWindowHandles = driver.getWindowHandles();
        String originalTab = driver.getWindowHandle();
        String newTab = null;
        for (String handle : allWindowHandles) {
            if (!handle.equals(originalTab)) {
                newTab = handle;
                break;
            }
        }
        // Switch to the new tab
        driver.switchTo().window(newTab);

        // Navigate to a URL in the new tab
        driver.get("https://dev.Zelevate.io");

        // Perform operations in the new tab, login as interviewer and open interviewer link in the new tab
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Login'])[1]")));
        driver.findElement(By.xpath("(//button[text()='Login'])[1]")).click();
        driver.findElement(By.xpath("(//input[@type='radio'])[3]")).click();
        WebElement phoneCode = driver.findElement(By.cssSelector(".flex-nowrap .pr-1"));
		phoneCode.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder = 'Search Country']")));
		List<WebElement> countryOptions = driver.findElements(By.cssSelector(".cursor-pointer"));
		WebElement countryOpt = countryOptions.stream().filter(countryOption
				->countryOption.findElement(By.xpath("//div[text()='🇮🇳 India']")).getText().equals(countryName)).findFirst().orElse(null);
		countryOpt.findElement(By.xpath("//div[text()='🇮🇳 India']")).click();
		driver.findElement(By.id("mobile__form_value.phoneNumber")).sendKeys(phoneNumber + "");
		driver.findElement(By.xpath("//button[text()='Request OTP']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Join'])[1]")));
		driver.findElement(By.xpath("(//button[text()='Join'])[1]")).click();
		Thread.sleep(2000);	
		driver.switchTo().window(newTab);
		driver.close();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Join Interview'])[1]")));
		//driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Unblock']")));
		//driver.findElement(By.xpath("//button[text()='Unblock']")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//svg[@class='text-lg cursor-pointer iconify iconify--tabler']")));
		//driver.findElement(By.xpath("//svg[@class='text-lg cursor-pointer iconify iconify--tabler']")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Join Interview'])[1]")));
		
		//navigate back to the candidate tab and join as a candidate
		Thread.sleep(12000);
		driver.switchTo().window(originalTab);
		Thread.sleep(12000);
        driver.findElement(By.xpath("(//button[text()='Join Interview'])[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Start Screen Sharing']")));
        driver.findElement(By.xpath("//button[text()='Start Screen Sharing']")).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Code Editor']")));
        //driver.findElement(By.xpath("//p[text()='Code Editor']")).click();
        driver.findElement(By.cssSelector(".text-white.iconify.iconify--tabler")).click();
       // actions.click(driver.findElement(By.cssSelector(".text-white.iconify.iconify--tabler")));
       // actions.click(driver.findElement(By.xpath("//p[text()='Code Editor']")));
        driver.findElement(By.xpath("//p[text()='Code Editor']")).click();
        
        //click on the code editor
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='CodeMirror-scroll']")));
        //driver.findElement(By.cssSelector(".CodeMirror-cursors")).click();
        actions.click(driver.findElement(By.xpath("//div[@class='CodeMirror-scroll']"))).build().perform();
        
        //select the already typed code and delete it
        actions.keyDown(Keys.CONTROL)
        .sendKeys("a")
        .keyUp(Keys.CONTROL)
        .sendKeys(Keys.BACK_SPACE)
        .build()
        .perform();
        Thread.sleep(2000);
        actions.click(driver.findElement(By.id("editor"))).build().perform();
        
    	//send code to the code editor and delete the last 5 lines because they're autocompleted braces and parenthesis
        actions.sendKeys(driver.findElement(By.id("editor")),codeToSend+"\n").build().perform();
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
        actions.sendKeys(Keys.ARROW_DOWN);
            }
        for (int i = 0; i < 4; i++) {
        actions.keyDown(Keys.SHIFT).sendKeys(Keys.ARROW_UP).keyUp(Keys.SHIFT);
            }
        // Simulate deletion (Backspace or Delete key)
        actions.sendKeys(Keys.BACK_SPACE).build().perform();
            
        //chat automation and validation
        String expectedChatText1 = "Hello how are you?";
        String expectedChatText2 = "I'm fine, thanks";
        driver.findElement(By.xpath("(//button[@class='button button-base button-white p-tnano border-none dark:bg-inherit'])[2]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        driver.findElement(By.id("message")).sendKeys(expectedChatText1);
        actions.sendKeys(Keys.ENTER).perform();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button button-sm button-primary p-[10px] mb-[6px] dark:hover:bg-dark-800 rounded m-[2px]']")));
        //actions.click(driver.findElement(By.xpath("//button[@class='button button-sm button-primary p-[10px] mb-[6px] dark:hover:bg-dark-800 rounded m-[2px]']")));
        //Thread.sleep(5000);
        
        allWindowHandles = driver.getWindowHandles();
        originalTab = driver.getWindowHandle();
        newTab = null;
        for (String handle : allWindowHandles) {
            if (!handle.equals(originalTab)) {
                newTab = handle;
                break;
            }
        }
        driver.switchTo().window(newTab);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='caps-small text-black dark:text-white']")));
        WebElement chatElement1 = driver.findElement(By.xpath("//p[@class='caps-small text-black dark:text-white']"));
        String actualChatText1 = chatElement1.getText();
       
        if(actualChatText1.equalsIgnoreCase(expectedChatText1)) {
        	System.out.println("correct chat text");
        }
        else {
        	System.out.println("incorrect chat text");
        }
        chatElement1.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        driver.findElement(By.id("message")).sendKeys(expectedChatText2);
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button button-sm button-primary p-[10px] mb-[6px] dark:hover:bg-dark-800 rounded m-[2px]']")));
        //actions.click(driver.findElement(By.xpath("//button[@class='button button-sm button-primary p-[10px] mb-[6px] dark:hover:bg-dark-800 rounded m-[2px]']")));
        actions.sendKeys(Keys.ENTER).perform();
        driver.switchTo().window(originalTab);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='caps-small text-black dark:text-white']")));
        WebElement chatElement2 = driver.findElement(By.xpath("//p[@class='caps-small text-black dark:text-white']"));
        String actualChatText2 = chatElement2.getText();
        if(actualChatText2.equalsIgnoreCase(expectedChatText2)) {
        	System.out.println("correct chat text");
        }
        else {
        	System.out.println("incorrect chat text");
        }
        chatElement2.click();
        
	}
}
