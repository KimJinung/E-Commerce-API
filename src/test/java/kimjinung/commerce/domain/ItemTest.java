package kimjinung.commerce.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    Item item;

    @BeforeEach
    void beforeEach() {
        Address address = new Address("city", "street", "1234");
        Member seller = new Member("jinung", "0410", "01012341234", "out@look.com", address);
        item = new Item(seller, "MacBook", 1000000, 10);
    }

    @Test
    void testAddStock() {
        item.addStock(10);

        assertThat(item.getStockQuantity()).isEqualTo(20);
    }

    @Test
    void testReduceStock() {
        item.reduceStock(5);

        assertThat(item.getStockQuantity()).isEqualTo(5);
    }

    @Test
    void testReduceStock_NotEnoughStockException() {
        assertThrows(
                IllegalStateException.class,
                () -> item.reduceStock(20)
        );
    }

    @Test
    void testGetTotalPriceByCount() {
        int count = 91;
        Integer price = item.getPrice();
        Integer totalPriceByCount = item.getTotalPriceByCount(count);

        assertThat(totalPriceByCount).isEqualTo(price * count);
    }

}