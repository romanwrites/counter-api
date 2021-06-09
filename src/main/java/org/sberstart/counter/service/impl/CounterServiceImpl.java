package org.sberstart.counter.service.impl;

import java.math.BigInteger;
import org.sberstart.counter.dao.CounterDao;
import org.sberstart.counter.model.Counter;
import org.sberstart.counter.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {

  private final CounterDao dao;

  @Autowired
  public CounterServiceImpl(CounterDao dao) {
    this.dao = dao;
  }

  public Counter getCounterById(Integer id) {
    return dao.getCounterById(id);
  }

  public BigInteger getAllSum() {
    return dao.getAllCounters()
        .stream()
        .map(Counter::getValue)
        .reduce(BigInteger::add)
        .orElseThrow();
  }
}
