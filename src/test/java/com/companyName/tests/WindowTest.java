package com.companyName.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class WindowTest {

    @Test  // This is called annotation
    public void test1() throws InterruptedException {
        // WebDriverManager.chromedriver().setup();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // Open the browser
        driver.manage().window().maximize();

        driver.get("https://letcode.in/windows"); // open the application


       String currentWindowHandle  = driver.getWindowHandle(); // control/context/handle of parent/main window
        driver.findElement(By.id("multi")).click();

        Set<String> allWindowHandles = driver.getWindowHandles();

        for(String windowHandle : allWindowHandles){
            driver.switchTo().window(windowHandle);
            if(driver.findElements(By.id("accept")).size()==1){
                driver.findElement(By.id("accept")).click();
                Alert alert = driver.switchTo().alert();
                Thread.sleep(2000);
                alert.accept();
                driver.close();
                break;
            }
        }
        Thread.sleep(2000);
        driver.quit();  //
    }
}
