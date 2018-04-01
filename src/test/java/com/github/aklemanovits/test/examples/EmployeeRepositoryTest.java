package com.github.aklemanovits.test.examples;

import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import javax.persistence.PersistenceException;

/**
 * @author aklemanovits on 2018. 04. 01.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired EmployeeRepository employeeRepository;

    @Test
    public void saveEntityTest() {
        Employee test = new Employee("Test");

        Employee persitedTest = testEntityManager.persistFlushFind(test);

        assertThat(persitedTest.getId()).isNotNull();
        assertThat(persitedTest.getName()).isEqualTo("Test");
    }

    @Test
    public void findByName_shouldReturnEmployee_WithTheGiveName() {
        Employee entity = testEntityManager.persistFlushFind(new Employee("Test"));

        Employee testEmployee = employeeRepository.findByName("Test").get();

        assertThat(testEmployee.getId()).isEqualTo(entity.getId());
        assertThat(testEmployee.getName()).isEqualTo("Test");
    }

    @Test
    public void findByName_shouldReturnEmpty_IfEmployeeWithTheGivenNameNotFound() {
        Employee entity = testEntityManager.persistFlushFind(new Employee("Test"));

        Optional<Employee> testEmployee = employeeRepository.findByName("Bob");

        assertThat(testEmployee.isPresent()).isFalse();
    }

    @Test(expected = PersistenceException.class)
    public void save_shouldNotAllowMultipleEntitiesWithTheSameName() {
        testEntityManager.persistFlushFind(new Employee("Test"));
        testEntityManager.persistFlushFind(new Employee("Test"));
    }

    @Test(expected = PersistenceException.class)
    public void save_shouldNotAllowEntityWithoutName() {
        testEntityManager.persistFlushFind(new Employee());
    }
}