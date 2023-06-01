package kimjinung.commerce.dto.item;

import kimjinung.commerce.dto.common.BaseDto;
import lombok.Data;

@Data
public class ItemRegisterResponseDto {
    private String id;
    private String name;
    private int price;
    private int stockQuantity;

    protected ItemRegisterResponseDto() {
    }

    public ItemRegisterResponseDto(String id, String name, int price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
