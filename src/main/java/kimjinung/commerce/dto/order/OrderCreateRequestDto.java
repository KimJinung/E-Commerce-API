package kimjinung.commerce.dto.order;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class OrderCreateRequestDto {
    private String userId;
    private HashMap<String, Integer> itemCart;

    public OrderCreateRequestDto() {
    }

    public OrderCreateRequestDto(String userId, HashMap<String, Integer> itemCart) {
        this.userId = userId;
        this.itemCart = itemCart;
    }
}
