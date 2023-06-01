package kimjinung.commerce.dto.item;

import lombok.Data;

@Data
public class ItemSearchRequestDto {
    private String keyword;

    public ItemSearchRequestDto() {
    }

    public ItemSearchRequestDto(String searchKeyword) {
        this.keyword = searchKeyword;
    }
}
