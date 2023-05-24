package kimjinung.commerce.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    Member member;
    Address address;

    @BeforeEach
    void beforeEach() {
        address = new Address("City", "Street", "12345");
        member = new Member("Jinung Kim", "0410", "010", "outlook", address);
    }

    @Test
    void testChangePassword() {
        member.changePassword("1234");

        assertThat(member.getPassword()).isEqualTo("1234");
    }

    @Test
    void testChangePhoneNumber() {
        member.changePhoneNumber("011");

        assertThat(member.getPhoneNumber()).isEqualTo("011");
    }

    @Test
    void testChangeEmail() {
        member.changeEmail("gmail");

        assertThat(member.getEmail()).isEqualTo("gmail");
    }

    @Test
    void testChangeAddress() {
        Address newAddress = new Address("Seoul", "Street", "4321");
        member.changeAddress(newAddress);

        assertThat(member.getAddress()).isEqualTo(newAddress);
    }
}