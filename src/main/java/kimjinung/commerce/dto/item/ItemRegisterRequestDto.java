package kimjinung.commerce.dto.item;

import lombok.Data;

@Data
public class ItemRegisterRequestDto {
    private String name;
    private int price;
    private int stockQuantity;

    protected ItemRegisterRequestDto() {
    }

    public ItemRegisterRequestDto(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
