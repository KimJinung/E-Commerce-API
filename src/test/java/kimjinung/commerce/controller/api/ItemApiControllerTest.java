package kimjinung.commerce.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kimjinung.commerce.dto.item.ItemRegisterRequestDto;
import kimjinung.commerce.dto.item.ItemRegisterResponseDto;
import kimjinung.commerce.usecase.item.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ItemApiController.class)
class ItemApiControllerTest {

    ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ItemService itemService;

    @BeforeEach
    void before() {
        mapper = new ObjectMapper();
    }

    @Test
    void testRegister() throws Exception {
        ItemRegisterRequestDto requestDto = new ItemRegisterRequestDto("MacBook", 1000, 2);
        ItemRegisterResponseDto responseDto = new ItemRegisterResponseDto(UUID.randomUUID().toString(), "MacBook", 1000, 2);
        given(itemService.register(requestDto)).willReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/item/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.response.id").exists());
    }

    @Test
    void testRegister_InvalidRequest() {

    }

    @Test
    void testSearch() {

    }

    @Test
    void testSearch_InvalidReqeust() {
    }
}