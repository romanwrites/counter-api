package org.sberstart.counter.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.sberstart.counter.controller.CounterController;
import org.sberstart.counter.model.Counter;
import org.sberstart.counter.service.impl.CounterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CounterControllerTest {

  @Autowired
  private CounterController counterController;

  @MockBean
  private CounterServiceImpl service;

  @Test
  void getCounterById() throws Exception {
    Counter counter = new Counter(10);
    when(service.getCounterById(anyInt())).thenReturn(counter);

    assertEquals(counter, counterController.getCounterById(10));
  }

  @Test
  void getallCounters() {
    List<Counter> counters = new ArrayList<>();
    for (int i = 1; i <= 5; i++) {
      counters.add(new Counter(i));
    }

    when(service.getAllCounters()).thenReturn(counters);

    assertEquals(counters, counterController.getallCounters());
  }

  @Test
  void getAllSum() {
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

    assertEquals(expected.toString(), counterController.getAllSum());
  }

  @Test
  void addCounter() {
    Counter counter = new Counter(15);
    when(service.addNewCounter()).thenReturn(counter);

    assertEquals(counter, counterController.addCounter());
  }

  @Test
  void incrementCounterById() {
    Counter counter = new Counter(15);
    Counter expected = new Counter(15);
    counter.increment();
    expected.increment();

    when(service.incrementCounterById(anyInt())).thenReturn(counter);

    assertEquals(expected, counterController.incrementCounterById(15));
  }

  @Test
  void deleteCounterById() {
    String message = "Successfully deleted";
    Counter counter = new Counter(15);
    when(service.deleteCounterById(anyInt())).thenReturn(counter);

    assertEquals(message, counterController.deleteCounterById(15));

  }
}
