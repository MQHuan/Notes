package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/login").permitAll()//设置 Spring Security对 / 和 /login 路径不拦截
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")// 设置 Spring Security的登录页面访问的路径为/login
                .defaultSuccessUrl("/chat")// 登录成功后转向/chat路径
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 在内存中分别配置两个用户mai和dime , 用户名和密码一致，角色是USER
        auth
                .inMemoryAuthentication()
                .withUser("mai").password("mai").roles("USER")
        .and()
        .withUser("demi").password("demi").roles("USER");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // /resources/static 目录下的静态资源， Spring Security不拦截
        web.ignoring().antMatchers("/resources/static/**");
    }
}
