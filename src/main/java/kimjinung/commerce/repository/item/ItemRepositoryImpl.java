package kimjinung.commerce.repository.item;

import kimjinung.commerce.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final EntityManager em;

    @Override
    public UUID save(Item item) {
        em.persist(item);
        return item.getUuid();
    }

    @Override
    public boolean remove(Item item) {
        UUID uuid = item.getUuid();

        if (uuid == null) {
            return false;
        } else if(findById(uuid).isEmpty()) {
            return false;
        }
        em.remove(item);
        return true;
    }

    @Override
    public void update(Item item) throws IllegalArgumentException{
        UUID uuid = item.getUuid();
        Item foundItem = findById(uuid).orElseThrow(
                () -> new IllegalArgumentException("Not exist item")
        );

        foundItem.changeName(item.getName());
        foundItem.changePrice(item.getPrice());
    }

    @Override
    public Optional<Item> findById(UUID uuid) {
        Item item = em.find(Item.class, uuid);
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<List<Item>> findByName(String name) {
        String jpql = "SELECT i FROM Item i WHERE i.name = :name";
        TypedQuery<Item> query = em.createQuery(jpql, Item.class)
                .setParameter("name", name);

        List<Item> itemList = query.getResultList();
        return Optional.ofNullable(itemList);
    }
}
