package kimjinung.commerce.dto.order;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderDTO {
    private String id;
    private LocalDateTime orderDate;
    private String status; //TODO refactor to enum class

    private List<ProductDTO> orderItemList;

    private Long totalOrderPrice;
}
