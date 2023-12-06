/*
A Spring Boot application that allows Single-Sign-On(SSO) using Google's OAuth 2.0 service. 
The Spring Security library is used for the OAuth 2.0 authentication and user details are obtained from Google.
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Sso;

@SpringBootApplication
public class oauth_authentication_3_Req28 {

    public static void main(String[] args) {
        SpringApplication.run(oauth_authentication_3_Req28.class, args);
    }

    @Configuration
    @EnableOAuth2Sso
    public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**")
                .permitAll()
                .anyRequest()
                .authenticated();
        }
    }
}