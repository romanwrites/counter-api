package org.sberstart.counter.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import org.sberstart.counter.dao.CounterDao;
import org.sberstart.counter.model.Counter;
import org.sberstart.counter.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {

  private final CounterDao dao;
  private Integer counterId;

  @Autowired
  public CounterServiceImpl(CounterDao dao) {
    this.dao = dao;
    this.counterId = 0;
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

  public Counter addNewCounter() {
    return dao.addCounter(new Counter(++counterId));
  }

  public Counter deleteCounterById(Integer id) {
    return dao.deleteCounterById(id);
  }

  public Counter incrementCounterById(Integer id) {
    return dao.incrementCounterById(id);
  }

  public List<Counter> getAllCounters() {
    return dao.getAllCounters();
  }

  public List<Integer> getAllCountersNames() {
    return dao.getAllCounters().stream().map(a -> a.getId()).collect(Collectors.toList());
  }
}
