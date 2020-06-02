package com.rbs.multimodule.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rbs.multimodule.backend.exception.UserNotFoundException;
import com.rbs.multimodule.backend.model.Employee;
import com.rbs.multimodule.backend.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	

    @Autowired
    private EmployeeRepository employeeService;

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

 /*   @RequestMapping(path = "/employee/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addNewUser (@PathVariable("empid") long id,
    						@PathVariable("lastName") String lastName,
    						@PathVariable("firstName") String firstName,
    						@PathVariable("address") String address,
    						@PathVariable("phone") String phone) {
    	Employee savedUser = employeeService.save(new Employee(id, firstName, lastName, address, phone));

        LOG.info(savedUser.toString() + " successfully saved into DB");

        return savedUser.getId();
    }

    @GetMapping(path = "/user/{id}")
    public Employee getUserById(@PathVariable("id") long id) {

        return employeeService.findById(id).map(emp -> {
            LOG.info("Reading user with id " + id + " from database.");
            return emp;
        }).orElseThrow(() -> new UserNotFoundException("The user with the id " + id + " couldn't be found in the database."));
    }


    @RequestMapping(value = "{_:^(?!index\\.html|api).*$}")
    public String redirectApi() {
        LOG.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }*/
    
    
    @GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return employeeService.findAll();
	}
	
   

	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable long id) {
		
		Optional<Employee> employee = employeeService.findById(id);
		if (employee == null){
			throw new UserNotFoundException("Cannot Find Employee with ID: "+id);
		}
		
		return employee;
	}

	@GetMapping(value="/employees/" )
	public ResponseEntity<?> getEmployeeById2(@Valid @RequestParam("id") long id) {
		
		Optional<Employee> e = employeeService.findById(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Ahmed-key", "RBS-Value-1");
		httpHeaders.add("Sean-key", "RBS-Value-2");
		httpHeaders.add("Brian-key", "RBS-Value-3");
		if (e != null ){
			return new ResponseEntity<>(e, httpHeaders, HttpStatus.OK);
		}else
		//return new ResponseEntity("Not found", httpHeaders, HttpStatus.NOT_FOUND);
			throw new NullPointerException("Employee Not found for the given id: "+id);
		
	}
	

	@PostMapping("/employees")
	public ResponseEntity<Boolean> AddEmployee(@RequestBody Employee emp){
		employeeService.save(emp);

		HttpHeaders httpHeaders = new HttpHeaders();
		/* Http Headers return the new created Employee API url by build the link
		 *  on fly and send it back in the header respond to fetch that particular Employee
		 *  Best practice rather than return the created Employee   */
		httpHeaders.add(
						"location",
						ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(emp.getId()).toUri()
						.toString());
		

		return new ResponseEntity <Boolean>(true,httpHeaders,HttpStatus.CREATED);

	}
	

	@PatchMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee emp, @PathVariable long id) {
		try {
			emp.setId(id);
			return employeeService.save(emp);
		} catch (Exception exc) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Foo Not Found", exc);
		}
		
		
	}	

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/employees")
	public @ResponseBody void deleteEmployees(@RequestBody List<Employee> emps) {
		employeeService.deleteAll(emps); 

	}

}
