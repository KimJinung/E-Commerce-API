package kimjinung.commerce.dto.item;

import lombok.Data;

@Data
public class ItemRegisterResponseDto {
    private String id;
    private String sellerId;
    private String name;
    private int price;
    private int stockQuantity;

    protected ItemRegisterResponseDto() {
    }

    public ItemRegisterResponseDto(
            String id,
            String sellerId,
            String name,
            int price,
            int stockQuantity) {
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
