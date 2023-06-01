package kimjinung.commerce.dto.product;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductSearchDTO {
    private List<String> keyword;

    public ProductSearchDTO() {
    }

    public ProductSearchDTO(List<String> keyword) {
        this.keyword = keyword;
    }
}
