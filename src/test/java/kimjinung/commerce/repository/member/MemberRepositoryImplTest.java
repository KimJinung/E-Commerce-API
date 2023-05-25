package kimjinung.commerce.repository.member;

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
import static org.junit.jupiter.api.Assertions.*;


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
        assertTrue(result);
    }

    @Test
    void testRemoveMember_NotExistMember() {
        Member dummy = new Member(null, null, null, null, null);
        boolean result = repository.remove(dummy);
        assertFalse(result);
    }

    @Test
    void testFindByUUID() {
        UUID uuid = member.getUuid();
        Optional<Member> optionalMember = repository.findByUUID(uuid);
        assertTrue(optionalMember.isPresent());

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
        assertFalse(optionalMember.isPresent());
    }

    @Test
    void testFindByUserId() {
        String userId = member.getUserId();
        Optional<Member> optionalMember = repository.findByUserId(userId);
        assertTrue(optionalMember.isPresent());

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
        assertFalse(optionalMember.isPresent());
    }
}