package kimjinung.commerce.usecase.order;

import kimjinung.commerce.domain.OrderStatus;
import kimjinung.commerce.dto.item.ItemRegisterResponseDto;
import kimjinung.commerce.dto.member.MemberJoinRequestDto;
import kimjinung.commerce.dto.order.*;
import kimjinung.commerce.dto.item.ItemRegisterRequestDto;
import kimjinung.commerce.usecase.member.MemberService;
import kimjinung.commerce.usecase.item.ItemService;
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

    String userId = "kim";
    String itemId;

    @Autowired
    OrderService orderService;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;

    @BeforeEach
    void testBefore() {
        saveMember();
        saveItem();

        HashMap<String, Integer> itemCart = new HashMap<>();
        itemCart.put(itemId, 5);

        OrderCreateRequestDto orderCreateRequestDto = new OrderCreateRequestDto(userId, itemCart);
        orderService.order(orderCreateRequestDto);
    }

    private void saveMember() {
        MemberJoinRequestDto dto = new MemberJoinRequestDto(
                userId, "1234", "01012341234", "gmail@gmail.com", "city", "sttt", "1234"
        );
        memberService.join(dto);
    }

    private void saveItem() {
        ItemRegisterRequestDto dto = new ItemRegisterRequestDto("foo", 10000, 20);
        ItemRegisterResponseDto register = itemService.register(dto);
        itemId = register.getId();
    }

    @Test
    void testOrder() {
        HashMap<String, Integer> itemCart = new HashMap<>();
        itemCart.put(itemId, 10);

        OrderCreateRequestDto orderCreateRequestDto = new OrderCreateRequestDto(userId, itemCart);
        OrderCreateResponseDto result = orderService.order(orderCreateRequestDto);

        assertThat(result.getOrderItem().size()).isEqualTo(1);
        assertThat(result.getStatus()).isEqualTo(OrderStatus.COMPLETE.toString());
        assertThat(result.getTotalOrderPrice()).isEqualTo(100000);
    }

    @Test
    void testSearch() {
        OrderSearchRequestDto orderSearchRequestDto = new OrderSearchRequestDto(userId);
        List<OrderSearchResponseDto> result = orderService.search(orderSearchRequestDto);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getOderItem().size()).isEqualTo(1);
        assertThat(result.get(0).getOderItem().containsKey("foo")).isTrue();
        assertThat(result.get(0).getOderItem().get("foo")).isEqualTo(5);
    }

    @Test
    void testCancel() {
        OrderSearchRequestDto orderSearchRequestDto = new OrderSearchRequestDto(userId);
        List<OrderSearchResponseDto> orders = orderService.search(orderSearchRequestDto);
        String id = orders.get(0).getId();

        OrderCancelRequestDto requestDto = new OrderCancelRequestDto(id);
        OrderCancelResponseDto result = orderService.cancel(requestDto);

        assertThat(result.getStatus()).isEqualTo("ok");
    }

}