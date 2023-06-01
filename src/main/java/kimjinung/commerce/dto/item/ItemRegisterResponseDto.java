package kimjinung.commerce.dto.item;

import kimjinung.commerce.dto.BaseDto;
import lombok.Data;

@Data
public class ItemRegisterResponseDto extends BaseDto {
    private String name;
    private int price;
    private int stockQuantity;

    protected ItemRegisterResponseDto() {
    }

    public ItemRegisterResponseDto(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
