package lamoda.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartScreen {
    private static SelenideElement
    totalPrice = $x("(//div[@class='_value_1oots_21'])[2]");


    ElementsCollection currentPriceBold = $$x("//span[@class='_currentPrice_1v56e_50']");
    ElementsCollection currentPriceDiscount = $$x("//span[@class='_discountPrice_1v56e_54']");

    public int getPricesItem(){
        totalPrice.should(Condition.appear, Duration.ofSeconds(30));
        List<Integer> priceBoldList = new ArrayList<>();
        List<Integer> priceDiscountList = new ArrayList<>();

        if(currentPriceBold.size() != 0){
            for (int i = 0; i < currentPriceBold.size(); i++) {
                priceBoldList.add(Integer.parseInt(currentPriceBold.get(i).getText().replaceAll("[^0-9]","")));
            }
        }

        if(currentPriceDiscount.size() != 0){
            for (int i = 0; i < currentPriceDiscount.size(); i++) {
                priceDiscountList.add(Integer.parseInt(currentPriceDiscount.get(i).getText().replaceAll("[^0-9]","")));
            }
        }

        priceBoldList.addAll(priceDiscountList);

        return priceBoldList.stream()
                .reduce(0, Integer::sum);
    }

    public int getTotalPrice(){
        return Integer.parseInt(totalPrice.getText().replaceAll("[^0-9]",""));
    }
}
