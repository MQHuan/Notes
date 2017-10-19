package com.example.demo.security;

import com.example.demo.dao.SysUserRepository;
import com.example.demo.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserService implements UserDetailsService { //1 自定义需实现　UserDetailsService接口
	@Autowired
	SysUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) { //2　重写 loadUserByUsername方法获得用户
		
		SysUser user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		
		return user; //3 我们当前的用户实现了UserDetails接口，可直接返回给　Spring Security 使用
	}

}
