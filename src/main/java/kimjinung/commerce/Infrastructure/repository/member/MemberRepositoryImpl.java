package kimjinung.commerce.Infrastructure.repository.member;

import kimjinung.commerce.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    @Override
    public Optional<Member> save(Member member) {
        em.persist(member);
        return findById(member.getId());
    }

    @Override
    public Optional<Member> remove(Member member) {
        em.remove(member);
        return findById(member.getId());
    }

    @Override
    public Optional<Member> findById(UUID uuid) {
        Member member = em.find(Member.class, uuid);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        String jpql = "SELECT m FROM Member m WHERE m.userId = :userId";
        List<Member> members = em.createQuery(jpql, Member.class)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getResultList();

        return members.stream().findFirst();
    }

}
