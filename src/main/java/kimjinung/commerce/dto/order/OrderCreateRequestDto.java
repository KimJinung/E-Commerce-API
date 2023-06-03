package kimjinung.commerce.dto.order;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

@Getter
public class OrderCreateRequestDto {

    @NotBlank
    private String userId;

    @NotEmpty
    private HashMap<String, Integer> itemCart;

    public OrderCreateRequestDto() {
    }

    public OrderCreateRequestDto(String userId, HashMap<String, Integer> itemCart) {
        this.userId = userId;
        this.itemCart = itemCart;
    }
}
