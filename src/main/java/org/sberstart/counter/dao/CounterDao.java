package org.sberstart.counter.dao;

import java.util.ArrayList;
import java.util.List;
import org.sberstart.counter.model.Counter;

public interface CounterDao {

  public Counter getCounterById(Integer id);

  public void addCounter(Counter counter);

  public List<Counter> getAllCounters();

  public void deleteCounterById(Integer id);
}
