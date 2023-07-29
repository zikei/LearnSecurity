package com.example.learnSecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.learnSecurity.data.Login;
import com.example.learnSecurity.entity.Account;
import com.example.learnSecurity.repository.AccountRepository;

@Service
public class DbUserDetailsService implements UserDetailsService {
	@Autowired
	AccountRepository accountRepo;
	
	@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Account> accountOpt = accountRepo.findByUserName(userName);
		Account user = accountOpt.orElseThrow(() -> new UsernameNotFoundException("NotFound UserName: " + userName));

        return new Login(user, AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
