package kimjinung.commerce.dto.product;

import lombok.Getter;

@Getter
public class ProductUpdateDTO {
    private String id;
    private String name;
    private int price;
    private int stockQuantity;

}
