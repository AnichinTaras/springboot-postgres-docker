package com.anichin.springbootpostgresdocker.service;

import com.anichin.springbootpostgresdocker.ConvertUser.Converter;
import com.anichin.springbootpostgresdocker.Dto.UsersDto;
import com.anichin.springbootpostgresdocker.Repository.UserRepository;
import com.anichin.springbootpostgresdocker.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Converter converter;

    @Override
    public UsersDto findByLogin (String login) {
        return  converter.fromUsersToUsersDto (userRepository.findByLogin (login));
    }

    @Override
    public List <UsersDto> findAll () {
        return userRepository
                .findAll ()
                .stream ()
                .map (converter :: fromUsersToUsersDto)
                .collect (Collectors.toList ());
    }

    @Override
    public void deleteUser (Integer id) {
        userRepository.deleteById (id);
    }

    @Override
    public UsersDto createUser (UsersDto usersDto) {
        Users savedUsers = userRepository.save (converter.fromUsersDtoToUsers (usersDto));
        return converter.fromUsersToUsersDto (savedUsers);
    }

    @Override
    public ResponseEntity <Users> editUser (UsersDto usersDto, Integer id) throws Exception {
        Users users = userRepository.findById (id)
                .orElseThrow (() -> new Exception());

        users.setName (usersDto.getName ());
        users.setLogin (usersDto.getLogin ());
        users.setEmail (usersDto.getEmail ());

        final Users savedUsers = userRepository.save (users);
        return ResponseEntity.ok (savedUsers);
    }
}
