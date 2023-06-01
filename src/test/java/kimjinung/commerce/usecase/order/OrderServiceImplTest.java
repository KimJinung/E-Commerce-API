package kimjinung.commerce.usecase.order;

import kimjinung.commerce.dto.member.JoinMemberDto;
import kimjinung.commerce.dto.order.*;
import kimjinung.commerce.dto.product.ProductDTO;
import kimjinung.commerce.dto.product.ProductRegisterDTO;
import kimjinung.commerce.dto.product.ProductSearchDTO;
import kimjinung.commerce.usecase.member.MemberService;
import kimjinung.commerce.usecase.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceImplTest {

    String userId;

    @Autowired
    OrderService orderService;
    @Autowired
    MemberService memberService;
    @Autowired
    ProductService productService;

    @BeforeEach
    void before() {
        JoinMemberDto joinMemberDto = new JoinMemberDto(
                "testUser",
                "1234",
                "01012341234",
                "test@outlook.com",
                "서울",
                "강변북로",
                "1234"
        );

        memberService.join(joinMemberDto);
        userId = joinMemberDto.getUserId();

        ProductRegisterDTO food = new ProductRegisterDTO("Food", 100, 10);
        productService.register(food);

        ProductSearchDTO productSearchDTO = new ProductSearchDTO(List.of("Food"));
        ProductDTO productDTO = productService.search(productSearchDTO).get(0);

        HashMap<String, Integer> itemCart = new HashMap<>();
        itemCart.put(productDTO.getId(), 10);

        OrderCreateDTO orderCreateDTO = new OrderCreateDTO(userId, itemCart);
        orderService.order(orderCreateDTO);
    }
    @Test
    void testOrder() {
        HashMap<String, Integer> itemCart = new HashMap<>();

        OrderCreateDTO orderCreateDTO = new OrderCreateDTO(userId, itemCart);
        OrderCreateResultDTO order = orderService.order(orderCreateDTO);

        assertThat(order.getTotalOrderPrice()).isEqualTo(0);
    }

    @Test
    void testSearch() {
        OrderSearchDTO orderSearchDTO = new OrderSearchDTO(userId);
        List<OrderSearchResultDTO> search = orderService.search(orderSearchDTO);

        assertThat(search.size()).isEqualTo(1);
        OrderSearchResultDTO orderSearchResultDTO = search.get(0);

        assertThat(orderSearchResultDTO.getOderItem().containsKey("Food")).isTrue();
        assertThat(orderSearchResultDTO.getOderItem().get("Food")).isEqualTo(10);
        assertThat(orderSearchResultDTO.getTotalOrderPrice()).isEqualTo(1000);
    }

    @Test
    void testCancel() {
        OrderSearchDTO orderSearchDTO = new OrderSearchDTO(userId);
        List<OrderSearchResultDTO> search = orderService.search(orderSearchDTO);

        assertThat(search.size()).isEqualTo(1);
        OrderSearchResultDTO orderSearchResultDTO = search.get(0);

        assertThat(orderSearchResultDTO.getStatus()).isEqualTo("COMPLETE");

        // Cancel
        String orderId = orderSearchResultDTO.getId();
        OrderCancelDTO orderCancelDTO = new OrderCancelDTO(orderId);
        orderService.cancel(orderCancelDTO);

        // Check
        orderSearchDTO = new OrderSearchDTO(userId);
        search = orderService.search(orderSearchDTO);

        assertThat(search.size()).isEqualTo(1);
        orderSearchResultDTO = search.get(0);

        assertThat(orderSearchResultDTO.getStatus()).isEqualTo("CANCEL");
    }
}