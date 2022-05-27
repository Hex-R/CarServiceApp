package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.ServiceOrder;
import com.hex.AutoServiceAppV1.repositories.ServiceOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/beforeRegistrationTests.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/afterRegistrationTests.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("testActive")
class UserProfileControllerTest {

    MockMvc mockMvc;

    ServiceOrderRepository serviceOrderRepository;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    public void setServiceOrderRepository(ServiceOrderRepository serviceOrderRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
    }

    @Test
    @Transactional
    void showUserProfilePage() throws Exception {
        ServiceOrder completedOrder = serviceOrderRepository.findById(3L).get();
        ServiceOrder activeOrder = serviceOrderRepository.findById(4L).get();

        this.mockMvc.perform(get("/user_profile"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userDetailsForm", "activeOrders", "completedOrders"))
                .andExpect(content().string(containsString("testActive@mail.com")))
                .andExpect(content().string(containsString("89081231234")))
                .andExpect(content().string(containsString("28.05.2022 14:00")))
                .andExpect(content().string(containsString(activeOrder.getServices().toString())))
                .andExpect(content().string(containsString("26.05.2022 12:00")))
                .andExpect(content().string(containsString(completedOrder.getServices().toString())));
    }

    @ParameterizedTest
    @Transactional
    @CsvFileSource(resources = "/profileUpdateTestData.csv", numLinesToSkip = 1, delimiter = '|')
    void updateUserProfile(String password,
                           String passwordConfirmation,
                           String email,
                           String phoneNumber,
                           String expectedPageContent)
            throws Exception {

        this.mockMvc.perform(post("/user_profile")
                        .param("password", password)
                        .param("passwordConfirmation", passwordConfirmation)
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                        .with(csrf()))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedPageContent)));
    }
}