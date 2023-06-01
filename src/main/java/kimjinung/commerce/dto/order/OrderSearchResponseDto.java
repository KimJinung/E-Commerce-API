package kimjinung.commerce.dto.order;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class OrderSearchResponseDto {

    private String id;
    private LocalDate orderDate;
    private String status;
    private Map<String, Integer> oderItem;
    private Long totalOrderPrice;

    public OrderSearchResponseDto() {
    }

    public OrderSearchResponseDto(String id, LocalDateTime orderDate, String status, Map<String, Integer> oderItem, Long totalOrderPrice) {
        this.id = id;
        this.orderDate = orderDate.toLocalDate();
        this.status = status;
        this.oderItem = oderItem;
        this.totalOrderPrice = totalOrderPrice;
    }
}
