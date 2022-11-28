package com.mindex.challenge.e2e;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureTests {
    private static String reportingUrl;
    private static String JohnLennonId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
    private static String PaulMcCartneyId = "b7839309-3348-463b-a7e3-5de1c168beb3";
    private static String RingoStarrId = "03aa1462-ffa9-4978-901b-7c001562cf6f";
    private static String PeteBestId = "62c1084e-6e34-4630-93fd-9153afb65309";
    private static String GeorgeHarrisonId = "c0c2293d-16bd-4603-8e08-638a9d18b22c";

    private static String JohnLennonFN = "John";
    private static String PaulMcCartneyFN = "Paul";
    private static String RingoStarrFN = "Ringo";
    private static String PeteBestFN = "Pete";
    private static String GeorgeHarrisonFN = "George";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup(){
        reportingUrl = "http://localhost:" + port + "/reporting-structure/{id}";
    }

    @Test
    public void shouldReturnCorrectReports(){
        ReportingStructure JohnLennonReports = getReportsById(JohnLennonId);
        ReportingStructure PaulMcCartneyReports = getReportsById(PaulMcCartneyId);
        ReportingStructure RingoStarrReports = getReportsById(RingoStarrId);
        ReportingStructure PeteBestReports = getReportsById(PeteBestId);
        ReportingStructure GeorgeHarrisonReports = getReportsById(GeorgeHarrisonId);

        Employee JohnLennon = new Employee();
        Employee PaulMcCartney = new Employee();
        Employee RingoStarr = new Employee();
        Employee PeteBest = new Employee();
        Employee GeorgeHarrison = new Employee();

        JohnLennon.setFirstName(JohnLennonFN);
        PaulMcCartney.setFirstName(PaulMcCartneyFN);
        RingoStarr.setFirstName(RingoStarrFN);
        PeteBest.setFirstName(PeteBestFN);
        GeorgeHarrison.setFirstName(GeorgeHarrisonFN);

        ReportingStructure JohnLennonExpected = new ReportingStructure(JohnLennon, 4);
        ReportingStructure PaulMcCartneyExpected = new ReportingStructure(PaulMcCartney, 0);
        ReportingStructure RingoStarrExpected = new ReportingStructure(RingoStarr, 2);
        ReportingStructure PeteBestExpected = new ReportingStructure(PeteBest, 0);
        ReportingStructure GeorgeHarrisonExpected = new ReportingStructure(GeorgeHarrison, 0);

        assertReportsEquivalence(JohnLennonExpected, JohnLennonReports);
        assertReportsEquivalence(PaulMcCartneyExpected, PaulMcCartneyReports);
        assertReportsEquivalence(RingoStarrExpected, RingoStarrReports);
        assertReportsEquivalence(PeteBestExpected, PeteBestReports);
        assertReportsEquivalence(GeorgeHarrisonExpected, GeorgeHarrisonReports);
    }

    private ReportingStructure getReportsById(String id){
        return restTemplate.getForObject(reportingUrl, ReportingStructure.class, id);
    }

    private static void assertReportsEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEquals(expected.getEmployee().getFirstName(), actual.getEmployee().getFirstName());
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
    }
}
