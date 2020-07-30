package Railway;

import Common.Utilities;
import Constant.Constant;
import au.com.bytecode.opencsv.CSVReader;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class BaseTest {
    @Parameters({"browser"})
    @BeforeMethod
    public void beforeMethod(@Optional String browser){
        System.out.println("Pre-condition");

        if(browser.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath()+ "\\Executables\\chromedriver_v84.exe");
            Constant.WEBDRIVER = new ChromeDriver();
        }
        else if(browser.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver",Utilities.getProjectPath()+"\\Executables\\geckodriver_v24.exe");
            Constant.WEBDRIVER = new FirefoxDriver();
        }
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Post-condition");

        Constant.WEBDRIVER.quit();
    }

    public String bookDate()
    {
        Calendar rightNow = Calendar.getInstance();

        String month = 1 + rightNow.get(Calendar.MONTH)+ "";
        String date = rightNow.get(Calendar.DATE)+"";
        String year = rightNow.get(Calendar.YEAR)+"";

        String bookDate = month+"/"+date+"/"+year;

        return bookDate;
    }

    public String expiredDay()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE,3);

        String expDate = sdf.format(c.getTime());

        String month = 1+ c.get(Calendar.MONTH)+"";
        String dt = c.get(Calendar.DATE)+"";
        String year = c.get(Calendar.YEAR)+"";
        String expiredDate = month + "/"+ dt+ "/"+ year;

        return expiredDate;
    }
}
