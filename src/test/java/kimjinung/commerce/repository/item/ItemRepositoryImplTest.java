package kimjinung.commerce.repository.item;

import kimjinung.commerce.Infrastructure.repository.item.ItemRepository;
import kimjinung.commerce.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class ItemRepositoryImplTest {

    Item item1;
    Item item2;
    String itemName = "myItem";
    @Autowired
    ItemRepository repository;

    @BeforeEach
    void beforeEach() {
        item1 = new Item(itemName, 1000000, 10);
        repository.save(item1);

        item2 = new Item(itemName + "2", 10, 1);
        repository.save(item2);
    }

    @Test
    void testSave() {
        Item myItem = new Item("myItem", 10, 1);
        Optional<Item> optionalItem = repository.save(myItem);
        assertThat(optionalItem).isPresent();

        Item savedItem = optionalItem.get();
        assertThat(savedItem.getName()).isEqualTo("myItem");
        assertThat(savedItem.getPrice()).isEqualTo(10);
        assertThat(savedItem.getStockQuantity()).isEqualTo(1);
    }

    @Test
    void testRemove() {
        Optional<Item> removedItem = repository.remove(item1);
        assertThat(removedItem).isEmpty();
    }

    @Test
    void testFindById() {
        Optional<Item> optionalItem = repository.findById(item1.getId());
        assertThat(optionalItem).isPresent();

        Item foundItem = optionalItem.get();
        assertThat(foundItem.getName()).isEqualTo(item1.getName());
        assertThat(foundItem.getPrice()).isEqualTo(item1.getPrice());
        assertThat(foundItem.getStockQuantity()).isEqualTo(item1.getStockQuantity());
        assertThat(foundItem.getId()).isEqualTo(item1.getId());
    }


    @Test
    void testFindByName() {
        Optional<List<Item>> optionalItem = repository.findByName(itemName);
        assertThat(optionalItem).isPresent();
    }

}