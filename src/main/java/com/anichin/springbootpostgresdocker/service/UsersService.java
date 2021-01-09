package com.anichin.springbootpostgresdocker.service;

import com.anichin.springbootpostgresdocker.Dto.UsersDto;
import com.anichin.springbootpostgresdocker.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UsersService {

   UsersDto findByLogin (String login);

   List <UsersDto> findAll ();

   void deleteUser(Integer id);

   UsersDto createUser(UsersDto usersDto);

   ResponseEntity <Users> editUser (UsersDto usersDto, Integer id) throws Exception;
}
