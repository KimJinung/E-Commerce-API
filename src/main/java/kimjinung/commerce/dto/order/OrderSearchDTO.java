package kimjinung.commerce.dto.order;

import lombok.Getter;

@Getter
public class OrderSearchDTO {
    private String userId;

    public OrderSearchDTO() {
    }

    public OrderSearchDTO(String userUuid) {
        this.userId = userUuid;
    }
}
