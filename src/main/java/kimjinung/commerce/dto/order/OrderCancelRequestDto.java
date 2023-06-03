package kimjinung.commerce.dto.order;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class OrderCancelRequestDto {

    @NotBlank
    private String id;

    public OrderCancelRequestDto() {
    }

    public OrderCancelRequestDto(String id) {
        this.id = id;
    }
}
