package kimjinung.commerce.dto.item;

import lombok.Data;

@Data
public class ItemUpdateResponseDto {
    private String name;
    private int price;
    private int stockQuantity;

    protected ItemUpdateResponseDto() {
    }

    public ItemUpdateResponseDto(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
