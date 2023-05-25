package kimjinung.commerce.repository.item;

import kimjinung.commerce.domain.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository {

    UUID save(Item item);
    boolean remove(Item item);

    void update(Item item);
    Optional<Item> findById(UUID uuid);
    Optional<List<Item>> findByName(String name);
}
