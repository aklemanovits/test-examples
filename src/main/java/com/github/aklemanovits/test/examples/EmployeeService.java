package com.github.aklemanovits.test.examples;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author aklemanovits on 2018. 04. 01.
 */
public interface EmployeeService {

    Employee create(String name);

    void delete(Long id);

    Employee update(Long id, Employee employee);

    Optional<Employee> findByName(String name);

    Stream<Employee> listAll();
}
