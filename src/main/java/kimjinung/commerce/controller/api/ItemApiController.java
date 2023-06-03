package kimjinung.commerce.controller.api;

import kimjinung.commerce.dto.common.ResponseDto;
import kimjinung.commerce.dto.item.*;
import kimjinung.commerce.usecase.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/item")
@RestController
public class ItemApiController extends BaseApiController{

    private final ItemService itemService;

    @PostMapping("/register")
    public ResponseDto<?> register(
            @RequestBody @Validated ItemRegisterRequestDto itemRegisterRequestDto,
            BindingResult bindingResult
    ) {
        validateRequest(bindingResult);
        ItemRegisterResponseDto registeredItem = itemService.register(itemRegisterRequestDto);
        return new ResponseDto<>(200, registeredItem);
    }

    @GetMapping("/search")
    public ResponseDto<?> search(
            @RequestBody @Validated ItemSearchRequestDto itemSearchRequestDto,
            BindingResult bindingResult
    ) {
        validateRequest(bindingResult);
        List<ItemSearchResponseDto> searchedItems = itemService.search(itemSearchRequestDto);
        return new ResponseDto<>(200, searchedItems);
    }

    @PatchMapping("/update")
    public ResponseDto<?> update(
            @RequestBody @Validated ItemUpdateRequestDto itemUpdateRequestDto,
            BindingResult bindingResult
    ) {
        validateRequest(bindingResult);
        ItemUpdateResponseDto updatedItem = itemService.update(itemUpdateRequestDto);
        return new ResponseDto<>(200, updatedItem);
    }

    @DeleteMapping("/delete")
    public ResponseDto<?> remove(
        @RequestBody @Validated ItemRemoveRequestDto itemRemoveRequestDto,
        BindingResult bindingResult
    ) {
        validateRequest(bindingResult);
        ItemRemoveResponseDto removedItem = itemService.remove(itemRemoveRequestDto);
        return new ResponseDto<>(200, removedItem);
    }

}
