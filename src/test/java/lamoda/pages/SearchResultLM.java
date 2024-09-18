package lamoda.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchResultLM {
    private static SelenideElement
    clearFiltersBtn = $x("//a[@class='_root_aroml_2 _link_aroml_25 _underline_aroml_49 _resetlink_srk49_24 _resetlink_srk49_24']"),
    preloader = $x("//div[@class='preloader__mask-layer']");

    ElementsCollection priceBold = $$x("//span[@class='_price_1rcja_8 x-product-card-description__price-single x-product-card-description__price-WEB8507_price_bold']");
    ElementsCollection priceWithDiscount = $$x("//span[@class='_price_1rcja_8 x-product-card-description__price-new x-product-card-description__price-WEB8507_price_bold']");



    public List<Integer> getPriceBold(){
        preloader.should(Condition.disappear, Duration.ofSeconds(30));
        int sizePrice = priceBold.size();
        System.out.println(sizePrice);
        List<Integer> priceBoldList = new ArrayList<>();

        for (int i = 0; i < sizePrice; i++) {
            String correctPrice = priceBold.get(i).getText().replaceAll("[^\\d]", "");
            priceBoldList.add(Integer.parseInt(correctPrice));
        }

        return priceBoldList;
    }

    public List<Integer> getPriceDiscount(){
        preloader.should(Condition.disappear, Duration.ofSeconds(30));
        int sizePrice = priceWithDiscount.size();
        List<Integer> priceDiscountList = new ArrayList<>();

        for (int i = 0; i < sizePrice; i++) {
            String correctPrice = priceWithDiscount.get(i).getText().replaceAll("[^\\d]", "");
            priceDiscountList.add(Integer.parseInt(correctPrice));
        }
        return priceDiscountList;
    }
}
