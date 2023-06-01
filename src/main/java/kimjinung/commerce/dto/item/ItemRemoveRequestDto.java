package kimjinung.commerce.dto.item;

import lombok.Getter;

@Getter
public class ItemRemoveRequestDto {
    private String id;

    public ItemRemoveRequestDto() {
    }

    public ItemRemoveRequestDto(String id) {
        this.id = id;
    }
}
