package com.priyavrat.springboot.thymeleafdemo.controller;

import com.priyavrat.springboot.thymeleafdemo.entity.Employee;
import com.priyavrat.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //Add mapping for listing the employees
    @GetMapping("/list")
    public String listEmployees(Model theModel){
        //get the employees from DB
        List<Employee> theEmployees = employeeService.findAll();

        //Add to spring model
        theModel.addAttribute("employees",theEmployees);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        //Create the module attribute to bind form data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        //Save the employee
        employeeService.save(theEmployee);

        //Use a redirect to prevent the duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId , Model theModel){
        //Get the employee
        Employee theEmployee = employeeService.findById(theId);

        //set employee in the model to propopulate the form
        theModel.addAttribute("employee", theEmployee);

        //send over to our form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){
        //Delete the employee
        employeeService.deleteById(theId);

        //return to employees/list
        return "redirect:/employees/list";
    }

}
