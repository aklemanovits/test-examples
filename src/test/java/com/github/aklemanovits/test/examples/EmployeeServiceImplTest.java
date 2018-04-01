package com.github.aklemanovits.test.examples;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author aklemanovits on 2018. 04. 01.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void create_shouldReturnWithAnEntityWithTheGivenName() {
        given(employeeRepository.save(any(Employee.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        Employee test = employeeService.create("Test");

        assertThat(test.getName()).isEqualTo("Test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_shouldCheckNameLenght() {
        employeeService.create("");
    }


    @Test
    public void delete_shouldCallRepositoryToDelete() {
        employeeService.delete(1L);

        verify(employeeRepository,times(1)).deleteById(1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void delete_shouldCheckIdsNullability() {
        employeeService.delete(null);
    }
}