package org.sberstart.counter.service;

import org.sberstart.counter.dao.CounterDao;
import org.sberstart.counter.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

  private final CounterDao dao;

  @Autowired
  public CounterService(CounterDao dao) {
    this.dao = dao;
  }

  public Counter getCounterById(Integer id) {
    return dao.getCounterById(id);
  }
}
