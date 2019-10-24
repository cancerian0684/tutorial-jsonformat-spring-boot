package com.shunya.jacksondemo;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DateController.class)
class DateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FooService fooService;

    @Test
    void postDate() throws Exception {
        //language=JSON
        String jsonPayload = "{\n" +
                "  \"instant\": \"2019-10-24T07:58:25.000+05:30\",\n" +
                "  \"date\": \"2019-10-24T02:28:25.000+0000\",\n" +
                "  \"localDate\": \"2019-10-25\",\n" +
                "  \"localDateTime\": \"2019-10-24T02:28:25\"\n" +
                "}";
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/date")
                .content(jsonPayload)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(CoreMatchers.notNullValue()));
    }

    @Test
    void getGetDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/date")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.instant").value("2019-10-24T07:58:25.000+05:30"))
                .andExpect(jsonPath("$.localDate").value("2019-10-24"))
                .andExpect(jsonPath("$.date").value("2019-10-24T02:28:25.000+0000"))
                .andExpect(jsonPath("$.localDateTime").value("2019-10-24T02:28:25"));
    }
}
