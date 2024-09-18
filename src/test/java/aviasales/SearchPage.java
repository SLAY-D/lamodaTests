package aviasales;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage {
    private static SelenideElement
        leftSliderLonger = $x("(//div[@class='rc-slider-handle rc-slider-handle-1'])[1]"),
        rightSliderLonger = $x("(//div[@class='rc-slider-handle rc-slider-handle-2'])[1]"),
        neighbourDates = $x("//div[@class='prediction-header__left-col']");

    public SearchPage selectLonger(int hoursMax){
        // Переход на другую страницу в табе
        Object[] windowHandles = WebDriverRunner.getWebDriver().getWindowHandles().toArray();
        //System.out.println(Arrays.toString(windowHandles));
        switchTo().window(((String) windowHandles[1]), Duration.ofSeconds(10));

        neighbourDates.should(Condition.appear, Duration.ofSeconds(90));
        //leftSliderLonger.should(Condition.visible, Duration.ofSeconds(90));


        // TODO: Убрать проблему с ранним присвоением
        int maxValue = Integer.parseInt(rightSliderLonger.getAttribute("aria-valuemax"));
        System.out.println(maxValue);
        int expectedValue;
        if(maxValue>1000){
            expectedValue = 60*hoursMax;
        }
        else{
            expectedValue = hoursMax;
        }

        System.out.println(expectedValue);


//        int expectedValue = hoursMax;
//        System.out.println(expectedValue);
//
//        int currentValue = Integer.parseInt(rightSliderLonger.getAttribute("aria-valuenow"));
//        System.out.println("current: " + currentValue);

        while(Integer.parseInt(rightSliderLonger.getAttribute("aria-valuenow")) != expectedValue ){
            rightSliderLonger.sendKeys(Keys.ARROW_LEFT);
        }


//        if (expectedValue < currentValue){
//            int valueToMove = (currentValue - expectedValue)*60;
//            System.out.println(valueToMove);
//            for (int i = 0; i < valueToMove; i++) {
//                rightSliderLonger.sendKeys(Keys.ARROW_LEFT);
//                System.out.println(i);
//            }
//        }
//        else{
//            int valueToMove = expectedValue - currentValue;
//            for (int i = 0; i < valueToMove; i++) {
//                leftSliderLonger.sendKeys(Keys.ARROW_RIGHT);
//            }
//        }


        return this;
    }

}
