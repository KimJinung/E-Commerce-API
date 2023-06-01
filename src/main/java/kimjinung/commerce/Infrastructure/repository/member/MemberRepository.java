package kimjinung.commerce.Infrastructure.repository.member;

import kimjinung.commerce.domain.Member;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {

    Optional<Member> save(Member member);
    Optional<Member> remove(Member member);
    Optional<Member> findById(UUID id);
    Optional<Member> findByUserId(String userId);
}

