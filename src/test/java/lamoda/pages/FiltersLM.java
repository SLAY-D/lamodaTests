package lamoda.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class FiltersLM {

    private static SelenideElement
            filterMaterials = $x("//span[@class='_title_pjvgk_43'][contains(text(), 'Материалы')]"),
            filterSize = $x("//span[@class='_title_pjvgk_43'][contains(text(), 'Размер')]"),
            filterPrice = $x("//span[@class='_title_pjvgk_43'][contains(text(), 'Цена')]"),
            filterScrollTab = $x("//div[@class='vue-recycle-scroller ready direction-vertical _scroller_n6etx_2 _root_htwrd_2 _withOutHeader_htwrd_74']"),
            filterPriceMinRange = $x("//input[@name='minRange']"),
            filterPriceMaxRange = $x("//input[@name='maxRange']"),
            submitFiltersBtn = $x("//button[@class='x-button x-button_primaryNewFilled x-button_48 x-button_intrinsic-width _apply_1x1qp_20']");

    public FiltersLM selectFilterMaterials(String material){
        filterMaterials.should(Condition.appear, Duration.ofSeconds(30)).click();
        $x("//span[contains(text(), '" + material + "')]").scrollTo().click();
        submitFiltersBtn.click();
        return this;
    }

    public FiltersLM selectFilterSize(String size){
        SelenideElement sizeLoc = $x("//span[@class='_valueTitle_htwrd_47 _firstLetterUppercase_htwrd_52'][contains(text(), '" + size + "')]");
        filterSize.should(Condition.appear, Duration.ofSeconds(30)).click();
        executeJavaScript("arguments[0].click();", sizeLoc);
        submitFiltersBtn.click();
        return this;
    }

    public FiltersLM selectFilterPrice(Integer minRange, Integer maxRange){
        filterPrice.should(Condition.appear, Duration.ofSeconds(30)).click();
        filterPriceMinRange.click();
        filterPriceMinRange.sendKeys(minRange.toString());

        filterPriceMaxRange.click();
        filterPriceMaxRange.sendKeys(Keys.LEFT_CONTROL + "A");
        filterPriceMaxRange.sendKeys(Keys.DELETE);
        filterPriceMaxRange.shouldBe(Condition.empty).sendKeys(maxRange.toString());

        submitFiltersBtn.click();
        return this;
    }

}
