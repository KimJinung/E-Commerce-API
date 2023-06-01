package kimjinung.commerce.dto.order;

import lombok.Getter;

@Getter
public class OrderSearchRequestDto {
    private String userId;

    public OrderSearchRequestDto() {
    }

    public OrderSearchRequestDto(String userUuid) {
        this.userId = userUuid;
    }
}
