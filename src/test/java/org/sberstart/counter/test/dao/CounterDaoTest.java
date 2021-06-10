package org.sberstart.counter.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sberstart.counter.dao.CounterDao;
import org.sberstart.counter.dao.impl.CounterDaoImpl;
import org.sberstart.counter.exception.NoCountersException;
import org.sberstart.counter.exception.NoSuchCounterIdException;
import org.sberstart.counter.model.Counter;

public class CounterDaoTest {

  CounterDao counterDao;

  @BeforeEach
  public void initDao() {
    counterDao = new CounterDaoImpl();
  }

  @Test
  public void noCountersExceptionTest() {
    assertThrows(NoCountersException.class, () -> counterDao.getAllCounters());
  }

  @Test
  public void noSuchCounterExceptionTest() {
    assertThrows(NoSuchCounterIdException.class, () -> counterDao.getCounterById(1));
    assertThrows(NoSuchCounterIdException.class, () -> counterDao.incrementCounterById(2));
    assertThrows(NoSuchCounterIdException.class, () -> counterDao.deleteCounterById(100_000));
  }

  @Test
  public void badIdInputExceptionTest() {
    assertThrows(IndexOutOfBoundsException.class, () -> counterDao.getCounterById(-1));
  }

  @Test
  public void getCounterByIdTest() {
    List<Counter> counters = new ArrayList<>();

    for (int i = 1; i <= 50; i++) {
      Counter counter = new Counter(i);
      counterDao.addCounter(counter);
      counters.add(counter);
      assertEquals(counters.get(i - 1), counterDao.getCounterById(i));
    }
  }

  @Test
  public void addCounterTest() {
    Counter counter = new Counter(15);
    assertEquals(counter, counterDao.addCounter(counter));
  }

  @Test
  public void getAllCountersTest() {
    List<Counter> counters = new ArrayList<>();
    fillCounters(counterDao, counters);

    assertEquals(counters, counterDao.getAllCounters());
  }

  @Test
  public void deleteCounterByIdTest() {
    List<Counter> counters = new ArrayList<>();
    fillCounters(counterDao, counters);

    for (int i = 49; i > 0; i -= 2) {
      counters.remove(i - 1);
      counterDao.deleteCounterById(i);
    }

    assertEquals(counters, counterDao.getAllCounters());
  }

  @Test
  public void incrementCounterByIdTest() {
    int id = 10;

    Counter counter = new Counter(id);
    counterDao.addCounter(new Counter(id));

    for (int i = 0; i < 300; i++) {
      counter.increment();
      counterDao.incrementCounterById(id);
    }

    assertEquals(counter, counterDao.getCounterById(id));
  }

  private static void fillCounters(CounterDao counterDao, List<Counter> counters) {
    for (int i = 1; i <= 50; i++) {
      counterDao.addCounter(new Counter(i));
      counters.add(new Counter(i));
    }
  }
}
