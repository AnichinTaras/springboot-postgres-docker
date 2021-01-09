package com.anichin.springbootpostgresdocker.ConvertUser;

import com.anichin.springbootpostgresdocker.Dto.UsersDto;
import com.anichin.springbootpostgresdocker.model.Users;
import org.springframework.stereotype.Service;


@Service
public class Converter {

  public UsersDto fromUsersToUsersDto (Users users){
      return UsersDto
              .builder ()
              .id (users.getId ())
              .name (users.getName ())
              .login (users.getLogin ())
              .email (users.getEmail ()).build ();
  }

  public Users fromUsersDtoToUsers(UsersDto usersDto) {

      Users users = new Users ();

      users.setId (usersDto.getId ());
      users.setName (usersDto.getName ());
      users.setLogin (usersDto.getLogin ());
      users.setEmail (usersDto.getEmail ());

      return users;
  }

}
