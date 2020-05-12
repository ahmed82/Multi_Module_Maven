package com.rbs.multimodule.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@Column(name = "id",updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstname;
	
	@Column(name = "last_name", nullable = false, length = 50)
    private String lastname;
	
    private String address;
    
    @Column(nullable = true)
    private String phone;
    
    protected Employee() {}
    
    
	@Override
	public String toString() {
		return "Employee [Id=" + Id + ", firstName=" + firstname + ", lastName=" + lastname + ", address=" + address
				+ ", phone=" + phone + "]";
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	


}
