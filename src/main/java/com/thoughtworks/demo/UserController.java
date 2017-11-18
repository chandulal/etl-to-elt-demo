package com.thoughtworks.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/user")
public class UserController {
  @Autowired
  private UserService userService;
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
  private Map<String, String> response = new HashMap<>();
  ObjectMapper mapper = new ObjectMapper();

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public Map<String, String> postUser(@RequestBody UserForm user) {
    try {
      userService.saveOrUpdateProductForm(user);
      logger.info("success");
      response.put("status", "success");
    } catch (Exception e) {
      logger.error("Error occurred while trying to process api request", e);
      response.put("status", "fail");
    }
    return response;
  }
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public Map<String, String> getAllUsers() {
    try {
      List<User> users = userService.listAll();
      logger.info("success");
      String jsonInString = mapper.writeValueAsString(users);
      response.put("users", jsonInString);
    } catch (Exception e) {
      logger.error("Error occurred while trying to process api request", e);
      response.put("status", "fail");
    }
    return response;
  }
}
