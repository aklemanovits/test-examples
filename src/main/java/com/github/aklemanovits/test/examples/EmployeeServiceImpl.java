
package com.github.aklemanovits.test.examples;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author aklemanovits on 2018. 04. 01.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(String name) {
        Assert.hasLength(name, "Name can not be blank.");

        return employeeRepository.save(new Employee(name));
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id, "id can not be null.");

        employeeRepository.deleteById(id);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee employeeFromDb = employeeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee with the specific id: " + id + ", not found."));

        BeanUtils.copyProperties(employee, employeeFromDb, "id");

        return employeeRepository.save(employeeFromDb);
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public Stream<Employee> listAll() {
        Spliterator<Employee> employees = employeeRepository.findAll()
                .spliterator();

        return StreamSupport.stream(employees, false);
    }
}
