package selenium.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    public WebDriver initializeDriver(){
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        WebDriverManager.chromedriver().cachePath("Drivers").setup();
        WebDriver driver =new ChromeDriver(options);
        driver.manage().window().maximize();
//        driver.getWindowHandle();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}
