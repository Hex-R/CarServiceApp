package com.hex.AutoServiceAppV1.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
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
class RegistrationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void showRegistrationForm() throws Exception {
        this.mockMvc.perform(get("/register"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Минимум четыре символа")))
                .andExpect(content().string(containsString("Мы отправим вам ссылку для верификации")));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/registrationTestData.csv", numLinesToSkip = 1, delimiter = '|')
    void processRegistration(String username,
                             String password,
                             String passwordConfirmation,
                             String email,
                             String phoneNumber,
                             int statusCode,
                             String redirectedUrl,
                             String expectedPageContent)
            throws Exception {

        this.mockMvc.perform(post("/register")
                        .param("username", username)
                        .param("password", password)
                        .param("passwordConfirmation", passwordConfirmation)
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                        .param("g-recaptcha-response",
                                "ValueForTestPurposes_69!04%b362-39)34-47=a5-bf0c-c89e*555Q23p1J28")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is(statusCode))
                .andExpect(redirectedUrl(redirectedUrl))
                .andExpect(content().string(containsString(expectedPageContent)));
    }

    @Test
    void showRegistrationSuccessPage() throws Exception {
        this.mockMvc.perform(get("/register/success"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Перейдите по ссылке в письме для завершения регистрации")));
    }

    @ParameterizedTest
    @CsvSource({"'6904b362-3934-47a5-bf0c-c89e55523128', 'Аккаунт успешно активирован'",
            "'testInvalidConfirmationCode', 'Пользователь был активирован ранее'"})
    void activateAccount(String code, String checkPageContent) throws Exception {
        this.mockMvc.perform(get(String.format("/register/activation/%s", code)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(checkPageContent)));
    }

    @Test
    @WithUserDetails("testActive")
    @Transactional
    void authenticationTest() throws Exception {
        this.mockMvc.perform(get("/service_order"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
    }
}