package org.sberstart.counter.controller;

import java.util.List;
import org.sberstart.counter.model.Counter;
import org.sberstart.counter.service.impl.CounterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {

  private final CounterServiceImpl service;

  @Autowired
  public CounterController(CounterServiceImpl service) {
    this.service = service;
  }

  @GetMapping("/counters/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Counter getCounterById(@PathVariable Integer id) {
    return service.getCounterById(id);
  }

  @GetMapping("/counters")
  @ResponseStatus(HttpStatus.OK)
  public List<Counter> getallCounters() {
    return service.getAllCounters();
  }

  @GetMapping("/counters/sum")
  @ResponseStatus(HttpStatus.OK)
  public String getAllSum() {
    return service.getAllSum().toString();
  }

  @PostMapping("/counters")
  @ResponseStatus(HttpStatus.CREATED)
  public Counter addNewCounter() {
    return service.addNewCounter();
  }

  @PutMapping("/counters/increment/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Counter incrementCounterById(@PathVariable Integer id) {
    return service.incrementCounterById(id);
  }

  @DeleteMapping("/counters/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String deleteCounterById(@PathVariable Integer id) {
    service.deleteCounterById(id);
    return "Successfully deleted";
  }
}
