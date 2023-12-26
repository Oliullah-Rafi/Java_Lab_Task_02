package app.controller;

import app.domain.User;
import app.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("")
    public List<User> list() throws SQLException {
        return userService.list();
    }

    @PostMapping("")
    public String store(@Valid @RequestBody User user, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "Invalid Request";
        }
        userService.create(user);
        return "Success";
    }
    @PutMapping("")
    public String update(@Valid @RequestBody User user, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "Invalid Request";
        }
        userService.update(user);
        return "Success";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        try {
            userService.delete(id);
        } catch (Exception ex) {
            return "Failed";
        }

        return "Successful";
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id) throws SQLException {
        return userService.get(id);
    }
    @GetMapping("/count")
    public int count() throws SQLException {
        return userService.count();
    }

}
