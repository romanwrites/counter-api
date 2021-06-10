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

    response.put("status", String.valueOf(status.value()));
    response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
    response.put("message", message);

    return response;
  }

  @ExceptionHandler(value = {NumberFormatException.class, IndexOutOfBoundsException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, String> handleBadRequestById() {
    return createErrorResponse(HttpStatus.BAD_REQUEST, "Bad Id provided");
  }

  @ExceptionHandler(value = NoSuchCounterIdException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public Map<String, String> handleNoSuchCounterByIdException(NoSuchCounterIdException e) {
    return createErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
  }

  @ExceptionHandler(value = NoCountersException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public Map<String, String> handleNoCountersException(NoCountersException e) {
    return createErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
  }
}
