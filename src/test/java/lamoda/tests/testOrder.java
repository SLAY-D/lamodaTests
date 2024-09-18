package lamoda.tests;

import lamoda.pages.FiltersLM;
import lamoda.pages.MainLM;
import lamoda.pages.SearchResultLM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Filter;
import java.util.stream.Collectors;

public class testOrder {

    @Test
    public void testAddToCart(){
        MainLM mainLM = new MainLM();
        FiltersLM filtersLM = new FiltersLM();
        String material = "Хлопок";
        String size = "60";

        mainLM.openPage()
                .selectSection();

        filtersLM
                //.selectFilterMaterials(material)
                //.selectFilterSize(size)
                .selectFilterPrice(2000, 6000);

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
}
