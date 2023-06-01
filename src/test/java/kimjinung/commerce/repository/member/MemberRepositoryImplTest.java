package kimjinung.commerce.repository.member;

import kimjinung.commerce.Infrastructure.repository.member.MemberRepository;
import kimjinung.commerce.domain.Address;
import kimjinung.commerce.domain.Member;
import org.junit.jupiter.api.AfterEach;
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
    Address address;
    @Autowired
    MemberRepository repository;

    @BeforeEach
    void before() {
        address = new Address("City", "Street", "1234");
        member = new Member("Jinung", "0410", "010", "outlook", address);
        repository.save(member);
    }

    @Test
    void testSave() {
        Address myAddress = new Address("city", "street", "321");
        Member james = new Member("James", "0410", "011", "gmail@gmail.com", myAddress);

        Optional<Member> optionalMember = repository.save(james);
        assertThat(optionalMember).isPresent();

        Member savedMember = optionalMember.get();
        assertThat(savedMember.getId()).isEqualTo(james.getId());
        assertThat(savedMember.getUserId()).isEqualTo(james.getUserId());
        assertThat(savedMember.getPassword()).isEqualTo(james.getPassword());
        assertThat(savedMember.getEmail()).isEqualTo(james.getEmail());
        assertThat(savedMember.getPhoneNumber()).isEqualTo(james.getPhoneNumber());
        assertThat(savedMember.getAddress()).isEqualTo(james.getAddress());
        assertThat(savedMember.getCreatedAt()).isEqualTo(james.getCreatedAt());
        assertThat(savedMember.getUpdatedAt()).isEqualTo(james.getUpdatedAt());
    }

    @Test
    void testRemove() {
        Optional<Member> optionalMember = repository.remove(member);
        assertThat(optionalMember).isEmpty();
    }

    @Test
    void testFindById() {
        Optional<Member> optionalMember = repository.findById(member.getId());
        assertThat(optionalMember).isPresent();

        Member foundMember = optionalMember.get();
        assertThat(foundMember.getId()).isEqualTo(member.getId());
        assertThat(foundMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(foundMember.getAddress()).isEqualTo(member.getAddress());
        assertThat(foundMember.getCreatedAt()).isEqualTo(member.getCreatedAt());
        assertThat(foundMember.getUserId()).isEqualTo(member.getUserId());
    }

    @Test
    void testFindByUserid() {
        Optional<Member> optionalMember = repository.findByUserId(member.getUserId());
        assertThat(optionalMember).isPresent();

        Member foundMember = optionalMember.get();
        assertThat(foundMember.getId()).isEqualTo(member.getId());
        assertThat(foundMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(foundMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(foundMember.getPhoneNumber()).isEqualTo(member.getPhoneNumber());
        assertThat(foundMember.getAddress()).isEqualTo(member.getAddress());
    }

}