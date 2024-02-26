package pl.crm.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.crm.model.users.User;
import pl.crm.security.config.SecurityConfig;
import pl.crm.security.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final SecurityConfig securityConfig;

    public UserController(UserService userService, SecurityConfig securityConfig) {
        this.userService = userService;
        this.securityConfig = securityConfig;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form-add-user")
    public String addNewUser(Model model) {
        List<User.Role> roles = List.of(User.Role.values());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roles);
        return "adminAccess/form-add-user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/form-add-user")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        securityConfig.addNewUser(user);
        return "redirect:/users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String user(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("user", users);
        return "users";
    }
}
