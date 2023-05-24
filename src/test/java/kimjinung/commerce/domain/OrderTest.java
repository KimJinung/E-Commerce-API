package kimjinung.commerce.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {
    Member member;
    Address address;
    Order order;
    Item item;

    @BeforeEach
    void beforeEach() {
        new Address("City", "Street", "12345");
        member = new Member("Jinung Kim", "0410", "010", "outlook", address);
        order = new Order(member);
        item = new Item("MacBook", 1000000, 10);
    }

    @Test
    void testAddOrder() {
        order.addOrder(item, 1);
        order.addOrder(item, 2);

        assertThat(order.getOrders().size()).isEqualTo(2);
        order.getOrders()
                .forEach(
                        i -> assertThat(i.getItem().getName()).isEqualTo("MacBook")
                );
    }

    @Test
    void testAddOrder_NotEnoughStockException() {
        assertThrows(
                IllegalStateException.class,
                () -> order.addOrder(item, 100)
        );
    }

}