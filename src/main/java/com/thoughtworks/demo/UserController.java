package com.thoughtworks.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/user")
public class UserController {
  @Autowired
  private UserService userService;
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
  private Map<String, String> response = new HashMap<>();

  @RequestMapping(value = "/create",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_XML_VALUE)
  public Map<String, String> postUser(@RequestBody User user) {
    try {
      userService.create(user);
      logger.info("success");
      response.put("status", "success");
    } catch (Exception e) {
      logger.error("Error occurred while trying to process api request", e);
      response.put("status", "fail");
    }

    return response;
  }
}
