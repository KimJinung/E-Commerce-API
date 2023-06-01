package kimjinung.commerce.dto.item;

import lombok.Data;

@Data
public class ItemRemoveResponseDto {
    private String id;
    private String name;

    protected ItemRemoveResponseDto() {
    }

    public ItemRemoveResponseDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
