package org.sberstart.counter.dao;

import java.util.List;
import org.sberstart.counter.model.Counter;

public interface CounterDao {

  /**
   * Find counter object by id
   * @param id id of a counter
   * @return counter object, found by id
   */
  Counter getCounterById(Integer id);

  /**
   * Add new counter object
   * @param counter model object to add
   * @return model added
   */
  Counter addCounter(Counter counter);

  /**
   * @return all existing counters list
   */
  List<Counter> getAllCounters();

  /**
   * @param id id of a counter
   * @return counter object, removed from database
   */
  Counter deleteCounterById(Integer id);

  /**
   * Increment counter value by one
   * @param id id of a counter
   * @return counter object
   */
  Counter incrementCounterById(Integer id);
}
