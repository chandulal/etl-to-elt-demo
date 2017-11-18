package com.thoughtworks.OLTP;

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

  public User saveOrUpdateProductForm(UserForm userForm) {
    User savedUser = saveOrUpdate(userFormToUser.convert(userForm));
    System.out.println("Saved Product Id: " + savedUser.getId());
    return savedUser;
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