package kimjinung.commerce.dto.order;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderSearchRequestDto {

    @NotBlank
    private String userId;

    public OrderSearchRequestDto() {
    }

    public OrderSearchRequestDto(String userUuid) {
        this.userId = userUuid;
    }
}
