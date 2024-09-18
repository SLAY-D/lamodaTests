package funSun.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ResultsPageFS {
    private static SelenideElement
            countTours = $x("//div[@class='tour-search-content__result-quantity']/h1"),
            costMin = $x("//input[@class='range-slider__input-counter'][1]"),
            costMax = $x("//input[@class='range-slider__input-counter'][2]"),
            filterMoreWaterEnjoy = $x("(//div[@class='search-content-filter__btn'])[2]");

    private ElementsCollection listCostBtn = $$x("//div[@class='btn']");

    public ResultsPageFS openPage(){
        Selenide.open("https://fstravel.com/searchtour/country/europe/turkey?departureCityId=274286&arrivalCountryId=18803&minStartDate=2024-09-30&maxStartDate=2024-09-30&minNightsCount=7&maxNightsCount=7&adults=2&flightTypes=all&sort=recommendations_FS&priceMin=98000&priceMax=552000");
        return new ResultsPageFS();
    }

    public List<Integer> getCosts(ElementsCollection elements) {
        // Список для хранения всех чисел
        List<Integer> costs = new ArrayList<>();

        // Проходим по каждому элементу и извлекаем число
        for (SelenideElement element : elements) {
            String text = element.getText();

            // Очищаем текст, оставляем только цифры
            String numberStr = text.replaceAll("[^\\d]", ""); // Убираем все, кроме цифр

            // Преобразуем строку в число и добавляем в список
            if (!numberStr.isEmpty()) {
                costs.add(Integer.parseInt(numberStr));
            }
        }

        return costs; // Возвращаем список чисел
    }


    public ResultsPageFS waitLoad(){
        countTours.should(Condition.appear, Duration.ofSeconds(30));
        return this;
    }

    public ResultsPageFS setMaxCost(Integer maxCost){
        costMax.click();
        costMax.sendKeys(maxCost.toString());
        costMax.sendKeys(Keys.ENTER);
        return this;
    }

    public List<Integer> getAllCost(){
        List<Integer> costs = getCosts(listCostBtn);
        System.out.println("Итоговый список: " + costs);
        return costs;
    }

//    public ResultsPageFS selectWaterEnjoyFilter(){
//        filterMoreWaterEnjoy.scrollTo().click();
//        filterMoreWaterEnjoyJacuzzi.click();
//
//        return this;
//    }



}
