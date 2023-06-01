package kimjinung.commerce.usecase.item;

import kimjinung.commerce.dto.item.*;

import java.util.List;

public interface ItemService {
    ItemRegisterResponseDto register(ItemRegisterRequestDto itemRegisterRequestDto);
    List<ItemSearchResponseDto> search(ItemSearchRequestDto itemSearchRequestDto);
    ItemUpdateResponseDto update(ItemUpdateRequestDto itemUpdateRequestDto);
    ItemRemoveResponseDto remove(ItemRemoveRequestDto itemRemoveRequestDto);
}
