package kimjinung.commerce.usecase.product;

import kimjinung.commerce.Infrastructure.repository.item.ItemRepository;
import kimjinung.commerce.domain.Item;
import kimjinung.commerce.dto.product.ProductDTO;
import kimjinung.commerce.dto.product.ProductDeleteDTO;
import kimjinung.commerce.dto.product.ProductRegisterDTO;
import kimjinung.commerce.dto.product.ProductSearchDTO;
import kimjinung.commerce.exception.ProductNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Transactional
@SpringBootTest
class ProductServiceImplTest {

    ProductRegisterDTO productRegisterDTO;
    ProductSearchDTO productSearchDTO;
    UUID itemUUID;

    @Autowired
    ProductService productService;
    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void before() {
        productRegisterDTO = new ProductRegisterDTO("MacBook", 1000000, 10);

        productService.register(productRegisterDTO);

    }

    @Test
    void testSearch() {
        ProductSearchDTO productSearchDTO = new ProductSearchDTO(List.of("MacBook"));

        List<ProductDTO> result = productService.search(productSearchDTO);

        assertThat(result.size()).isEqualTo(1);
        ProductDTO productDTO = result.get(0);
        assertThat(productDTO.getName()).isEqualTo("MacBook");
        assertThat(productDTO.getPrice()).isEqualTo(1000000);
        assertThat(productDTO.getStockQuantity()).isEqualTo(10);
    }

    @Test
    void testSearch_EmptyResult() {
        ProductSearchDTO productSearchDTO = new ProductSearchDTO(List.of("foo"));

        List<ProductDTO> result = productService.search(productSearchDTO);

        assertThat(result).isEmpty();
    }

    @Test
    void testDelete() {
        Optional<List<Item>> optionalItems = itemRepository.findByName(List.of("MacBook"));

        assertThat(optionalItems).isPresent();
        List<Item> items = optionalItems.get();
        assertThat(items.size()).isEqualTo(1);
        Item item = items.get(0);
        UUID uuid = item.getUuid();

        ProductDeleteDTO productDeleteDTO = new ProductDeleteDTO(uuid.toString());
        productService.delete(productDeleteDTO);

        optionalItems = itemRepository.findByName(List.of("MacBook"));

        assertThat(optionalItems).isPresent();
        items = optionalItems.get();
        assertThat(items.size()).isEqualTo(0);
    }

    @Test
    void testDelete_ProductNotExist() {
        String dummyId = UUID.randomUUID().toString();
        ProductDeleteDTO productDeleteDTO = new ProductDeleteDTO(dummyId);

        assertThrows(ProductNotExistException.class,
                () -> productService.delete(productDeleteDTO));

    }

}