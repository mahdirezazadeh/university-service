package ir.mahdi.universityservice.config;

import ir.mahdi.universityservice.config.security.RoleName;
import ir.mahdi.universityservice.config.security.SecurityConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(SecurityConstant.getAdminPaths())
                .hasRole(RoleName.ADMIN)
                .mvcMatchers(SecurityConstant.getStudentPath())
                .hasRole(RoleName.STUDENT)
                .mvcMatchers(SecurityConstant.getTeacherPaths())
                .hasRole(RoleName.TEACHER)
                .mvcMatchers(SecurityConstant.getPermitAllUrls())
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
//                .permitAll();

        http.formLogin()/*.loginPage("/login")*/;
//        http.logout();
    }
}