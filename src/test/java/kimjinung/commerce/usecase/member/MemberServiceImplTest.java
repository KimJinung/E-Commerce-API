package kimjinung.commerce.usecase.member;

import kimjinung.commerce.dto.member.*;
import kimjinung.commerce.exception.MemberPasswordMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class MemberServiceImplTest {

    MemberJoinRequestDto memberJoinRequestDto;
    @Autowired
    MemberService service;

    @BeforeEach
    void before() {
        memberJoinRequestDto = new MemberJoinRequestDto(
                "kimjinung",
                "1234",
                "01012341234",
                "gmail@gmail.com",
                "city",
                "street",
                "1234"
        );

        service.join(memberJoinRequestDto);
    }

    @Test
    void testLogin() {
        MemberLoginRequestDto requestDto = new MemberLoginRequestDto("kimjinung", "1234");
        MemberLoginResponseDto responseDto = service.login(requestDto);

        assertThat(responseDto.isOk()).isTrue();
    }

    @Test
    void testLogin_InvalidPassword() {
        MemberLoginRequestDto requestDto = new MemberLoginRequestDto("kimjinung", "4321");

        assertThrows(MemberPasswordMismatchException.class,
                () -> service.login(requestDto)
        );
    }

    @Test
    void testJoin() {
        MemberJoinRequestDto joinDto = new MemberJoinRequestDto(
                "manager",
                "1234",
                "01012341234",
                "gmail@gmail.com",
                "city",
                "street",
                "1234"
        );

        MemberJoinResponseDto result = service.join(joinDto);
        assertThat(result.getUserId()).isEqualTo("manager");
        assertThat(result.getPhoneNumber()).isEqualTo("01012341234");
        assertThat(result.getEmail()).isEqualTo("gmail@gmail.com");
    }

    @Test
    void testSearch() {
        MemberSearchRequestDto searchDto = new MemberSearchRequestDto("kimjinung");
        MemberSearchResponseDto searchMember = service.search(searchDto);

        assertThat(searchMember.getUserId()).isEqualTo("kimjinung");
        assertThat(searchMember.getEmail()).isEqualTo("gmail@gmail.com");
    }

    @Test
    void testUpdate() {
        MemberUpdateRequestDto updateDto = new MemberUpdateRequestDto(
                "kimjinung",
                "4321",
                "outlook@outlook.com",
                "01143214321",
                "도시",
                "도로",
                "12"
        );

        MemberUpdateResponseDto updatedMember = service.update(updateDto);
        assertThat(updatedMember.getUserId()).isEqualTo("kimjinung");
        assertThat(updatedMember.getPhoneNumber()).isEqualTo("01143214321");
        assertThat(updatedMember.getEmail()).isEqualTo("outlook@outlook.com");
        assertThat(updatedMember.getCity()).isEqualTo("도시");
        assertThat(updatedMember.getStreet()).isEqualTo("도로");
        assertThat(updatedMember.getZipCode()).isEqualTo("12");
    }

    @Test
    void testWithdrawal() {
        MemberWithdrawalRequestDto removeDto = new MemberWithdrawalRequestDto("kimjinung", "1234");
        MemberWithdrawalResponseDto withdrawal = service.withdrawal(removeDto);
        assertThat(withdrawal.getUserId()).isEqualTo("kimjinung");
    }

}