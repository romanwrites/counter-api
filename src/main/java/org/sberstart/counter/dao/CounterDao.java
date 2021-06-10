package org.sberstart.counter.dao;

import java.util.List;
import org.sberstart.counter.model.Counter;

public interface CounterDao {

  Counter getCounterById(Integer id);

  Counter addCounter(Counter counter);

  List<Counter> getAllCounters();

  Counter deleteCounterById(Integer id);

  Counter incrementCounterById(Integer id);
}
