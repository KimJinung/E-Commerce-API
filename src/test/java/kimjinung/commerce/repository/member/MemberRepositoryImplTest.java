package kimjinung.commerce.repository.member;

import kimjinung.commerce.Infrastructure.repository.member.MemberRepository;
import kimjinung.commerce.domain.Address;
import kimjinung.commerce.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
class MemberRepositoryImplTest {

    Member member;

    @Autowired
    MemberRepository repository;

    @BeforeEach
    void beforeEach() {
        Address address = new Address("City", "Street", "1234");
        member = new Member("Jinung", "0410", "010", "outlook", address);
        repository.save(member);
    }

    @Test
    void testRemoveMember() {
        boolean result = repository.remove(member);
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveMember_NotExistMember() {
        Member dummy = new Member(null, null, null, null, null);
        boolean result = repository.remove(dummy);
        assertThat(result).isFalse();
    }

    @Test
    void testFindByUUID() {
        UUID uuid = member.getUuid();
        Optional<Member> optionalMember = repository.findByUUID(uuid);
        assertThat(optionalMember).isPresent();

        Member foundMember = optionalMember.get();
        assertThat(foundMember.getUserId()).isEqualTo(member.getUserId());
        assertThat(foundMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(foundMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(foundMember.getPhoneNumber()).isEqualTo(member.getPhoneNumber());
    }

    @Test
    void testFindByUUID_NotExistMember() {
        UUID uuid = UUID.randomUUID();
        Optional<Member> optionalMember = repository.findByUUID(uuid);
        assertThat(optionalMember).isEmpty();
    }

    @Test
    void testFindByUserId() {
        String userId = member.getUserId();
        Optional<Member> optionalMember = repository.findByUserId(userId);
        assertThat(optionalMember).isPresent();

        Member foundMember = optionalMember.get();
        assertThat(foundMember.getUuid()).isEqualTo(member.getUuid());
        assertThat(foundMember.getAddress()).isEqualTo(member.getAddress());
        assertThat(foundMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(foundMember.getPhoneNumber()).isEqualTo(member.getPhoneNumber());
    }

    @Test
    void testFindByUserId_NotExistMember() {
        String dummyUserId = "foo";
        Optional<Member> optionalMember = repository.findByUserId(dummyUserId);
        assertThat(optionalMember).isEmpty();
    }

    @Test
    void testFindByEmail() {
        String email = member.getEmail();

        Optional<Member> optionalMember = repository.findByEmail(email);
        assertThat(optionalMember).isPresent();

        Member foundMember = optionalMember.get();
        assertThat(foundMember.getUuid()).isEqualTo(member.getUuid());
        assertThat(foundMember.getAddress()).isEqualTo(member.getAddress());
        assertThat(foundMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(foundMember.getPhoneNumber()).isEqualTo(member.getPhoneNumber());
    }

    @Test
    void testFindByEmail_NotFound() {
        Optional<Member> optionalMember = repository.findByEmail("foo");
        assertThat(optionalMember).isEmpty();
    }
}