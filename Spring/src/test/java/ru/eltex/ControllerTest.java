package ru.eltex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private UserController controller;

    @Test
    public void contextLoads() {
        org.assertj.core.api.Assertions.assertThat(controller).isNotNull();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUser() throws Exception {
        this.mockMvc.perform(get("http://localhost:8081/get_user/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Gog")));
    }
}
