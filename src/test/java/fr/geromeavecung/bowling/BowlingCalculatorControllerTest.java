package fr.geromeavecung.bowling;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {BowlingCalculatorController.class})
@WebMvcTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BowlingCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BowlingCalculator bowlingCalculator;

    @Test
    void compute_score() throws Exception {
        when(bowlingCalculator.compute(new Frames("0 0 0 0 0 0 0 0 0 0"))).thenReturn(0);

        mockMvc.perform(post("/api/bowling/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "frames": "0 0 0 0 0 0 0 0 0 0"
                                }"""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "score": 0
                        }"""));

    }

}
