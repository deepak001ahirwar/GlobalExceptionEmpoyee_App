package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmptyInputException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.EmpCURDRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmpoyeeServiceImpTest {
    @Mock
    private EmpCURDRepo emprepo;

//    @InjectMocks
//    private EmpoyeeServiceImp empoyeeServiceImp;

    private EmpoyeeServiceInterface empoyeeServiceInterface;
    private Employee tempemp;

    @BeforeEach
    void assignValuesandsetup() {

        this.empoyeeServiceInterface = new EmpoyeeServiceImp(this.emprepo);
        tempemp = Employee.builder().empId(1L).name("Ram").empDepatment("IT").build();


    }

    @Test
    void addEmployeeTest() {
        when(emprepo.save(Mockito.any())).thenReturn(tempemp);
        Assertions.assertNotNull(empoyeeServiceInterface.addEmployee(tempemp));
    }

    @Test
    void getAllEmpTest() {
//        when(emprepo.findAll()).thenReturn(createemployeestubList().get());
//        //  testedemp from EmpimpALL Emp  services class
//        List<Employee> allEmp = empoyeeServiceImp.getAllEmp();
//
//        // comPARE THE assert
//        assertEquals(allEmp.size(),createemployeestubList().get().size());

        when(emprepo.findAll()).thenReturn(createemployeestubList().get());
        Assertions.assertNotNull(empoyeeServiceInterface.getAllEmp());


    }

    private Optional<List<Employee>> createemployeestubList() {
        List<Employee> list = new ArrayList<>();
        Employee tempemp = Employee.builder().empId(4L).name("Ram").empDepatment("IT").build();
        Employee tempemp2 = Employee.builder().empId(2L).name("Kumar").empDepatment("CS").build();
        list.add(tempemp);
        list.add(tempemp2);
        return Optional.of(list);
    }


    @Test
    void getEmpbyIdTest() {
        // from moke emp obj
//        when(emprepo.findById(1L)).thenReturn(createemployeestub());
//
//        //testedemp from Empimp services class
//        Employee testedemp = empoyeeServiceImp.getEmpbyId(1L);
//
//        // compare the original and mock obj anme
//        assertEquals(testedemp.getName(), "Ram");

        when(emprepo.findById(Mockito.any())).thenReturn(Optional.of(tempemp));
        Assertions.assertNotNull(empoyeeServiceInterface.getEmpbyId(Mockito.any()));


    }

//    private Optional<Employee> createemployeestub() {
//        Employee tempemp = Employee.builder().empId(1L).name("Ram").empDepatment("IT").build();
//        return Optional.ofNullable(tempemp);
//    }


    @Test
    void updateEmpbyIdTest() {

        // mock the data
        Long id = 1L;
        Employee originalEmp = tempemp;
        Employee needtoupdateEmp = Employee.builder().empId(null).name("Gopal").empDepatment("IT").build();


        // Mock the behavior of empRepo.findById
        when(emprepo.findById(id)).thenReturn(Optional.ofNullable(originalEmp));

        // Mock the behavior of empRepo.save


        // Call the service method
        Employee result = empoyeeServiceInterface.updateEmpbyId(needtoupdateEmp, id);


        // Verify the interactions and assertions
        verify(emprepo, times(1)).findById(id);
        verify(emprepo, times(1)).save(originalEmp);
        assertEquals(needtoupdateEmp.getName(), result.getName());
        assertEquals(needtoupdateEmp.getEmpDepatment(), result.getEmpDepatment());


//        when(emprepo.save(tempemp)).thenReturn(getEmpByIdStub());
//        Assertions.assertNotNull(empoyeeServiceInterface.updateEmpbyId(getEmpByIdStub(), 1L));

        //when(emprepo.)
//        Employee updateEmpbyId = empoyeeServiceImp.updateEmpbyId(new Employee(null, "Gopal", "EC"), 5L);

    }

    private Employee getEmpByIdStub() {

        Employee storeemp = Employee.builder().empId(1L).name("Ram").empDepatment("IT").build();
        return storeemp;
    }

    @Test
    void deleteEmpbyIdTest() {

        // mock the data
        Long id = 1L;
        Employee originalEmp = tempemp;

        // Mock the behavior of empRepo.findById
        when(emprepo.findById(id)).thenReturn(Optional.of(originalEmp));

        // Mock the behavior of empRepo.deletebyId
        doNothing().when(emprepo).deleteById(id);

        // Call the service method
        String result = empoyeeServiceInterface.deleteEmpbyId(id);

        // Verify the interactions and assertions
        verify(emprepo, times(1)).findById(id); // Verify that findById was called once
        verify(emprepo, times(1)).deleteById(id); // Verify that deleteById was called once
        assertEquals("empId " + id + " has been sucessfully delete ", result);


//        empoyeeServiceInterface.deleteEmpbyId(1L);
//        verify(emprepo,times(1)).deleteById(1L);
//        when(emprepo.deleteById(Mockito.any())).thenReturn()

    }

    @Test
    void ResourceNotFoundExceptionTest() {
        when(emprepo.findById(3L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> empoyeeServiceInterface.getEmpbyId(3L));
        Assertions.assertThrows(ResourceNotFoundException.class, () -> empoyeeServiceInterface.updateEmpbyId(getEmpByupdateStub(), 3L));
        Assertions.assertThrows(ResourceNotFoundException.class, () -> empoyeeServiceInterface.deleteEmpbyId(3L));


    }

    private Employee getEmpByupdateStub() {

        Employee storeemp = Employee.builder().empId(6L).name("Ram").empDepatment("IT").build();
        return storeemp;
    }


    @Test
    void EmptyInputExceptionTest() {
        // mock the data
        Employee empToadd = Employee.builder().name("").empDepatment("IT").build();

        // Mock the behavior of empRepo.findById

        // Call the service method
        try {
            empoyeeServiceInterface.addEmployee(empToadd);
        } catch (EmptyInputException e) {

            assertEquals("Empoyee name does  not correct or not be empty", e.getMessage());
            assertEquals("Bad request", e.getErrorCode());
//            assertArrayEquals(new Object[]{empToadd}, e.getDetails());
        }
        // Verify the interactions and assertions
        verify(emprepo, never()).save(any(Employee.class)); // Verify that save was not called


//        when(emprepo.save(empToadd).thenReturn(empToadd));
//        Assertions.assertThrows(EmptyInputException.class,()->empoyeeServiceInterface.addEmployee(empToadd));


    }

    private Employee getEmpByEmptyStub() {

        Employee storeemp = Employee.builder().empId(6L).name("").empDepatment("IT").build();
        return storeemp;
    }

}