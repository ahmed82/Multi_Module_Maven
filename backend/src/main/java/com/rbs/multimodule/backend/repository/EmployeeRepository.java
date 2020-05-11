package com.rbs.multimodule.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rbs.multimodule.backend.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByLastName(@Param("lastname") String lastname);

	List<Employee> findByFirstName(@Param("firstname") String firstname);

}
