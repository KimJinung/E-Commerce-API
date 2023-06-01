package kimjinung.commerce.dto.order;

import lombok.Getter;

@Getter
public class OrderCancelDTO {
    private String id;

    public OrderCancelDTO() {
    }

    public OrderCancelDTO(String id) {
        this.id = id;
    }
}
