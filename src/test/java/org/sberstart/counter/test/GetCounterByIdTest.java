package org.sberstart.counter.test;

import org.mockito.Mock;
import org.sberstart.counter.service.CounterService;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetCounterByIdTest {

    @Mock
    private CounterService counterService;
}
