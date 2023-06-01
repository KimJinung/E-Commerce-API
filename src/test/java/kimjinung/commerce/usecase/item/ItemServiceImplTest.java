package kimjinung.commerce.usecase.item;

import kimjinung.commerce.domain.Item;
import kimjinung.commerce.dto.item.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
class ItemServiceImplTest {

    @Autowired
    ItemService service;

    @BeforeEach
    void before() {
        ItemRegisterRequestDto item = new ItemRegisterRequestDto("myItem", 10, 1);
        service.register(item);
    }

    @Test
    void testRegister() {
        ItemRegisterRequestDto item = new ItemRegisterRequestDto("PC", 1010101010, 1);
        ItemRegisterResponseDto registeredItem = service.register(item);

        assertThat(registeredItem.getName()).isEqualTo("PC");
        assertThat(registeredItem.getPrice()).isEqualTo(1010101010);
        assertThat(registeredItem.getStockQuantity()).isEqualTo(1);
    }

    @Test
    void testSearch() {
        ItemSearchRequestDto searchDto = new ItemSearchRequestDto("myItem");

        List<ItemSearchResponseDto> searchItems = service.search(searchDto);

        assertThat(searchItems.size()).isEqualTo(1);
        assertThat(searchItems.get(0).getName()).isEqualTo("myItem");
    }

    @Test
    void testUpdate() {
        ItemSearchRequestDto searchDto = new ItemSearchRequestDto("myItem");
        ItemSearchResponseDto itemSearchResponseDto = service.search(searchDto).get(0);
        String id = itemSearchResponseDto.getId();
        ItemUpdateRequestDto updateDto = new ItemUpdateRequestDto(id, "updatedItem", 100, 100);

        ItemUpdateResponseDto result = service.update(updateDto);

        assertThat(result.getName()).isEqualTo("updatedItem");
        assertThat(result.getPrice()).isEqualTo(100);
        assertThat(result.getStockQuantity()).isEqualTo(100);
    }

    @Test
    void testRemove() {
        ItemSearchRequestDto searchDto = new ItemSearchRequestDto("myItem");
        ItemSearchResponseDto itemSearchResponseDto = service.search(searchDto).get(0);
        String id = itemSearchResponseDto.getId();

        ItemRemoveRequestDto itemRemoveRequestDto = new ItemRemoveRequestDto(id);
        ItemRemoveResponseDto removedItem = service.remove(itemRemoveRequestDto);

        assertThat(removedItem.getId()).isEqualTo(id);
    }

}