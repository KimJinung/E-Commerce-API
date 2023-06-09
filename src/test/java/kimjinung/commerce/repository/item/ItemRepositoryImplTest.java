package kimjinung.commerce.repository.item;

import kimjinung.commerce.Infrastructure.repository.item.ItemRepository;
import kimjinung.commerce.Infrastructure.repository.member.MemberRepository;
import kimjinung.commerce.domain.Address;
import kimjinung.commerce.domain.Item;
import kimjinung.commerce.domain.Member;
import org.assertj.core.api.Assertions;
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

    Member seller;
    Item item1;
    Item item2;
    String itemName = "myItem";
    @Autowired
    ItemRepository repository;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        Address address = new Address("city", "street", "1234");
        seller = new Member("jinung", "1234", "01012341234", "out@llokk.com", address);
        memberRepository.save(seller);

        item1 = new Item(seller, itemName, 1000000, 10);
        repository.save(item1);

        item2 = new Item(seller, itemName + "2", 10, 1);
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

    @Test()
    void testFindBySeller() {
        Optional<List<Item>> optionalItems = repository.findBySeller(seller);
        assertThat(optionalItems).isPresent();

        List<Item> items = optionalItems.get();
        items.forEach(item -> {
            assertThat(item.getSeller().getUserId()).isEqualTo("jinung");
                }
        );
    }
}