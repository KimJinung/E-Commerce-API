package kimjinung.commerce.dto.item;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ItemRemoveRequestDto {
    @NotBlank
    private String id;

    protected ItemRemoveRequestDto() {
    }

    public ItemRemoveRequestDto(String id) {
        this.id = id;
    }
}
