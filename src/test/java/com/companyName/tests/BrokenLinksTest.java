package com.companyName.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BrokenLinksTest {

    @Test  // This is called annotation
    public void test1() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions); // Open the browser
        driver.manage().window().maximize();

        driver.get("http://www.zlti.com"); // open the application
        List<WebElement> alLinks = driver.findElements(By.xpath("//a"));
        //List<WebElement> alLinks = driver.findElements(By.tagName("a"));
        HttpURLConnection httpURLConnection;
        URL url = null;

        for (WebElement link : alLinks){
            String href = link.getAttribute("href");  // entire url
            try {
                url = new URL(href);
                httpURLConnection  = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();//
                if(httpURLConnection.getResponseCode()>=400 && httpURLConnection.getResponseCode()<=600){
                    System.out.println("Broken URL is :- " + href+ " :: " +"Response Code : " + httpURLConnection.getResponseCode());
                }
            }catch (Exception e){
                System.out.println(href);
            }


        }
        driver.quit();
    }
}
