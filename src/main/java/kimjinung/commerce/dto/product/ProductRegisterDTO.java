package kimjinung.commerce.dto.product;

import lombok.Getter;

@Getter
public class ProductRegisterDTO {
    private String name;
    private int price;
    private int stockQuantity;

    public ProductRegisterDTO(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
