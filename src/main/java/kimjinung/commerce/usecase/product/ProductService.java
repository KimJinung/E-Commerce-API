package kimjinung.commerce.usecase.product;

import kimjinung.commerce.dto.product.*;

import java.util.List;

public interface ProductService {
    void register(ProductRegisterDTO productRegisterDTO);
    List<ProductDTO> search(ProductSearchDTO productSearchDTO);
    void update(ProductUpdateDTO productUpdateDTO);
    void delete(ProductDeleteDTO productDeleteDTO);
}
