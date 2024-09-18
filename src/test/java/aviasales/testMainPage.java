package aviasales;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

public class testMainPage {

    @Test
    public void selectTicket(){
        Configuration.pageLoadTimeout = 120000; // 60 секунд
        String cityFrom = "Москва";
        String cityTo = "Владивосток";

        mainPage mainPage = new mainPage();
        mainPage.openPage()
                .selectCityFrom(cityFrom)
                .selectCityTo(cityTo)
                .selectDateTo(20, "Декабрь")
                .selectDateBack(5, "Январь")
                .search();

        SearchPage searchPage = new SearchPage();
        searchPage.selectLonger(10);
    }
}
