package com.mindex.challenge;

import com.mindex.challenge.controller.CompensationController;
import com.mindex.challenge.controller.ReportingStructureController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeApplicationTests {

	@Autowired
	private ReportingStructureController reportingStructureController;
	@Autowired
	private CompensationController compensationController;
	@Test
	public void contextLoads() {
		assertNotNull(reportingStructureController);
		assertNotNull(compensationController);
	}

}
