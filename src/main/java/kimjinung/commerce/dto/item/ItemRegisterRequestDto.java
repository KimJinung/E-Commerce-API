package kimjinung.commerce.dto.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
public class ItemRegisterRequestDto {

    @NotBlank
    private String name;

    @Range(min = 100, max = 999999999)
    private int price;

    @Range(min = 1, max = 9999999)
    private int stockQuantity;

    protected ItemRegisterRequestDto() {
    }

    public ItemRegisterRequestDto(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
