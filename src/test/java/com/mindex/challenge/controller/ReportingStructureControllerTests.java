package com.mindex.challenge.controller;

import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class ReportingStructureControllerTests {
    @Mock
    private ReportingStructureService reportingStructureService;

    @InjectMocks
    private ReportingStructureController reportingStructureController;

    private static String JohnLennonId = "16a596ae-edd3-4847-99fe-c4518e82c86f";

    @Test
    public void read(){
        reportingStructureController.read(JohnLennonId);
        verify(reportingStructureService, times(1)).read(JohnLennonId);
    }
}
