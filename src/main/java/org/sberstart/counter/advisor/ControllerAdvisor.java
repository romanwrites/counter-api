package org.sberstart.counter.advisor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.sberstart.counter.NoCountersException;
import org.sberstart.counter.NoSuchCounterIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvisor {

  public final static String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";

  private Map<String, String> createErrorResponse(HttpStatus status, String message) {
    Map<String, String> response = new LinkedHashMap<>();

    response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
    response.put("status", String.valueOf(status.value()));
    response.put("message", message);

    return response;
  }

  // todo create custom exception to handle more precisely
  @ExceptionHandler(value = {NumberFormatException.class, IndexOutOfBoundsException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, String> handleBadRequestById() {
    return createErrorResponse(HttpStatus.BAD_REQUEST, "Bad Id provided");
  }

  @ExceptionHandler(value = NoSuchCounterIdException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, String> handleNoSuchCounterByIdException() {
    return createErrorResponse(HttpStatus.NOT_FOUND, "no such element exception");
  }

  @ExceptionHandler(value = NoCountersException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, String> handleNoCountersException() {
    return createErrorResponse(HttpStatus.NOT_FOUND, "no such element exception");
  }

}