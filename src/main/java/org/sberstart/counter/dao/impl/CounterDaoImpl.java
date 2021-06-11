package org.sberstart.counter.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.sberstart.counter.exception.NoCountersException;
import org.sberstart.counter.exception.NoSuchCounterIdException;
import org.sberstart.counter.dao.CounterDao;
import org.sberstart.counter.model.Counter;
import org.springframework.stereotype.Component;

@Component
public class CounterDaoImpl implements CounterDao {

  private final Map<Integer, Counter> counters;

  private void checkId(Integer id) {
    if (!counters.containsKey(id)) {
      if (id < 1) {
        throw new IndexOutOfBoundsException("Bad Id value: " + id);
      }
      throw new NoSuchCounterIdException(id);
    }
  }

  public CounterDaoImpl() {
    this.counters = new LinkedHashMap<>();
  }

  public Counter getCounterById(Integer id) {
    checkId(id);

    return counters.get(id);
  }

  public Counter addCounter(Counter counter) {
    counters.putIfAbsent(counter.getId(), counter);
    return counter;
  }

  public List<Counter> getAllCounters() {
    if (counters.size() == 0) {
      throw new NoCountersException();
    }
    return new ArrayList<>(counters.values());
  }

  public Counter deleteCounterById(Integer id) {
    checkId(id);

    return counters.remove(id);
  }

  public Counter incrementCounterById(Integer id) {
    checkId(id);

    Counter counter = counters.get(id);
    counter.increment();
    return counter;
  }
}
