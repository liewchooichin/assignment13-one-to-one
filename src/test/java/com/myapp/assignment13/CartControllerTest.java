package com.myapp.assignment13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@WebMvcTest(EmployeeController.class)
@SpringBootTest
@AutoConfigureMockMvc 
@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

  /* Test the test */
  // @Test
  // public void test1(){
  //   assertEquals(1, 1, "message: true");
  // }

  // Autowired fields
  @Autowired
  private MockMvc mockie;

  // object mapper to create json string
  @Autowired
  private ObjectMapper objectMapper;


  // mock a repository
  // then inject the mock repository into the service
  /*
  @Mock
  private EmployeeRepository employeeRepository;
  @InjectMocks
  EmployeeServiceImpl employeeService;  
  
  @Mock
  private AddressRepository addressRepository;
  @InjectMocks
  AddressServiceImpl addressService;
  */

  // GET one employee - OK
  @Test
  public void testGetOneCart_Success() throws Exception {
    // create a sample

    // 1. get the request uri
    // IMPORTANT: The problem is with the generated id. Need to check the generated id before
    // running the test.
    // Check the value against something permanent, i.e. something that we set like name.
    RequestBuilder request = MockMvcRequestBuilders.get("/cart/2");
    // 2. perform the request and get response
    mockie.perform(request)
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.status").value("Successful"))
      .andExpect(jsonPath("$.data.cartModelName"). hasJsonPath())
      .andExpect(jsonPath("$.data.cartModelName")
        .value("Multi-purpose cooking oven"));     
  }

    // GET one employee - No such employee
    @Test
    public void testGetOneCart_Fail() throws Exception {
      // create a sample
  
      // 1. get the request uri
      RequestBuilder request = MockMvcRequestBuilders.get("/cart/99");
      // 2. perform the request and get response
      mockie.perform(request)
        .andDo(print())
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$.status").value("Failed"));
    }

  // Get all employees
  @Test
  public void getAllCarts() throws Exception {
    // Build the request
    RequestBuilder request = MockMvcRequestBuilders.get("/cart/all");
    // perform the request
    mockie.perform(request)
      .andDo(print())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.status").value("Successful"));
  }
  
  // Create an employee
  @Test
  public void testCreateCart_Success() throws Exception {
    // Create an employee 
    CartModel cartModel = new CartModel();
    cartModel.setCartModelId(1001L);
    cartModel.setCartModelName("Product 1");
    cartModel.setCartModelPrice(10.00);
    cartModel.setCartModelShortDesc("Product 1 description");
    cartModel.setCartModelQuantity(1000);
    // Convert to Json object
    String newCartJson = objectMapper.writeValueAsString(cartModel);
    // build the request
    RequestBuilder request = MockMvcRequestBuilders
      .post("/cart/save")
      .contentType(MediaType.APPLICATION_JSON)
      .content(newCartJson);
    // perform the request
    mockie.perform(request)
      .andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.status").value("Successful")); // 1 - OK
  }

  // search for employee's name
  @Test
  public void searchCartNameTest() throws Exception{
    // build the request
    RequestBuilder request = MockMvcRequestBuilders
      .get("/cart/search?catalogueName=super");
    // perform the request
    mockie.perform(request)
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.data.[0].cartModelName").hasJsonPath())
      .andExpect(jsonPath("$.data.[0].cartModelName").value("Super useful cutting set"));
  }

} // END OF CLASS
