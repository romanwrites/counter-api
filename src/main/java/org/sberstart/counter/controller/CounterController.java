package org.sberstart.counter.controller;

import org.sberstart.counter.model.Counter;
import org.sberstart.counter.service.impl.CounterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {

  private CounterServiceImpl service;

  @Autowired
  public CounterController(CounterServiceImpl service) {
    this.service = service;
  }

  // todo codes
  @GetMapping("/counters/{id}")
  public Counter getCounterById(@PathVariable Integer id) {
    return service.getCounterById(id);
  }

  @GetMapping("/counters/sum")
  public String getAllSum() {
    return service.getAllSum().toString();
  }

  @GetMapping("/hello")
  public String getHello() {
    return "hello";
  }
}
