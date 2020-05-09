package com.rbs.multimodule.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.rbs.multimodule.backend.domin.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByLastName(@Param("lastname") String lastname);

	List<Employee> findByFirstName(@Param("firstname") String firstname);

}
