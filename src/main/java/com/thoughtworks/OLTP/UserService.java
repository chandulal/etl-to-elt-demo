package com.thoughtworks.OLTP;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.ELT.Extractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserFormToUser userFormToUser;
  @Autowired
  private Extractor extractor;

  ObjectMapper mapper = new ObjectMapper();

  public User saveOrUpdateUser(UserForm userForm) {
    User savedUser = saveOrUpdate(userFormToUser.convert(userForm));
    System.out.println("Saved User Id: " + savedUser.getId());
    return savedUser;
  }

  public User addOrUpdateUser(UserForm userForm) {
    User savedUser = saveOrUpdate(userFormToUser.convert(userForm));
    System.out.println("Saved User Id: " + savedUser.getId());
    userForm.setId(savedUser.getId().toString());
    sendToKafkaProducer(userForm);
    return savedUser;
  }

  private void sendToKafkaProducer(UserForm userForm) {
    JsonNode jsonNode = mapper.valueToTree(userForm);
    extractor.send("helloworld.t", jsonNode);
  }

  public List<User> listAll() {
    List<User> users = new ArrayList<>();
    Iterable<User> all = userRepository.findAll();
    for (User anAll : all) {
      users.add(anAll);
    }
    return users;
  }

  private User saveOrUpdate(User user) {
    userRepository.save(user);
    return user;
  }
}