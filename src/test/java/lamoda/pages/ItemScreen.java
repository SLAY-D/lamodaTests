package lamoda.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ItemScreen {
    private static SelenideElement
            selectSizeBtn = $x("//div[@class='_sizeValue_8karg_281']"),
            sizeItem = $x("(//div[@class='_colspanMain_8karg_182'])[1]"),
            addToCartBtn = $x("//button[@aria-label='Добавить в корзину']"),
            closeModalBtn = $x("//div[@title='Закрыть']");


    public ItemScreen selectSize(){
        closeModalBtn.should(Condition.appear, Duration.ofSeconds(30)).click();
        selectSizeBtn.click();
        sizeItem.click();
        return this;
    }

    public ItemScreen addToCart(){
        addToCartBtn.click();
        closeModalBtn.click();
        return this;
    }



}
