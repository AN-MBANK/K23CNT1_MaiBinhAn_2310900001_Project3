package K23CNT1_Mba_Day04.controller;

import K23CNT1_Mba_Day04.dto.UsersDTO;
import K23CNT1_Mba_Day04.entity.Users;
import K23CNT1_Mba_Day04.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/user-list")
    public List<Users> getAllUsers() {
        return usersService.findAll();
    }

    @PostMapping("/user-add")
    public ResponseEntity<String> addUser(@Valid @RequestBody UsersDTO user) {
        if (usersService.create(user)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully!");
        }
        return ResponseEntity.badRequest().body("Username already exists or failed to create.");
    }
}