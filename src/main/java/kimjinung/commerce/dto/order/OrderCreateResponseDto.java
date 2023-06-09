package kimjinung.commerce.dto.order;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class OrderCreateResponseDto {
    private String id;
    private final LocalDateTime orderDate = LocalDateTime.now();
    private String status; //TODO refactor to enum class
    private Map<String, Integer> orderItem;
    private Long totalOrderPrice;

    public OrderCreateResponseDto(String id, String status, Map<String, Integer> orderItem, Long totalOrderPrice) {
        this.id = id;
        this.status = status;
        this.orderItem = orderItem;
        this.totalOrderPrice = totalOrderPrice;
    }
}
