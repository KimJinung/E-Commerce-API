package kimjinung.commerce.usecase.item;

import kimjinung.commerce.Infrastructure.repository.item.ItemRepository;
import kimjinung.commerce.domain.Item;
import kimjinung.commerce.dto.item.*;
import kimjinung.commerce.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    
    private final ItemRepository itemRepository;
    
    @Override
    public ItemRegisterResponseDto register(ItemRegisterRequestDto itemRegisterRequestDto) throws RuntimeException {

        Item item = createItem(itemRegisterRequestDto);
        Optional<Item> optionalItem = itemRepository.save(item);

        if (optionalItem.isEmpty()) {
            throw new ItemRegisterFailException();
        }
        Item savedItem = optionalItem.get();

        return new ItemRegisterResponseDto(
                savedItem.getId().toString(),
                savedItem.getName(),
                savedItem.getPrice(),
                savedItem.getStockQuantity()
        );
    }

    @Override
    public List<ItemSearchResponseDto> search(ItemSearchRequestDto itemSearchRequestDto) throws RuntimeException{

        String keyword = itemSearchRequestDto.getKeyword();
        Optional<List<Item>> optionalItems = itemRepository.findByName(keyword);

        if (optionalItems.isEmpty()) {
            throw new ItemSearchFailException();
        }
        List<Item> items = optionalItems.get();

        return items.stream()
                .map(item -> new ItemSearchResponseDto(
                        item.getId().toString(), item.getName(), item.getPrice(), item.getStockQuantity())
                )
                .collect(Collectors.toList());
    }

    @Override
    public ItemUpdateResponseDto update(ItemUpdateRequestDto itemUpdateRequestDto) throws RuntimeException {
        UUID id = UUID.fromString(itemUpdateRequestDto.getId());
        Optional<Item> optionalItem = itemRepository.findById(id);

        if (optionalItem.isEmpty()) {
            throw new ItemNotExistException();
        }
        Item item = optionalItem.get();

        updateItem(item, itemUpdateRequestDto);

        Optional<Item> optionalUpdatedItem = itemRepository.findById(item.getId());
        if (optionalUpdatedItem.isEmpty()) {
            throw new ItemUpdateFailException();
        }
        Item upadtedItem = optionalUpdatedItem.get();

        return new ItemUpdateResponseDto(
                upadtedItem.getName(),
                upadtedItem.getPrice(),
                upadtedItem.getStockQuantity()
        );
    }

    @Override
    public ItemRemoveResponseDto remove(ItemRemoveRequestDto itemRemoveRequestDto) throws RuntimeException{
        UUID id = UUID.fromString(itemRemoveRequestDto.getId());
        Optional<Item> optionalItem = itemRepository.findById(id);

        if (optionalItem.isEmpty()) {
            throw new ItemNotExistException();
        }
        Item item = optionalItem.get();
        String name = item.getName();

        Optional<Item> removedItem = itemRepository.remove(item);
        if (removedItem.isPresent()) {
            throw new ProductRemoveFailException("Fail to remove product");
        }
        return new ItemRemoveResponseDto(id.toString(), name);
    }

    private void updateItem(Item item, ItemUpdateRequestDto itemUpdateRequestDto) {
        item.changeName(itemUpdateRequestDto.getName());
        item.changePrice(itemUpdateRequestDto.getPrice());
        item.changeStockQuantity(itemUpdateRequestDto.getStockQuantity());
    }

    private Item createItem(ItemRegisterRequestDto itemRegisterRequestDto) {
        String name = itemRegisterRequestDto.getName();
        int price = itemRegisterRequestDto.getPrice();
        int stockQuantity = itemRegisterRequestDto.getStockQuantity();

        return new Item(name, price, stockQuantity);
    }
}
