package com.rhee.shoppingmall.login;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.rhee.shoppingmall.admin.AdminUriConstants;
import com.rhee.shoppingmall.tiles.MainUriConstants;



@Configuration
@EnableWebSecurity
public class ShoppingMallSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private ShoppingmallAuthenticationProvider authProvider; 
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/index.do", LoginUriConstants.signinPath+"/**", LoginUriConstants.loginUri, "/authenticate.do", LoginUriConstants.endUri).permitAll()
                .antMatchers(MainUriConstants.mainPath+"/**").permitAll()
                .antMatchers("/menu/**").permitAll()
                .antMatchers(AdminUriConstants.adminPath+"/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/**").authenticated();
                
        		
        
         
		
		  http.formLogin() 
		   // default
		  .loginPage("/login.do")
		  .loginProcessingUrl(LoginUriConstants.authenticateUri)
		  .failureUrl(LoginUriConstants.endUri)
		  .defaultSuccessUrl("/", true)
		  .usernameParameter("userId") .passwordParameter("password");
		 
                
 
        http.logout()
                .logoutUrl("/logout") // default
                .logoutSuccessUrl("/")
                .permitAll();
        		
        http.csrf().ignoringAntMatchers("/**");
	}
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authProvider);
	}
	
	
}
