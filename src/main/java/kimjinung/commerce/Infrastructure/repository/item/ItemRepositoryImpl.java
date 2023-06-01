package kimjinung.commerce.Infrastructure.repository.item;

import kimjinung.commerce.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
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
    public Optional<Item> findById(UUID uuid) {
        Item item = em.find(Item.class, uuid);
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<List<Item>> findByName(List<String> name) {
        String jpql = "SELECT i FROM Item i WHERE i.name IN :name";
        TypedQuery<Item> query = em.createQuery(jpql, Item.class)
                .setParameter("name", name);

        List<Item> itemList = query.getResultList();
        return Optional.ofNullable(itemList);
    }

    @Override
    public Optional<List<Item>> findByIds(List<UUID> uuids) {
        String jpql = "SELECT i FROM Item i WHERE i.uuid IN :uuids";
        TypedQuery<Item> query = em.createQuery(jpql, Item.class)
                .setParameter("uuids", uuids);
        List<Item> itemList = query.getResultList();
        return Optional.ofNullable(itemList);
    }
}
