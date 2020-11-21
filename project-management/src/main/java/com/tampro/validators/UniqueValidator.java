package com.tampro.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.tampro.dao.EmployeeRepository;
import com.tampro.entities.Employee;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String>{

	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		System.out.println("Entered validation method..");
		Employee employee = empRepo.findByEmail(value);
		if(employee != null) {
			return false;
		}else {
			return true;
		}
	}

}
