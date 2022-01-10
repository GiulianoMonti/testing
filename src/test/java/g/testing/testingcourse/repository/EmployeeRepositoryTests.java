package g.testing.testingcourse.repository;


import g.testing.testingcourse.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = Employee.builder()
                .firstName("giuliano")
                .lastName("monti")
                .email("giulianm@gmail.com")
                .build();
    }

    // JUnit test for save employee operation

    @DisplayName("junit test for saving employee")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        // given - precondition or setup

        // when - action or the behaviour that we are going to test

        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);

    }

    @DisplayName("junit test for test all employees operation")
    @Test
    public void givenEmployeeList_whenFindAll_thenEmployeesList() {

        // given - precondition or setup

        Employee employee1 = Employee.builder()
                .firstName("john")
                .lastName("cena")
                .email("cena@gmail.com")
                .build();

        employeeRepository.save(employee);

        employeeRepository.save(employee1);

        // when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();


        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);


    }

    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

        // given - precondition or setup

        employeeRepository.save(employee);
        // when - action or the behaviour that we are going to test

        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
    }


    //junit for get employee by email operation

    @DisplayName("JUnit test for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {

        // given - precondition or setup


        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();

    }

    // JUnit test for update employee operation
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

        // given - precondition or setup

        employeeRepository.save(employee);
        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();

        savedEmployee.setEmail("ram@gmail.com");
        savedEmployee.setFirstName("Ram");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output

        assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ram");

    }

    @Test
    public void givenEmployeeObject_whenDeleteEmployee_then() {

        // given - precondition or setup

        employeeRepository.save(employee);
        // when - action or the behaviour that we are going to test

        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output

        assertThat(employeeOptional).isEmpty();


    }

    // JUnit test for custom query using JPQL with index
    @DisplayName("JUnit test for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {

        // given - precondition or setup

        employeeRepository.save(employee);

        String firstName = "giuliano";
        String lastName = "monti";


        // when -  action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByJPQL(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }


    // JUnit test for custom query using JPQL with named params
    @DisplayName("JUnit test for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {

        // given - precondition or setup

        employeeRepository.save(employee);
        String firstName = "Giuliano";
        String lastName = "Monti";

        // when - action or the behaviour that we are going to test

        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using native SQL with index

    @DisplayName("JUnit test for custom query using native SQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject() {

        // given - precondition or setup

        employeeRepository.save(employee);


        // when - action or the behaviour that we are going to test

        Employee savedEmployee = employeeRepository.findByNativeSQL
                (employee.getFirstName(), employee.getLastName());


        // then - verify the output

        assertThat(savedEmployee).isNotNull();
    }


    // JUnit test for custom query using native SQL with named params

    @DisplayName("JUnit test for custom query using native SQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject() {

        // given - precondition or setup

        employeeRepository.save(employee);


        // when - action or the behaviour that we are going to test

        Employee savedEmployee = employeeRepository.findByNativeSQLNamed
                (employee.getFirstName(), employee.getLastName());


        // then - verify the output

        assertThat(savedEmployee).isNotNull();
    }
}
