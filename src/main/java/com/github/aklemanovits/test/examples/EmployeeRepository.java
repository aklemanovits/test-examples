package com.github.aklemanovits.test.examples;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author aklemanovits on 2018. 04. 01.
 */

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Optional<Employee> findByName(String name);
}
