package org.sberstart.counter.dao;

import java.util.List;
import org.sberstart.counter.model.Counter;

public interface CounterDao {

  public Counter getCounterById(Integer id);

  public Counter addCounter(Counter counter);

  public List<Counter> getAllCounters();

  public Counter deleteCounterById(Integer id);

  Counter incrementCounterById(Integer id);
}
