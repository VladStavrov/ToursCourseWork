package com.example.servicemodule.s.Parsing;


import com.example.commonmodule.exception.LocalException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.HttpStatus;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
public class ParseImageFromSite {


    public List<String> getImages(Long hotelId)  {
        List<String> imageList= new ArrayList<>();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.tez-tour.com/hotel.html?id="+hotelId);
        WebElement closeButton = findElementIfExists(driver, By.id("fancybox-close"));
        if (closeButton != null) {
            closeButton.click();
        }
        try {
            WebElement tab3Elements = driver.findElement(By.cssSelector("a#tab-3"));
            tab3Elements.click();


            WebElement photoContainer = driver.findElement(By.cssSelector("div#tab-3.hotel-page-tab-container .photo-th"));

// Находим все ссылки внутри этого div
            List<WebElement> imageLinks = photoContainer.findElements(By.tagName("a"));
            imageLinks.forEach(image->{
                imageList.add(image.getAttribute("href"));
            });
            System.out.println(imageList);
            driver.quit();
        }
        catch (Exception e){
            throw  new LocalException(HttpStatus.NOT_FOUND,"Фотографий не было найдено");
        }



        return imageList;
    }
    private static WebElement findElementIfExists(WebDriver driver, By by) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            return null;
        }
    }

}
