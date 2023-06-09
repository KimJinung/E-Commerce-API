package kimjinung.commerce.dto.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemUpdateRequestDto {

    @NotNull
    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @Range(min = 100, max = 999999999)
    private int price;

    @Range(min = 1, max = 9999999)
    private int stockQuantity;

    protected ItemUpdateRequestDto() {
    }

    public ItemUpdateRequestDto(String id, String name, int price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
