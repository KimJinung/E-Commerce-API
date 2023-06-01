package kimjinung.commerce.dto.item;

import kimjinung.commerce.dto.BaseDto;
import lombok.Data;

@Data
public class ItemUpdateRequestDto extends BaseDto {
    private String id;
    private String name;
    private int price;
    private int stockQuantity;

}
