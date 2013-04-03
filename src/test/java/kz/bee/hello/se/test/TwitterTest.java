package kz.bee.hello.se.test;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class TwitterTest  {

    private WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testGoogle() throws InterruptedException {

        // Load Twitter
        driver.get("http://www.twitter.com");

        // Login
        WebElement email = driver.findElement(By.id("signin-email"));
        WebElement password = driver.findElement(By.id("signin-password"));

        email.sendKeys("Michael45300981");
        password.sendKeys("annihilation");

        email.submit();

        // Check the title of the page
        Assert.assertEquals(driver.getTitle(), "Twitter");

        // Generate a unique tweet
        String tweet = "Hello " + Math.random();

        // Make a tweet
        WebElement tweetBoxMini = driver.findElement(By.id("tweet-box-mini-home-profile"));
        tweetBoxMini.sendKeys(tweet);
        driver.findElement(By.xpath("//button[@class='btn primary-btn tweet-action' and @type='button']")).click();

        // Check the tweet is visible in tweetstream
        Assert.assertTrue(driver.findElements(By.xpath("//p[@class='js-tweet-text' and text()='"+tweet+"']")).size() == 1);
    }

    @After
    public void teardown() {
        //Close the browser
        driver.quit();
    }
}