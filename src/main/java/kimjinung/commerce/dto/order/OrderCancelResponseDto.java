package kimjinung.commerce.dto.order;

import lombok.Data;

@Data
public class OrderCancelResponseDto {
    private String status;

    protected OrderCancelResponseDto() {
    }

    public OrderCancelResponseDto(String status) {
        this.status = status;
    }
}
