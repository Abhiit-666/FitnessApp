package com.example.fitness.OAuth;

import org.glassfish.jaxb.core.annotation.OverrideAnnotationOf;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/","/home").permitAll() //everyone is authenticated for /  and /home
                    .anyRequest().authenticated()
                    )
                    .oauth2Login(oauth2Login -> 
                        oauth2Login
                            .loginPage("/oauth2/authorization/api-client-oidc")
                            .redirectionEndpoint(redirectionEndpoint ->
                                redirectionEndpoint.baseUri("/oauth2/callback/*"))
                            );
                  }        

}
