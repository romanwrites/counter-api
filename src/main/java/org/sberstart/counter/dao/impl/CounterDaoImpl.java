package org.sberstart.counter.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.sberstart.counter.NoCountersException;
import org.sberstart.counter.NoSuchCounterIdException;
import org.sberstart.counter.dao.CounterDao;
import org.sberstart.counter.model.Counter;
import org.springframework.stereotype.Component;

@Component
public class CounterDaoImpl implements CounterDao {

  private final List<Counter> counters;

  private void checkId(Integer id) {
    if (id > counters.size()) {
      throw new NoSuchCounterIdException(id);
    }
  }

  public CounterDaoImpl() {
    this.counters = new ArrayList<>();
  }

  public Counter getCounterById(Integer id) {
    checkId(id);

    return counters.get(id - 1);
  }

  public Counter addCounter(Counter counter) {
    counters.add(counter);
    return counter;
  }

  public List<Counter> getAllCounters() {
    if (counters.size() == 0) {
      throw new NoCountersException();
    }
    return counters;
  }

  public Counter deleteCounterById(Integer id) {
    checkId(id);

    return counters.remove(id - 1);
  }

  public Counter incrementCounterById(Integer id) {
    checkId(id);

    Counter counter = counters.get(id - 1);
    counter.increment();
    return counter;
  }
}
