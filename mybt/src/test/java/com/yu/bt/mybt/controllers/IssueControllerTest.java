package com.yu.bt.mybt.controllers;

import com.yu.bt.mybt.models.Issue;
import com.yu.bt.mybt.repository.IssueRepository;
import com.yu.bt.mybt.repository.RoleRepository;
import com.yu.bt.mybt.repository.UserRepository;
import com.yu.bt.mybt.services.IssueService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import java.util.Arrays;
import java.util.List;


@WebMvcTest(IssueController.class)
class IssueControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    UserRepository userRepo;

    @MockBean
    IssueService issueService;

    @MockBean
    RoleRepository roleRepo;

    @MockBean
    IssueRepository issueRepo;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void getAllIssues() throws Exception {
        Mockito.when(issueRepo.findAll()).thenReturn(List.of(
                new Issue("something is wrong", "BUG", "high", "good description", "APPROVER"),
                new Issue("dead mouse", "Help desk", "low", "good description", "APPROVER"),
                new Issue("app is suck", "BUG", "high", "good description", "APPROVER"))
        );

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/issues"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].issueType").value("BUG"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].priority").value("high"));

    }
}