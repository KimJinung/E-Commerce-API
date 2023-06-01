package kimjinung.commerce.dto.product;

import lombok.Getter;

@Getter
public class ProductDeleteDTO {
    private String id;

    public ProductDeleteDTO() {
    }

    public ProductDeleteDTO(String id) {
        this.id = id;
    }
}
