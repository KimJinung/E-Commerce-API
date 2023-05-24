package kimjinung.commerce.repository.item;

import kimjinung.commerce.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    void testSave() {
        Item item = new Item("Item", 10, 1);
        UUID result = repository.save(item);

        assertThat(result.getClass()).isEqualTo(UUID.class);
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
        Item item = repository.findById(uuid).orElse(null);

        assertThat(item).isNotNull();
    }

    @Test
    void testFindById_NotExist() {
        UUID randomUUID = UUID.randomUUID();
        Item result = repository.findById(randomUUID).orElse(null);

        assertThat(result).isNull();
    }

    @Test
    void testFindByName() {
        List<Item> items = repository.findByName(itemName).orElse(null);

        assertThat(items).isNotEmpty();
        assertThat(items.size()).isEqualTo(1);
        items.forEach(i -> {
            assertThat(i.getName()).isEqualTo(itemName);
        });
    }

    @Test
    void testFindByName_NotExist() {
        List<Item> items = repository.findByName("Dummy").orElse(null);
        System.out.println("items = " + items);

        assertThat(items).isEmpty();
    }

}