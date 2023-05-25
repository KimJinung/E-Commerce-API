package kimjinung.commerce.dto.order;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class OrderCreateDTO {
    private String userUuid;
    private HashMap<String, Integer> itemCart;
}
