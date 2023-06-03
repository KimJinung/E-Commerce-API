package kimjinung.commerce.dto.item;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemSearchRequestDto {

    @NotBlank
    private String keyword;

    public ItemSearchRequestDto() {
    }

    public ItemSearchRequestDto(String searchKeyword) {
        this.keyword = searchKeyword;
    }
}
