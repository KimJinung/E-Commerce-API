package kimjinung.commerce.repository.member;

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
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    @Override
    public UUID save(Member member) {
        em.persist(member);
        return member.getUuid();
    }

    @Override
    public boolean remove(Member member) {
        if (member.getUuid() == null) {
            return false;
        } else if(findByUUID(member.getUuid()).isEmpty()) {
            return false;
        }
        em.remove(member);
        return true;
    }

    @Override
    public Optional<Member> findByUUID(UUID uuid) {
        Member member = em.find(Member.class, uuid);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        String jpql = "SELECT m FROM Member m WHERE m.userId = :userId";
        TypedQuery<Member> query = em.createQuery(jpql, Member.class)
                .setParameter("userId", userId)
                .setMaxResults(1);

        return query.getResultList().stream().findFirst();
    }
}
