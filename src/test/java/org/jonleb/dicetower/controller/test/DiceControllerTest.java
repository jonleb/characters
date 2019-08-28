package org.jonleb.dicetower.controller.test;

import lombok.extern.log4j.Log4j2;
import org.jonleb.dicetower.SpringBootCharacterApplication;
import org.jonleb.dicetower.config.WebSecurityConfiguration;
import org.jonleb.dicetower.web.controller.DiceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("WeakerAccess")
@SpringBootTest(classes = {
        SpringBootCharacterApplication.class,
        WebSecurityConfiguration.class,
        DiceController.class
})
@AutoConfigureMockMvc
@Log4j2
public class DiceControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void given_3D6_for_total() throws Exception {
        log.debug("Start testing 'given_3D6_for_total'");
        String s = mockMvc.perform(
                get("/api/v1/dices/roll")
                        .contentType("application/json")
                        .content("{" +
                                "\"rollType\": \"TOTAL\"," +
                                "\"dicesToRoll\": \"3D6 \"" +
                                "}")
            )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug(s);
        log.debug("End testing 'given_3D6_for_total'");
    }

    @Test
    public void given_3D6_8D4_for_total() throws Exception {
        log.debug("Start testing 'given_3D6_for_total'");
        String s = mockMvc.perform(
                get("/api/v1/dices/roll")
                        .contentType("application/json")
                        .content("{" +
                                "\"rollType\": \"TOTAL_BY_TYPE\"," +
                                "\"dicesToRoll\": \"3D6 8D4\"" +
                                "}")
        )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug(s);
        log.debug("End testing 'given_3D6_for_total'");
    }

    @Test
    public void given_3D60_for_total_for_exception() throws Exception {
        log.debug("Start testing 'given_3D60_for_total'");
        String s = mockMvc.perform(
                get("/api/v1/dices/roll")
                        .contentType("application/json")
                        .content("{" +
                                "\"rollType\": \"TOTAL\"," +
                                "\"dicesToRoll\": \"3D60\"" +
                                "}")
        )
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
        log.debug(s);
        log.debug("End testing 'given_3D60_for_total'");
    }
}
