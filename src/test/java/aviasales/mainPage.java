package aviasales;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class mainPage {
    private static SelenideElement

            aviaticketLink = $x("(//a[@class='s__copbTnCU01Z8RvQlTAy3'])[1]"),
            hotelsLink = $x("(//a[@class='s__copbTnCU01Z8RvQlTAy3'])[2]"),
            shortLink = $x("(//a[@class='s__copbTnCU01Z8RvQlTAy3'])[3]"),
            favouriteLink = $x("(//a[@class='s__copbTnCU01Z8RvQlTAy3'])[4]"),

            // Элементы ввода информации о полете
            fromCity = $x("//input[@id='avia_form_origin-input']"),
            fromCityDropDown = $x("(//div[@class='s__RZXUDcSQaCxxvnYZsti5'])[1]"),
            destinationCity = $x("//input[@id='avia_form_destination-input']"),
            destinationCityDrowDown = $x("(//div[@class='s__RZXUDcSQaCxxvnYZsti5'])[1]"),
            datePickerTo = $x("//button[@data-test-id='start-date-field']"),
            datePickerBack = $x("//button[@data-test-id='end-date-field']"),
            selectMonthTo = $x("(//select[@data-test-id='select-month'])[1]"),
            selectMonthBack = $x("(//select[@data-test-id='select-month'])[2]"),
            submitBtn = $x("//button[@data-test-id='form-submit']");

    public mainPage openPage(){
        Selenide.open("https://www.aviasales.ru");
        return new mainPage();
    }

    public mainPage selectCityFrom(String city){
        fromCity.click();
        fromCity.sendKeys(Keys.DELETE);
        //fromCity.shouldBe(Condition.visible).clear();
        fromCity.sendKeys(city);
        fromCityDropDown.click();

        return this;
    }

    public mainPage selectCityTo(String city){
        //destinationCity.click();
        //destinationCity.sendKeys(Keys.DELETE);
        destinationCity.sendKeys(city);
        destinationCityDrowDown.click();
        return this;
    }


    public mainPage selectDateTo(Integer dateTo, String monthTo){
        datePickerTo.click();
        selectMonthTo.click();
        $x("(//option[text()='" + monthTo +"'])[1]").click(); // Месяц из пикера
        $x("(//div[@class='s__hozqdl8_u1owv7f58Vov s__HLWgkBC9TdsHSs4C7GgX s__xdkwycDyyPyqv99LG7RA'])[" + dateTo + "]").click();

        return this;
    }

    public mainPage selectDateBack(Integer dateBack, String monthBack){
        //datePickerBack.click();
        selectMonthBack.click();
        $x("(//option[text()='" + monthBack +"'])[1]").click(); // Месяц из пикера
        $x("(//div[@class='s__hozqdl8_u1owv7f58Vov s__HLWgkBC9TdsHSs4C7GgX s__xdkwycDyyPyqv99LG7RA'])[" + dateBack + "]").click();

        return this;
    }

    public SearchPage search(){
        submitBtn.click();
        return page(SearchPage.class);
    }




}
