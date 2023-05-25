package kimjinung.commerce.repository.item;

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

    Item item;
    UUID uuid;
    String itemName = "MacBook";
    @Autowired
    ItemRepository repository;

    @BeforeEach
    void beforeEach() {
        item = new Item(itemName, 1000000, 10);
        uuid = repository.save(item);
    }

    @Test
    void testRemove() {
        boolean result = repository.remove(item);
        assertThat(result).isTrue();
    }

    @Test
    void testRemove_NotExist() {
        Item dummy = new Item("Dummy", 10, 1);
        boolean result = repository.remove(dummy);
        assertThat(result).isFalse();
    }

    @Test
    void testRemove_AlreadyRemoved() {
        boolean remove = repository.remove(item);
        assertThat(remove).isTrue();

        boolean result = repository.remove(item);
        assertThat(result).isFalse();
    }

    @Test
    void testFindById() {
        Optional<Item> optionalItem = repository.findById(uuid);
        assertThat(optionalItem).isPresent();

        Item foundItem = optionalItem.get();
        assertThat(foundItem.getName()).isEqualTo(itemName);
        assertThat(foundItem.getPrice()).isEqualTo(1000000);
        assertThat(foundItem.getStockQuantity()).isEqualTo(10);
    }

    @Test
    void testFindById_NotExist() {
        UUID randomUUID = UUID.randomUUID();
        Optional<Item> optionalItem = repository.findById(randomUUID);
        assertThat(optionalItem).isNotPresent();
    }

    @Test
    void testFindByName() {
        Optional<List<Item>> optionalItems = repository.findByName(itemName);
        assertThat(optionalItems).isPresent();

        List<Item> foundItems = optionalItems.get();
        assertThat(foundItems).isNotEmpty();
        assertThat(foundItems.size()).isEqualTo(1);
        foundItems.forEach(i -> {
            assertThat(i.getName()).isEqualTo(itemName);
        });
    }

    @Test
    void testFindByName_NotExist() {
        Optional<List<Item>> optionalItems = repository.findByName("Dummy");
        assertThat(optionalItems).isPresent();

        List<Item> foundItems = optionalItems.get();
        assertThat(foundItems).isEmpty();
    }

}