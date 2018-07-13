package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dao.EmployeeDAO;
import com.employee.model.Employee;
import com.employee.model.EmployeeFrom;

@RestController 
public class EmployeeController {
	private static final String EMPLOYEE_WITH_ID = "/employee/{empId}";
	private static final String EMPLOYEES ="/employees";
	private static final String EMPLOYEE="/employee";
    @Autowired
    private EmployeeDAO employeeDAO;
  
 
    @RequestMapping(value = EMPLOYEES, 
            method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, 
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Employee> getEmployees() {
        List<Employee> list = employeeDAO.getAllEmployees();
        return list;
    }
  
  
    @RequestMapping(value = EMPLOYEE_WITH_ID, 
            method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, 
            		MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee getEmployee(@PathVariable("empId") Long empId) {
        return employeeDAO.getEmployee(empId);
    }
  

    @RequestMapping(value = EMPLOYEE, 
            method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, 
            		MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee addEmployee(@RequestBody EmployeeFrom empForm) {
  
        System.out.println("(Service Side) Creating employee with empNo: " + empForm.getEmpNo());
  
        return employeeDAO.addEmployee(empForm);
    }
  

    @RequestMapping(value = EMPLOYEE, 
            method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE, 
            		MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee updateEmployee(@RequestBody EmployeeFrom empForm) {
  
        System.out.println("(Service Side) Editing employee with Id: " + empForm.getEmpId());
  
        return employeeDAO.updateEmployee(empForm);
    }
    
    @RequestMapping(value = EMPLOYEE_WITH_ID, 
            method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE, 
            		MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteEmployee(@PathVariable("empId") Long empId) {
  
        System.out.println("(Service Side) Deleting employee with Id: " + empId);
  
        employeeDAO.deleteEmployee(empId);
    }
}
  