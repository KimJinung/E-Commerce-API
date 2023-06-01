package kimjinung.commerce.dto.order;

import lombok.Getter;

@Getter
public class OrderCancelRequestDto {
    private String id;

    public OrderCancelRequestDto() {
    }

    public OrderCancelRequestDto(String id) {
        this.id = id;
    }
}
