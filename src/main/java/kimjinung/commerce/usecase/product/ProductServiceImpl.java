package kimjinung.commerce.usecase.product;

import kimjinung.commerce.Infrastructure.repository.item.ItemRepository;
import kimjinung.commerce.domain.Item;
import kimjinung.commerce.dto.product.*;
import kimjinung.commerce.exception.ProductNotExistException;
import kimjinung.commerce.exception.ProductRemoveFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    
    private final ItemRepository itemRepository;
    
    @Override
    public void register(ProductRegisterDTO productRegisterDTO) {
        String name = productRegisterDTO.getName();
        int price = productRegisterDTO.getPrice();
        int stockQuantity = productRegisterDTO.getStockQuantity();

        Item item = new Item(name, price, stockQuantity);
        itemRepository.save(item);
    }

    @Override
    public List<ProductDTO> search(ProductSearchDTO productSearchDTO) throws RuntimeException{
        List<String> keywords = productSearchDTO.getKeyword();
        Optional<List<Item>> optionalItems = itemRepository.findByName(keywords);

        if (optionalItems.isEmpty()) {
            throw new IllegalStateException();
        }
        List<Item> items = optionalItems.get();

        return items.stream()
                .map(item -> new ProductDTO(
                        item.getUuid().toString(),
                        item.getName(),
                        item.getPrice(),
                        item.getStockQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(ProductUpdateDTO productUpdateDTO) throws RuntimeException{
        UUID uuid = UUID.fromString(productUpdateDTO.getId());
        Optional<Item> optionalItem = itemRepository.findById(uuid);

        if (optionalItem.isEmpty()) {
            throw new ProductNotExistException("Not exist product");
        }
        Item item = optionalItem.get();

        String name = productUpdateDTO.getName();
        int price = productUpdateDTO.getPrice();
        int stockQuantity = productUpdateDTO.getStockQuantity();

        item.changeName(name);
        item.changePrice(price);
        item.changeStockQuantity(stockQuantity);
    }

    @Override
    public void delete(ProductDeleteDTO productDeleteDTO) throws RuntimeException{
        UUID uuid = UUID.fromString(productDeleteDTO.getId());
        Optional<Item> optionalItem = itemRepository.findById(uuid);

        if (optionalItem.isEmpty()) {
            throw new ProductNotExistException("Not exist product");
        }

        Item item = optionalItem.get();
        boolean result = itemRepository.remove(item);
        if (!result) {
            throw new ProductRemoveFailException("Fail to remove product");
        }
    }
}
