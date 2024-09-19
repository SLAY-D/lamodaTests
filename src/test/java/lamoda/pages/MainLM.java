package lamoda.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainLM {
    private static SelenideElement
            sectionMen = $x("//a[@data-genders='men']"),
            clothesMenu = $(byText("Одежда")),
            shirtDropDownMenu = $x("//a[@href='/c/515/clothes-muzhskie-rubashki-i-sorochki/?sitelink=topmenuM&l=9']"),
            cartCount = $x("//span[@class='_count_85qsb_18']"),
            cookieOkBtn = $x("//button[@class='x-button x-button_primaryFilledWeb9131 x-button_32 x-button_intrinsic-width _accept_72lhl_18']"),
            cartBtn = $x("//span[@class='_icon_1qrec_2 _icon_85qsb_8']");


    public MainLM openPage(){
        Selenide.open("https://www.lamoda.ru");
        return new MainLM();
    }

    public MainLM cookieOk(){
        cookieOkBtn.click();
        return this;
    }

    public CartScreen goToCart(){
        cartBtn.click();
        return page(CartScreen.class);
    }
    // Выбор раздела
    public FiltersLM selectSection(){
        sectionMen.should(Condition.visible, Duration.ofSeconds(30)).click();
        clothesMenu.should(Condition.visible, Duration.ofSeconds(30)).hover(); // Неустойчиво. Изредка падает
        shirtDropDownMenu.click();

        return page(FiltersLM.class);
    }


    // Получение числа на иконке корзины
    public int getCountCart(){
        return Integer.parseInt(cartCount.getText());
    }

}
