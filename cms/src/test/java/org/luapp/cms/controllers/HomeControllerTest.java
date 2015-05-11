package org.luapp.cms.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by lum on 2015/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class HomeControllerTest extends AbstractContextControllerTests {
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }

    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andExpect(view().name(containsString("index")))
                .andExpect(forwardedUrl("/WEB-INF/jsp/user/index.jsp"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", "11111"));
    }
}
