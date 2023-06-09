package kimjinung.commerce.Infrastructure.repository.item;

import kimjinung.commerce.domain.Item;
import kimjinung.commerce.domain.Member;
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
    public Optional<Item> save(Item item) {
        em.persist(item);
        return findById(item.getId());
    }

    @Override
    public Optional<Item> remove(Item item) {
        em.remove(item);
        return findById(item.getId());
    }

    @Override
    public Optional<Item> findById(UUID id) {
        Item item = em.find(Item.class, id);
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

    @Override
    public Optional<List<Item>> findBySeller(Member member) {
        String jpql = "SELECT i FROM Item i WHERE i.seller = :seller";
        TypedQuery<Item> query = em.createQuery(jpql, Item.class)
                .setParameter("seller", member);

        List<Item> items = query.getResultList();
        return Optional.ofNullable(items);
    }
}
