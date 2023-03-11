package by.shylau.library.config;

import by.shylau.library.service.UserService;
import by.shylau.library.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/success", "/auth/regis", "/error").permitAll()
                .antMatchers(HttpMethod.GET,"/students/**").hasAnyRole(Role.ADMIN.name(),
                Role.USER.name())
                .antMatchers(HttpMethod.GET,"/books/**").hasAnyRole(Role.ADMIN.name(),
                Role.USER.name())
                .antMatchers(HttpMethod.POST,"/students/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/books/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PATCH,"/students/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PATCH,"/books/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/students/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/books/**").hasAnyRole(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/students", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(getPasswordEncoder());
    }

    //в аргументы передали силу кодировки
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}