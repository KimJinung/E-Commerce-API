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
        }
        if (findById(uuid).isEmpty()) {
            return false;
        }
        em.remove(item);
        return true;
    }

    @Override
    public Optional<Item> findById(UUID uuid) {
        Item item = em.find(Item.class, uuid);
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<List<Item>> findByName(String name) {
        TypedQuery<Item> JPQL = em.createQuery(
                "select i from Item i where i.name = :name", Item.class
        );
        List<Item> items = JPQL
                .setParameter("name", name)
                .getResultList();

        return Optional.ofNullable(items);
    }
}
