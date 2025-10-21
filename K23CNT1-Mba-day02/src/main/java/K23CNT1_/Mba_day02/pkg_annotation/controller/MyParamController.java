package K23CNT1_.Mba_day02.pkg_annotation.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MyParamController {

    // Dùng @RequestParam
    @GetMapping("/users")
    public String findUser(@RequestParam(required = false) String name) {
        if (name == null)
            return "<h1>Searching for all users</h1>";
        return "<h1>Searching for user with name: " + name + "</h1>";
    }

    // Dùng @PathVariable
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable int id) {
        return "<h1>User ID = " + id + "</h1>";
    }
}
///users?name=An: dùng @RequestParam để lấy name.
//
//        /user/5: dùng @PathVariable để lấy id từ đường dẫn.