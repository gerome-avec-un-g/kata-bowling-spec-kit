package fr.geromeavecung.bowling;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest
    @CsvSource({"00 00 00 00 00 00 00 00 00 00,0", "00 00 00 00 00 00 00 00 00 10,1"})
    void compute_score(String frames, String score) throws Exception {
        when(bowlingCalculator.compute(new Frames(frames))).thenReturn(Integer.parseInt(score));

        mockMvc.perform(post("/api/bowling/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "frames": "%s"
                                }""".formatted(frames)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "score": %s
                        }""".formatted(score)));

    }

}
