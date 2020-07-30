package Railway;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class Myticket extends GeneralPage{
    public void cancelTicket(String departFrom, String arriveAt){

        JavascriptExecutor jse = (JavascriptExecutor) Constant.WEBDRIVER;
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");

        Constant.WEBDRIVER.findElement(By.xpath("//td[text()='"+ departFrom +"']//following-sibling::td[text()='"+ arriveAt + "']//following-sibling::td//input")).click();
    }
}
