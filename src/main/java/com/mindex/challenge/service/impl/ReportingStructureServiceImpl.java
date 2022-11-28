package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Reading employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        AtomicInteger counter = new AtomicInteger(0);
        getReportsNumber(id, counter);

        return new ReportingStructure(employee, counter.intValue());
    }

    private void getReportsNumber(String employeeId, AtomicInteger counter){
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if(employee.getDirectReports() != null) {
            employee.getDirectReports().forEach(elem -> {
                counter.getAndIncrement();
                getReportsNumber(elem.getEmployeeId(), counter);
            });
        }
    }
}
