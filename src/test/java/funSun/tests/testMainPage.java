package funSun.tests;

import funSun.pages.MainPageFS;
import org.junit.jupiter.api.Test;


public class testMainPage {

//    @BeforeEach
//    public void initOptions(){
//        ChromeOptions options = new ChromeOptions();
//        // Отключение всплывающих окон
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--disable-notifications");
//        Configuration.browserCapabilities = options;
//        Configuration.pageLoadTimeout = 120000;
//    }

    // Инициация поиска тура
    @Test
    public void testSearch(){
        MainPageFS mainPageFS = new MainPageFS();
        mainPageFS.openPage()
                .selectCityDeparture("Новосибирск")
                .selectCityArrival("Анталья")
                .selectDate(20)
                //.selectDuration()
                .searchTours();

    }


//    // Поиск туров по установленному фильтру с максимальной стоимостью. Сравнение стоимости выданных результатов
//    @Test
//    public void checkFilterMaxCost(){
//        int maxCost = 140000;
//
//        ResultsPageFS resultsPageFS = new ResultsPageFS();
//        resultsPageFS.openPage()
//                .waitLoad()
//                .setMaxCost(maxCost);
//
//        List<Integer> costs = resultsPageFS.getAllCost();
//        for (int i = 0; i < costs.size(); i++) {
//            Assertions.assertTrue(costs.get(i) <= maxCost);
//        }
//    }


}
