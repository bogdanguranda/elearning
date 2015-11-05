package thecerealkillers.elearning.controller.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:test-dispatcher-servlet.xml"})
public class CourseControllerImplIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getCourseTest() throws Exception {
        mockMvc.perform(post("/courses").content("{ \n" +
                " \"title\":\"dada\",\n" +
                " \"about\":\"dada\",\n" +
                " \"recommendedBackground\":\"dada\",\n" +
                " \"suggestedReadings\":\"dada\",\n" +
                " \"courseFormat\":\"dada\" \n" +
                "}"));
        ResultActions resultActions = mockMvc.perform(get("/courses/dada").accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().string("{ \n" +
                " \"title\":\"dada\",\n" +
                " \"about\":\"dada\",\n" +
                " \"recommendedBackground\":\"dada\",\n" +
                " \"suggestedReadings\":\"dada\",\n" +
                " \"courseFormat\":\"dada\" \n" +
                "}"));
    }
}
