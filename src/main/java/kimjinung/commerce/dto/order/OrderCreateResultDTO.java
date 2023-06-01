package kimjinung.commerce.dto.order;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class OrderCreateResultDTO {
    private String id;
    private final LocalDateTime orderDate = LocalDateTime.now();
    private String status; //TODO refactor to enum class
    private Map<String, Integer> orderItem;
    private Long totalOrderPrice;

    public OrderCreateResultDTO(String id, String status, Map<String, Integer> orderItem, Long totalOrderPrice) {
        this.id = id;
        this.status = status;
        this.orderItem = orderItem;
        this.totalOrderPrice = totalOrderPrice;
    }
}
