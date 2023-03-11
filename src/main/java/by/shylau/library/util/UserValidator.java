package by.shylau.library.util;

import by.shylau.library.model.User;
import by.shylau.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        try {
            userService.loadUserByUsername(user.getName());
        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("name", "", "Человек c таким именем ужe существует");
    }
}