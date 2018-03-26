package org.spring.springboot.controller.user;

import org.spring.springboot.domain.User;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseBean registerOneUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/user/loginIn", method = RequestMethod.POST)
    public ResponseBean UserLoginIn(@RequestBody User user) {
        return userService.login(user);
    }

}
