package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.dao.EmployeeDAO;
import org.example.domain.Employee;

import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private final EmployeeDAO employeeDAO;

    public EmployeeResource(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GET
    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    @GET
    @Path("/{id}")
    public Employee getEmployeeById(@PathParam("id") Long id) {
        return employeeDAO.findById(id);
    }

    @POST
    public Employee createEmployee(Employee employee) {
        return employeeDAO.create(employee);
    }

    @PUT
    @Path("/{id}")
    public Employee updateEmployee(@PathParam("id") Long id, Employee employee) {
        employee.setId(id);
        return employeeDAO.update(employee);
    }

    @DELETE
    @Path("/{id}")
    public void deleteEmployee(@PathParam("id") Long id) {
        Employee employee = employeeDAO.findById(id);
        if (employee != null) {
            employeeDAO.delete(employee);
        }
    }

}
