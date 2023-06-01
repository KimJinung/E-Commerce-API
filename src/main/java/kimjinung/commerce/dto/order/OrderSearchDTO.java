package kimjinung.commerce.dto.order;

import lombok.Getter;

@Getter
public class OrderSearchDTO {
    private String userUuid;

    public OrderSearchDTO() {
    }

    public OrderSearchDTO(String userUuid) {
        this.userUuid = userUuid;
    }
}
