package kimjinung.commerce.usecase.member;

import kimjinung.commerce.dto.member.*;
import kimjinung.commerce.exception.MemberAlreadyExistException;
import kimjinung.commerce.exception.MemberInfoNotMatchException;
import kimjinung.commerce.exception.MemberNotFoundException;
import org.junit.jupiter.api.AfterEach;
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

    JoinMemberDto joinMemberDto;
    @Autowired
    MemberService service;

    @BeforeEach
    void before() {
        joinMemberDto = new JoinMemberDto(
                "peter",
                "0410",
                "010",
                "outlook",
                "Ky",
                "Sw",
                "12"

        );
        service.join(joinMemberDto);
    }

    @AfterEach
    void after() {
        cleanUp();
    }

    void cleanUp() {
        WithdrawalMemberDto memberDto = new WithdrawalMemberDto("peter");
        service.withdrawal(memberDto);
    }

    @Test
    void testJoin_alreadyExistEmail() {
        JoinMemberDto duplicateMember = new JoinMemberDto(
                "peter",
                "0410",
                "010",
                "outlook",
                "Ky",
                "Sw",
                "12"

        );
        assertThrows(MemberAlreadyExistException.class,
                () ->  service.join(duplicateMember))
        ;
    }

    @Test
    void testSearch() {
        SearchMemberDto searchMemberDto = new SearchMemberDto("peter", "outlook");
        SearchMemberResultDto result = service.search(searchMemberDto);

        assertThat(result.getUserId()).isEqualTo("peter");
        assertThat(result.getPassword()).isEqualTo("0410");
    }

    @Test
    void testSearch_NotMatchUserIdAndEmail() {
        SearchMemberDto searchMemberDto = new SearchMemberDto("peter", "gmail");

        assertThrows(MemberInfoNotMatchException.class,
                () -> service.search(searchMemberDto));
    }

    @Test
    void testSearch_NotFound() {
        SearchMemberDto searchMemberDto = new SearchMemberDto("kim", "outlook");

        assertThrows(MemberNotFoundException.class,
                () -> service.search(searchMemberDto));
    }

    @Test
    void testUpdate() {
        UpdateMemberDto updateMemberDto = new UpdateMemberDto(
                "peter",
                "1234",
                "naver",
                "011",
                "Seoul",
                "river",
                "11"
        );

        UpdateMemberResultDto result = service.update(updateMemberDto);

        assertThat(result.getEmail()).isEqualTo(updateMemberDto.getEmail());
        assertThat(result.getPhoneNumber()).isEqualTo(updateMemberDto.getPhoneNumber());
        assertThat(result.getCity()).isEqualTo(updateMemberDto.getCity());
        assertThat(result.getStreet()).isEqualTo(updateMemberDto.getStreet());
        assertThat(result.getZipCode()).isEqualTo(updateMemberDto.getZipCode());
    }

    @Test
    void testUpdate_NotFoundUser() {
        UpdateMemberDto updateMemberDto = new UpdateMemberDto(
                "foo",
                "1234",
                "naver",
                "011",
                "Seoul",
                "river",
                "11"
        );

        assertThrows(MemberNotFoundException.class,
                () -> service.update(updateMemberDto));
    }

    @Test
    void testWithdrawalMember_NotExistMember() {
        WithdrawalMemberDto memberDto = new WithdrawalMemberDto("foo");
        assertThrows(MemberNotFoundException.class,
                () -> service.withdrawal(memberDto));
    }
}