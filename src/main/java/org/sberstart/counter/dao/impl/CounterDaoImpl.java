package org.sberstart.counter.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.sberstart.counter.dao.CounterDao;
import org.sberstart.counter.model.Counter;
import org.springframework.stereotype.Component;

@Component
public class CounterDaoImpl implements CounterDao {

  private final List<Counter> counters;

  public CounterDaoImpl() {
    this.counters = new ArrayList<>();
  }

  public Counter getCounterById(Integer id) {
    return counters.get(id - 1);
  }

  public void addCounter(Counter counter) {
    counters.add(counter);
  }

  public List<Counter> getAllCounters() {
    return counters;
  }

  public void deleteCounterById(Integer id) {
    counters.remove(id - 1);
  }
}
