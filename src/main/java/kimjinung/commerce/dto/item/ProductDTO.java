package kimjinung.commerce.dto.item;

import lombok.Getter;

@Getter
public class ProductDTO {
    private String id;
    private String name;
    private int price;
    private int stockQuantity;

    public ProductDTO() {
    }

    public ProductDTO(String id, String name, int price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
