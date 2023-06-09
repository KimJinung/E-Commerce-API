package kimjinung.commerce.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kimjinung.commerce.dto.member.*;
import kimjinung.commerce.usecase.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberApiController.class)
class MemberApiControllerTest {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    void testJoin() throws Exception{
        MemberJoinRequestDto requestDto = new MemberJoinRequestDto("test", "0410", "01012341234",
                "look@outlook.com,", "Seoul", "street", "1234");
        MemberJoinResponseDto responseDto = new MemberJoinResponseDto("test", "123@outlook.com",
                "01012341234");
        given(memberService.join(requestDto)).willReturn(responseDto);

        mockMvc.perform(post("/api/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200));

    }

    @Test
    void testJoin_InvalidRequest() throws Exception{
        mockMvc.perform(post("/api/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(400));
    }

    @Test
    void testSearch() throws Exception {
        MemberSearchRequestDto requestDto = new MemberSearchRequestDto("jinung");
        MemberSearchResponseDto responseDto = new MemberSearchResponseDto("jinung", "outlook@outlook.com");
        given(memberService.search(requestDto)).willReturn(responseDto);

        mockMvc.perform(get("/api/member/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.response.userId").exists());
    }

    @Test
    void testSearch_InvalidRequest() throws Exception{
        mockMvc.perform(get("/api/member/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(400));
    }


    @Test
    void testUpdate() throws Exception{
        MemberUpdateRequestDto requestDto = new MemberUpdateRequestDto("jinung", "1234",
                "out@look.com", "01012341234", "city", "street", "1234");
        MemberUpdateResponseDto responseDto = new MemberUpdateResponseDto("jinung", "out@look.com",
                "01012341234", "city", "steet", "1234");
        given(memberService.update(requestDto)).willReturn(responseDto);

        mockMvc.perform(patch("/api/member/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.response.userId").exists());

    }

    @Test
    void testUpdate_InvalidRequest() throws Exception{
        mockMvc.perform(patch("/api/member/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(400));
    }

    @Test
    void testWithdrawal() throws Exception{
        MemberWithdrawalRequestDto requestDto = new MemberWithdrawalRequestDto("jinung", "1234");
        MemberWithdrawalResponseDto responseDto = new MemberWithdrawalResponseDto("jinung");
        given(memberService.withdrawal(requestDto)).willReturn(responseDto);

        mockMvc.perform(delete("/api/member/withdrawal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.response.userId").exists());
    }

    @Test
    void testWithdrawal_InvalidRequest() throws Exception{
        mockMvc.perform(delete("/api/member/withdrawal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(400));
    }




}