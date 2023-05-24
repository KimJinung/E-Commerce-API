package kimjinung.commerce.repository.member;

import kimjinung.commerce.domain.Member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {

    UUID save(Member member);
    Optional<Member> update(Member member);
    boolean remove(Member member);
    Optional<Member> findByUUID(UUID uuid);
    Optional<Member> findByUserId(String userId);
}

