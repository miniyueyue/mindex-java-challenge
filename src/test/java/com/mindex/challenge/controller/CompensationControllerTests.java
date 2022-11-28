package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
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
public class CompensationControllerTests {
    @Mock
    private CompensationService compensationService;

    @InjectMocks
    private CompensationController compensationController;

    private static String JohnLennonId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
    @Mock
    private static Compensation compensation;

    @Test
    public void create(){
        compensationController.create(compensation);
        verify(compensationService, times(1)).create(compensation);
    }

    @Test
    public void read(){
        compensationController.read(JohnLennonId);
        verify(compensationService, times(1)).read(JohnLennonId);
    }
}
