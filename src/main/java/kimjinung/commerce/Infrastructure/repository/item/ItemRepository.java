package kimjinung.commerce.Infrastructure.repository.item;

import kimjinung.commerce.domain.Item;
import kimjinung.commerce.domain.Member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository {

    Optional<Item> save(Item item);
    Optional<Item> remove(Item item);
    Optional<Item> findById(UUID id);
    Optional<List<Item>> findByName(String name);
    Optional<List<Item>> findBySeller(Member member);
}
