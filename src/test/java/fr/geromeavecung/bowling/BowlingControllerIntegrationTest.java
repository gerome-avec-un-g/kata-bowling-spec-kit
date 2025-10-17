package fr.geromeavecung.bowling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BowlingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnScoreForPerfectGame() throws Exception {
        String json = "{\"frames\":\"X X X X X X X X X XXX\"}";

        mockMvc.perform(post("/api/score")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value(300));
    }

    @Test
    public void shouldReturnBadRequestForInvalidInput() throws Exception {
        String invalid = "{\"frames\":\"75 - - - - - - - - -\"}"; // invalid frame token 75

        mockMvc.perform(post("/api/score")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalid))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnScoreForMixedExample() throws Exception {
        String json = "{\"frames\":\"X 7/ 9- X -8 8/ -6 X X X81\"}";

        mockMvc.perform(post("/api/score")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value(167));
    }
}

