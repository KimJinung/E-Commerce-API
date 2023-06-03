package kimjinung.commerce.dto.item;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ItemRemoveRequestDto {
    @NotBlank
    private String id;

    public ItemRemoveRequestDto() {
    }

    public ItemRemoveRequestDto(String id) {
        this.id = id;
    }
}
