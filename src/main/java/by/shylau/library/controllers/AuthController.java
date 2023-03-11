package by.shylau.library.controllers;

import by.shylau.library.model.User;
import by.shylau.library.service.RegistrationService;
import by.shylau.library.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, UserValidator userValidator) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/regis")
    public String registrationPage(@ModelAttribute("student") User user) {
        return "auth/regis";
    }

    @PostMapping("/regis")
    public String performRegistration(@ModelAttribute("student") @Valid User user, BindingResult result) {
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return "/auth/regis";
        }
        registrationService.registry(user);
        return "/auth/success";
    }
}