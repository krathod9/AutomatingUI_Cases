package selenium.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    public WebDriver initializeDriver(){
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver =new ChromeDriver(options);
        driver.manage().window().maximize();
       // driver.manage().timeouts().implicitlyWait();
        return driver;
    }
}
