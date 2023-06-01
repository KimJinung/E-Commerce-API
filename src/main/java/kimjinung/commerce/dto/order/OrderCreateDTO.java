package kimjinung.commerce.dto.order;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class OrderCreateDTO {
    private String userId;
    private HashMap<String, Integer> itemCart;

    public OrderCreateDTO() {
    }

    public OrderCreateDTO(String userId, HashMap<String, Integer> itemCart) {
        this.userId = userId;
        this.itemCart = itemCart;
    }
}
