package kimjinung.commerce.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kimjinung.commerce.domain.OrderStatus;
import kimjinung.commerce.dto.order.*;
import kimjinung.commerce.usecase.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderApiController.class)
class OrderApiControllerTest {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void testOrder() throws Exception{
        HashMap<String, Integer> itemCart = new HashMap<>();
        itemCart.put("foo", 10);
        itemCart.put("bar", 20);
        OrderCreateRequestDto requestDto = new OrderCreateRequestDto("jinung", itemCart);
        OrderCreateResponseDto responseDto = new OrderCreateResponseDto("jinung", OrderStatus.COMPLETE.toString(),
                itemCart, 1000L);
        given(orderService.order(requestDto)).willReturn(responseDto);

        mockMvc.perform(post("/api/order/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.response.id").exists());
    }

    @Test
    void testOrder_InvalidRequest() throws Exception{
        mockMvc.perform(post("/api/order/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(400));
    }

    @Test
    void testSearch() throws Exception{
        OrderSearchRequestDto requestDto = new OrderSearchRequestDto("jinung");
        HashMap<String, Integer> itemCart = new HashMap<>();
        itemCart.put("foo", 100);
        itemCart.put("bar", 10);
        OrderSearchResponseDto responseDto1 = new OrderSearchResponseDto(UUID.randomUUID().toString(), LocalDateTime.now(),
                OrderStatus.COMPLETE.name(), itemCart, 10000L);
        TimeUnit.SECONDS.sleep(2);
        OrderSearchResponseDto responseDto2 = new OrderSearchResponseDto(UUID.randomUUID().toString(), LocalDateTime.now(),
                OrderStatus.COMPLETE.name(), itemCart, 20000L);
        given(orderService.search(requestDto)).willReturn(List.of(responseDto1, responseDto2));

        mockMvc.perform(get("/api/order/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.response").exists())
                .andExpect(jsonPath("$.response.length()").value(2));
    }

    @Test
    void testSearch_InvalidRequest() throws Exception{
        mockMvc.perform(get("/api/order/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(400));
    }

    @Test
    void testCancel() throws Exception{
        OrderCancelRequestDto requestDto = new OrderCancelRequestDto(UUID.randomUUID().toString());
        OrderCancelResponseDto responseDto = new OrderCancelResponseDto(OrderStatus.CANCEL.name());
        given(orderService.cancel(requestDto)).willReturn(responseDto);

        mockMvc.perform(patch("/api/order/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.response.status").exists());
    }

    @Test
    void testCancel_InvalidRequest() throws Exception{
        mockMvc.perform(patch("/api/order/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(400));
    }
}