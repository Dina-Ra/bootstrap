package com.controller;

import com.model.Role;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String getUsers(ModelMap model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "show_users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, ModelMap model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        List<Role> roles = user.getRoles().stream().collect(Collectors.toList());
        model.addAttribute("roles", roles);
        return "/show_user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "add_user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("newUser") User user, @ModelAttribute("listRoles") String[] roles) {
        System.out.println(user);
        userService.addUser(user, roles);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(ModelMap modelMap, @PathVariable("id") long id) {
        modelMap.addAttribute("user", userService.getUser(id));
        return "update";
    }

    @PatchMapping("/{id}/patch")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id, @ModelAttribute(
            "listRoles") String[] roles) {
        userService.updateUser(user, id, roles);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin/";
    }

}

