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
            shirtDropDownMenu = $x("//a[@href='/c/515/clothes-muzhskie-rubashki-i-sorochki/?sitelink=topmenuM&l=9']");


    public MainLM openPage(){
        Selenide.open("https://www.lamoda.ru");
        return new MainLM();
    }

    public FiltersLM selectSection(){
        sectionMen.should(Condition.visible, Duration.ofSeconds(30)).click();
        clothesMenu.should(Condition.visible, Duration.ofSeconds(30)).hover(); // Неустойчиво. Изредка падает
        shirtDropDownMenu.click();

        return page(FiltersLM.class);
    }
}
