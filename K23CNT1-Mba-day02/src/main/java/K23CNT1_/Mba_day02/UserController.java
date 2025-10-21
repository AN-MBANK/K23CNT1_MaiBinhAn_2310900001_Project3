package K23CNT1_.Mba_day02;

import K23CNT1_.Mba_day02.pkg_annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUsers() {
        return "<h1>Get all users</h1>";
    }

    @GetMapping("/user/details")
    public String getUserDetails() {
        return userService.getUserDetails();
    }

    @PostMapping("/users")
    public String createUser() {
        return "<h1>User created</h1>";
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable int id) {
        return "<h1>User with ID " + id + " updated</h1>";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        return "<h1>User with ID " + id + " deleted</h1>";
    }
}
