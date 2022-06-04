package com.example.demo;

import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.Controller.EmployeeController;
import com.example.demo.Repo.EmployeeRepository;
import com.example.demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=EmployeeController.class)
class EmployeeControllerTests {
	@MockBean
	private EmployeeRepository employeeRepository;
	 @Autowired
	   private MockMvc mvc;

	   @Test
	   public void saveEmployee() throws Exception {
	      mvc.perform(MockMvcRequestBuilders.post("/saveemployee")
	    		  .content(asJsonString(new Employee(1, "Priyanka", "Engg", 1000)))
	    	      .contentType(MediaType.APPLICATION_JSON)
	    	      .accept(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
	    	      .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(1000.0))
	    	      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Priyanka"))
	    	      .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("Engg"));
	   }

	   public static String asJsonString(final Object obj) {
		    try {
		        return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		}
	  
	   @Test
	   public void updateEmployee() throws Exception 
	   {
	     mvc.perform( MockMvcRequestBuilders
	         .put("/updateemployee")
	         .content(asJsonString(new Employee(2, "Aditya", "Eco", 2000)))
	         .contentType(MediaType.APPLICATION_JSON)
	         .accept(MediaType.APPLICATION_JSON))
	         .andExpect(status().isOk())
   	      .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(2000.0))
   	      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Aditya"))
   	      .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("Eco"));
	   }
	   
	   @Test
	   public void deleteEmployee() throws Exception 
	   {
	     mvc.perform( MockMvcRequestBuilders
	         .delete("/deleteemployee/{id}",3))
	         .andExpect(status().isOk());
	   }
}
