package kimjinung.commerce.dto.order;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class OrderSearchRequestDto {

    @NotBlank
    private String userId;

    public OrderSearchRequestDto() {
    }

    public OrderSearchRequestDto(String userUuid) {
        this.userId = userUuid;
    }
}
