package lamoda.tests;

import com.codeborne.selenide.Configuration;
import lamoda.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Filter;
import java.util.stream.Collectors;

public class testOrder {

    // Добавление товара в корзину
    @Test
    public void testAddToCart(){
        MainLM mainLM = new MainLM();
        SearchResultLM searchResultLM = new SearchResultLM();
        ItemScreen itemScreen = new ItemScreen();
        FiltersLM filtersLM = new FiltersLM();
        String material = "Хлопок";
        String size = "60";
        int countCartExpected = 1;

        mainLM.openPage()
                .cookieOk()
                .selectSection();

//        filtersLM
//                .selectFilterMaterials(material)
//                .selectFilterSize(size)
//                .selectFilterPrice(2000, 6000);

        searchResultLM.goToItemScreen();
        itemScreen.selectSize()
                .addToCart();


        Assertions.assertEquals(countCartExpected, mainLM.getCountCart());
    }

    // Проверка работоспособности фильтра цены
    @Test
    public void checkFilterPrice(){
        MainLM mainLM = new MainLM();
        FiltersLM filtersLM = new FiltersLM();

        mainLM.openPage().selectSection();
        filtersLM.selectFilterPrice(2000, 6000);

        SearchResultLM searchResultLM = new SearchResultLM();

        List<Integer> discountPrice = searchResultLM.getPriceDiscount();
        List<Integer> boldPrice = searchResultLM.getPriceBold();
        System.out.println("list: " + boldPrice);
        System.out.println("list: " + discountPrice);
        boolean allInRangeBoldPrice = boldPrice.stream().allMatch(price -> price >= 2000 && price <= 6000);
        boolean allInRangeDiscountPrice = discountPrice.stream().allMatch(price -> price >= 2000 && price <= 6000);

        if (!allInRangeBoldPrice) {
            boldPrice.forEach(price -> {
                if (price < 2000 || price > 6000) {
                    System.out.println("Цена вне диапазона: " + price);
                }
            });
        }

        if (!allInRangeDiscountPrice) {
            discountPrice.forEach(price -> {
                if (price < 2000 || price > 6000) {
                    System.out.println("Цена вне диапазона: " + price);
                }
            });
        }

        int paginationCount = 60;
        Assertions.assertTrue(allInRangeBoldPrice, "Не все цены без скидок в диапазоне от 2000 до 6000");
        Assertions.assertTrue(allInRangeDiscountPrice, "Не все цены со скидками в диапазоне от 2000 до 6000");
        Assertions.assertEquals(paginationCount,discountPrice.size() + boldPrice.size());
    }

    // Проверка на совпадение пути
    @Test
    public void checkPath(){
        Configuration.timeout = 5000;
        MainLM mainLM = new MainLM();
        mainLM.openPage().selectSection();

        SearchResultLM searchResultLM = new SearchResultLM();
        List<String> pathExpected = new ArrayList<>(Arrays.asList("Главная", "Мужчинам", "Одежда", "Рубашки"));
        List<String> pathActual = searchResultLM.getPath();
        Assertions.assertEquals(pathExpected, pathActual);

    }

    @Test
    public void checkCartPrice(){
        CartScreen cartScreen = new CartScreen();
        MainLM mainLM = new MainLM();
        SearchResultLM searchResultLM = new SearchResultLM();
        ItemScreen itemScreen = new ItemScreen();

        mainLM.openPage()
                .cookieOk()
                .selectSection();

        searchResultLM.goToItemScreen();
        itemScreen.selectSize()
                .addToCart();

        mainLM.goToCart();

        int expectedPrice = cartScreen.getPricesItem();
        int actualPrice = cartScreen.getTotalPrice();
        Assertions.assertEquals(expectedPrice, actualPrice);

    }

}
