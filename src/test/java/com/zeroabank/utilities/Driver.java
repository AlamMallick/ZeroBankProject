package com.zeroabank.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;;
import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver() {
    }

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (driverPool.get() == null) {

            synchronized (Driver.class) {

                String browser = ConfigurationReader.getProperty("browser");
                DesiredCapabilities cap = new DesiredCapabilities();

                switch (browser) {


                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions().addArguments("--ignore-certificate-errors");
                        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        cap.setCapability(ChromeOptions.CAPABILITY, options);
                        driverPool.set(new ChromeDriver(options));
                        break;


                    case "chrome-headless":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions optionsHL = new ChromeOptions().addArguments("--ignore-certificate-errors");
                        optionsHL.setHeadless(true);
                        optionsHL.addArguments("--disable-gpu");
                        optionsHL.addArguments("- window-size = 1920,1200");
                        optionsHL.addArguments("--disable-infobars");
                        optionsHL.addArguments("--disable-notifications");
                        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        cap.setCapability(ChromeOptions.CAPABILITY, optionsHL);
                        driverPool.set(new ChromeDriver(optionsHL));
                        break;


                }

            }
        }
        driverPool.get().manage().window().maximize();
        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //This same driver will be returned every time we call Driver.getDriver() method
        return driverPool.get();

    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }


    public static WebDriver setDriver(String browser) {

        if (driverPool.get() == null) {

            synchronized (Driver.class) {
                DesiredCapabilities cap = new DesiredCapabilities();
                switch (browser) {


                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions().addArguments("--ignore-certificate-errors");
                        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        cap.setCapability(ChromeOptions.CAPABILITY, options);
                        driverPool.set(new ChromeDriver());
                        break;


                    case "chrome-headless":
                        ChromeOptions chromeOptions = new ChromeOptions();
                        WebDriverManager.chromedriver().setup();
                        chromeOptions.setHeadless(true);
                        driverPool.set(new ChromeDriver(chromeOptions));
                        break;

                }

            }
        }
        driverPool.get().manage().window().maximize();
        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //This same driver will be returned every time we call Driver.getDriver() method
        return driverPool.get();

    }


}




