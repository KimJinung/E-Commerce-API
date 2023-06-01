package kimjinung.commerce.dto.order;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class OrderSearchResultDTO {

    private String id;
    private LocalDate orderDate;
    private Map<String, Integer> oderItem;
    private Long totalOrderPrice;

    public OrderSearchResultDTO() {
    }

    public OrderSearchResultDTO(String id, LocalDateTime orderDate, Map<String, Integer> oderItem, Long totalOrderPrice) {
        this.id = id;
        this.orderDate = orderDate.toLocalDate();
        this.oderItem = oderItem;
        this.totalOrderPrice = totalOrderPrice;
    }
}
