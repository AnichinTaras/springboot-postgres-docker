package com.anichin.springbootpostgresdocker.Controller;


import com.anichin.springbootpostgresdocker.Dto.UsersDto;
import com.anichin.springbootpostgresdocker.model.Users;
import com.anichin.springbootpostgresdocker.service.UsersServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Data
@Log
public class UsersController {

    @Autowired
    private UsersServiceImpl usersService;

    private final String LABEL_CONTROLLER = "Controller works";


     @PostMapping("/saveUser")
     public UsersDto CreateUser(@RequestBody UsersDto usersDto) {
         log.info (LABEL_CONTROLLER + " Save user");
         return usersService.createUser (usersDto);
     }


     @GetMapping("/findUserByLogin")
     public UsersDto findUserByLogin(@RequestParam String login) {
         log.info (LABEL_CONTROLLER + " find user by login " + login);
         return usersService.findByLogin (login);
     }

     @DeleteMapping("/deleteUser/{id}")
     public void deleteUser(@PathVariable Integer id){
         log.info (LABEL_CONTROLLER + " delete user by id " + id);
        usersService.deleteUser (id);
     }

     @GetMapping("/findAllUsers")
     public List <UsersDto> findAll(){
         log.info (LABEL_CONTROLLER + " findAllUsers");
         return usersService.findAll ();
     }

     @PutMapping("/editUser/{id}")
    public ResponseEntity<Users> editUsers (@RequestBody UsersDto usersDto,
                                           @PathVariable Integer id) throws Exception {
         log.info (LABEL_CONTROLLER + " edit users");
         return usersService.editUser (usersDto, id);
     }
}
