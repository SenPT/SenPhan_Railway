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
    @DataProvider(name = "dp")
    public Object[][] readJSon() throws IOException, ParseException
    {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(Utilities.getProjectPath()+"\\DataObjects\\userInfor.json");

        Object obj = jsonParser.parse(reader);

        JSONObject users= (JSONObject) obj;
        JSONArray userArray = (JSONArray) users.get("userlogins");

        String arr[][] = new String[userArray.size()][2];

        for(int i=0;i<userArray.size();i++)
        {
            JSONObject ticts = (JSONObject) userArray.get(i);
            String usern = ticts.get("username").toString();
            String passw = ticts.get("password").toString();

            arr[i][0] = usern;
            arr[i][1] = passw;
        }
        return arr;
    }

    @DataProvider(name = "dpticket")
    public Object[][] readJSonTicket() throws IOException, ParseException
    {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(Utilities.getProjectPath()+"\\DataObjects\\Tickets.json");

        Object obj = jsonParser.parse(reader);

        JSONObject users= (JSONObject) obj;
        JSONArray userArray = (JSONArray) users.get("tickets");

        String arr[][] = new String[userArray.size()][5];

        for(int i=0;i<userArray.size();i++)
        {
            JSONObject ticts = (JSONObject) userArray.get(i);
            String departDate = ticts.get("departDate").toString();
            String departFrom = ticts.get("departFrom").toString();
            String arriveAt = ticts.get("arriveAt").toString();
            String seatType = ticts.get("seatType").toString();
            String ticketAmount = ticts.get("ticketAmount").toString();

            arr[i][0] = departDate;
            arr[i][1] = departFrom;
            arr[i][2] = arriveAt;
            arr[i][3] = seatType;
            arr[i][4] = ticketAmount;
        }
        return arr;
    }
}
