package com.thoughtworks.demo;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserFormToUser implements Converter<UserForm, User> {

  @Override
  public User convert(UserForm userForm) {
    User user = new User();
    if (userForm.getId() != null && !StringUtils.isEmpty(userForm.getId())) {
      user.setId(new ObjectId(userForm.getId()));
    }
    user.setFirstName(userForm.getFirstName());
    user.setLastName(userForm.getLastName());
    user.setAge(userForm.getAge());
    return user;
  }
}
