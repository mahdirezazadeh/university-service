package ir.mahdi.universityservice.config;

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
//        http.authorizeRequests().anyRequest().authenticated();
//        http.authorizeRequests().anyRequest().denyAll();
/*        http.authorizeRequests().anyRequest()
                .hasAuthority("delete");  */
        /*http.authorizeRequests().anyRequest()
                .hasAnyAuthority("delete", "read");*/
/*        http.authorizeRequests().anyRequest()
                .access("hasAnyAuthority('delete')");*/
        /*http.authorizeRequests().anyRequest()
                .hasRole("ROLE_ADMIN");*/

        http.authorizeRequests()
                .mvcMatchers("/admin")
                .hasRole("ADMIN")
                .mvcMatchers("/manager")
                .hasRole("MANAGER")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();

        http.httpBasic();
        http.formLogin();
    }
}