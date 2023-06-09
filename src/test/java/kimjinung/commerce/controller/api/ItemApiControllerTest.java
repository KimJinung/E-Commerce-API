package kimjinung.commerce.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kimjinung.commerce.dto.item.*;
import kimjinung.commerce.usecase.item.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ItemApiController.class)
class ItemApiControllerTest {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ItemService itemService;

    @Test
    void testRegister() throws Exception {
        ItemRegisterRequestDto requestDto = new ItemRegisterRequestDto(
                "jinung","MacBook", 1000, 2);
        ItemRegisterResponseDto responseDto = new ItemRegisterResponseDto(
                UUID.randomUUID().toString(), "jinung", "MacBook", 1000, 2);
        given(itemService.register(requestDto)).willReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/item/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.response.id").exists());
    }

    @Test
    void testRegister_InvalidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/item/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(400));
    }

    @Test
    void testSearch() throws Exception {
        ItemSearchRequestDto requestDto = new ItemSearchRequestDto("Duck");
        ItemSearchResponseDto responseDto = new ItemSearchResponseDto(UUID.randomUUID().toString(), "Duck", 100, 1);
        given(itemService.search(requestDto)).willReturn(List.of(responseDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/item/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.response[0].id").exists());
    }

    @Test
    void testSearch_InvalidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/item/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString("")))
                .andExpect(status().is(400));
    }

    @Test
    void testUpdate() throws Exception {
        String id = UUID.randomUUID().toString();
        ItemUpdateRequestDto requestDto = new ItemUpdateRequestDto(id, "Duck", 1000, 1);
        ItemUpdateResponseDto responseDto = new ItemUpdateResponseDto(id, "Duck", 1000, 1);
        given(itemService.update(requestDto)).willReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/item/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.response.id").exists());
    }

    @Test
    void testUpdate_InvalidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/item/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(400));
    }

    @Test
    void testRemove() throws Exception {
        String id = UUID.randomUUID().toString();
        ItemRemoveRequestDto requestDto = new ItemRemoveRequestDto(id);
        ItemRemoveResponseDto responseDto = new ItemRemoveResponseDto(id, "Duck");
        given(itemService.remove(requestDto)).willReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/item/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.response.id").exists());
    }

    @Test
    void testRemove_InvalidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/item/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(400));
    }
}