package funSun.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPageFS {
    private static SelenideElement
            cityDeparture = $x("//input[@class='departure-city-field__pinput_input']"),
            cityDepartureField = $x("//div[@class='departure-city-field__pinput']"),
            cityDeparturePopUp = $x("(//div[@class='departure-city-popup__body_el v-text-16'])[1]"),
            cityArrival = $x("//input[@class='arrival-country-field__pinput_input']"),
            cityArrivalPopUp = $x("(//div[@class='arrival-country-popup__body_el v-text-16'])[1]"),
            nextMonthBtn = $x("//button[@class='calendar-popup__body_month-header-arrow-right']"),
            //dayCalendarDeparture = ,
            chooseBtn = $x("//div[@class='calendar-popup__footer-button-choose']"),
            selectDayFrom = $(byText("1")),
            selectDayTo = $(byText("7")),
            searchBtn = $x("//button[@class='v-btn-yellow tour-search__button h-64']"),
            modalWindow = $x("//div[@class='popmechanic-close']");
            //modalFrame = $x("//iframe[@id='fl-778465']");


    public MainPageFS openPage(){
        Selenide.open("https://fstravel.com/");
        return new MainPageFS();
    }

    public MainPageFS selectCityDeparture(String city){
        //cityDeparture.should(Condition.visible, Duration.ofSeconds(30));
        switchTo().defaultContent();
        cityDeparture.shouldBe(Condition.visible, Condition.enabled).click();
        cityDeparture.clear();
        cityDeparture.sendKeys(city);
        //cityDeparturePopUp.click();
        return this;
    }

    public MainPageFS selectCityArrival(String city){
        cityArrival.shouldBe(Condition.visible, Condition.enabled).click();
        cityArrival.clear();
        cityArrival.sendKeys(city);
        //cityArrival.sendKeys(Keys.ENTER);
        //cityArrivalPopUp.click();
        return this;
    }

    public MainPageFS selectDate(int day){
        nextMonthBtn.click();
        $x("(//span[text()='" + day + "'])[1]").click();
        chooseBtn.click();
        return this;
    }

    public MainPageFS selectDuration(){
        selectDayFrom.click();
        selectDayTo.click();
        return this;
    }

    public ResultsPageFS searchTours(){
        searchBtn.click();
        return page(ResultsPageFS.class);
    }

    public MainPageFS closeModal(){
        modalWindow.should(Condition.appear, Duration.ofSeconds(60)).click();
        switchTo().defaultContent();
        return this;
    }

    public MainPageFS waitForPage(){
        switchTo().defaultContent();
        cityDeparture.should(Condition.matchText("Москва"));
        return this;
    }
}
