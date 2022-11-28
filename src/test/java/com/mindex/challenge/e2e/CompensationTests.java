package com.mindex.challenge.e2e;

import com.mindex.challenge.data.Compensation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationTests {

    private static String compensationUrl;
    private static String compensationIdUrl;

    private static String JohnLennonId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
    private static Date date;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() throws ParseException {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
        date = new SimpleDateFormat( "yyyyMMdd" ).parse( "20230101" );
    }

    @Test
    public void shouldSaveAndReturnCompensation(){
        Compensation compensation = new Compensation(JohnLennonId, 100000F, date);
        ResponseEntity<Compensation> compensationResponseEntity = restTemplate.postForEntity(compensationUrl, compensation, Compensation.class);
        assertEquals(200, compensationResponseEntity.getStatusCodeValue());

        Compensation actualRead = restTemplate.getForObject(compensationIdUrl, Compensation.class, JohnLennonId);
        assertEquals(JohnLennonId, actualRead.getEmployeeId());
        assertEquals(100000.0, actualRead.getSalary(), 0.0001f);
        assertEquals(date.toString(), actualRead.getEffectiveDate().toString());
    }
}
