package kimjinung.commerce.usecase.product;

import kimjinung.commerce.dto.product.*;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDTO register(ProductRegisterDTO productRegisterDTO);
    List<ProductDTO> search(ProductSearchDTO productSearchDTO);
    ProductDTO update(ProductUpdateDTO productUpdateDTO);
    boolean delete(ProductDeleteDTO productDeleteDTO);
}
