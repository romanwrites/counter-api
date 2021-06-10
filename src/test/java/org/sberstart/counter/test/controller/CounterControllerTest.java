package org.sberstart.counter.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.sberstart.counter.controller.CounterController;
import org.sberstart.counter.model.Counter;
import org.sberstart.counter.service.impl.CounterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(CounterController.class)
class CounterControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CounterServiceImpl service;

  @Test
  void getCounterById() throws Exception {
    Counter expected = new Counter(10);
    when(service.getCounterById(anyInt())).thenReturn(expected);
    MvcResult result = this.mockMvc.perform(get("/counters/10")).andDo(print())
        .andExpect(status().isOk()).andReturn();

    Counter counter = objectMapper
        .readValue(result.getResponse().getContentAsString(), Counter.class);

    assertEquals(expected, counter);
    verify(service).getCounterById(anyInt());
  }

  @Test
  void getAllCounters() throws Exception {
    List<Counter> expectedCounters = new ArrayList<>();
    for (int i = 1; i <= 5; i++) {
      expectedCounters.add(new Counter(i));
    }

    when(service.getAllCounters()).thenReturn(expectedCounters);

    MvcResult result = this.mockMvc.perform(get("/counters")).andDo(print())
        .andExpect(status().isOk()).andReturn();

    List<Counter> counters = objectMapper
        .readValue(result.getResponse().getContentAsString(), new TypeReference<List<Counter>>(){});

    assertEquals(expectedCounters, counters);
    verify(service).getAllCounters();
  }

  @Test
  void getAllSum() throws Exception {
    List<Counter> counters = new ArrayList<>();
    for (int i = 1; i <= 5; i++) {
      Counter counter = new Counter(i);
      for (int j = 0; j < 5; j++) {
        counter.increment();
      }
      counters.add(counter);
    }

    BigInteger expected = counters.stream()
        .map(Counter::getValue)
        .reduce(BigInteger::add)
        .orElseThrow();

    when(service.getAllSum()).thenReturn(expected);

    MvcResult result = this.mockMvc.perform(get("/counters/sum")).andDo(print())
        .andExpect(status().isOk()).andReturn();

    BigInteger sum = new BigInteger(result.getResponse().getContentAsString());

    assertEquals(expected, sum);
    verify(service).getAllSum();
  }

  @Test
  void addNewCounter() throws Exception {
    Counter expected = new Counter(1);
    when(service.addNewCounter()).thenReturn(expected);

    MvcResult result = this.mockMvc.perform(post("/counters")).andDo(print())
        .andExpect(status().isCreated()).andReturn();

    Counter counter = objectMapper
        .readValue(result.getResponse().getContentAsString(), Counter.class);

    assertEquals(expected, counter);
    verify(service).addNewCounter();
  }

  @Test
  void incrementCounterById() throws Exception {
    Counter expected = new Counter(15);
    expected.increment();

    when(service.incrementCounterById(anyInt())).thenReturn(expected);

    MvcResult result = this.mockMvc.perform(put("/counters/increment/15")).andDo(print())
        .andExpect(status().isOk()).andReturn();

    Counter counter = objectMapper
        .readValue(result.getResponse().getContentAsString(), Counter.class);

    assertEquals(expected, counter);
    verify(service).incrementCounterById(anyInt());
  }

  @Test
  void deleteCounterById() throws Exception {
    String message = "Successfully deleted";
    Counter counter = new Counter(15);
    when(service.deleteCounterById(anyInt())).thenReturn(counter);

    this.mockMvc.perform(delete("/counters/15")).andDo(print())
        .andExpect(status().isNoContent()).andReturn();

    verify(service).deleteCounterById(anyInt());
  }
}
