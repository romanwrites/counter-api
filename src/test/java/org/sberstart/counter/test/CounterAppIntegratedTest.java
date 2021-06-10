package org.sberstart.counter.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.sberstart.counter.controller.CounterController;
import org.sberstart.counter.model.Counter;
import org.sberstart.counter.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CounterController.class, CounterService.class})
@WebMvcTest
class BirthdayInfoControllerIT {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void addNewCounterTest() throws Exception {
//        Counter counter = new Counter(1);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//            .post("/customers")
////            .with(user(TEST_USER_ID))
////            .with(csrf())
//            .content(Counter)
//            .contentType(MediaType.APPLICATION_JSON)
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isCreated())
//            .andReturn();
//
//        String resultDOW = result.getResponse().getContentAsString();
//        assertNotNull(resultDOW);
//        assertEquals(dow, resultDOW);
//    }
}