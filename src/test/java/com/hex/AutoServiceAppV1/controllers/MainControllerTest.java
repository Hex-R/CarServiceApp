package com.hex.AutoServiceAppV1.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void showHomePage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("О нашем автосервисе")));
    }

    @Test
    void showMaintenanceServices() throws Exception {
        this.mockMvc.perform(get("/maintenance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Зачем нужен плановый техосмотр")));
    }

    @Test
    void showDiagnosticsServices() throws Exception {
        this.mockMvc.perform(get("/diagnostics"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Виды диагностики авто")));
    }

    @Test
    void showRepairServices() throws Exception {
        this.mockMvc.perform(get("/repair"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Виды ремонта автомобилей")));
    }

    @Test
    void showBodyRepairServices() throws Exception {
        this.mockMvc.perform(get("/body_repair"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Виды кузовного ремонта авто")));
    }

    @Test
    void showTireFittingServices() throws Exception {
        this.mockMvc.perform(get("/tire_fitting"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Шиномонтаж в X-Service")));
    }

    @Test
    void showContactsPage() throws Exception {
        this.mockMvc.perform(get("/contacts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ООО «Экс-Сервис»")));
    }
}