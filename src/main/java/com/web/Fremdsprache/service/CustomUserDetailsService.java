package com.web.Fremdsprache.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.Fremdsprache.entity.mongodb.Preference;
import com.web.Fremdsprache.entity.mongodb.Role;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.repositories.PreferenceRepository;
import com.web.Fremdsprache.repositories.RoleRepository;
import com.web.Fremdsprache.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private  PreferenceRepository preferenceRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByEmail(email);
		 
			if(user.isPresent()) {
		        List<GrantedAuthority> authorities = getUserAuthority(user.get().getRoles());
		        return buildUserForAuthentication(user.get(), authorities);
		    } else {
		        throw new UsernameNotFoundException("username not found");
		    }
		    
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	public Optional<User> findUserByEmail(String email) {
	    return userRepository.findByEmail(email);
	}

	public void saveUser(User user, String role, boolean enabled) {
	    
		long id = generateId();
		
	    Role userRole = new Role();
	    userRole.setId(id);
	    userRole.setRole(role);
	    
	    Preference preference = new Preference();
	    preference.setId(id);
	    preference.setDate_of_registration(new Date());
	    preference.setStatus("okey");
	    preference.setExperience(0L);
	    preference.setEnabled(enabled);
	    
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setRoles(new HashSet<>(Arrays.asList(userRole)));
	    user.setId(id);
	    user.setPreference(new HashSet<>(Arrays.asList(preference)));
	    
	    preferenceRepository.save(preference);
	    roleRepository.save(userRole);
	    userRepository.save(user);
	    
	}

	private Long generateId() {
		
		Optional<User> found = userRepository.findFirstByOrderByIdDesc();
		
		if(found.isPresent())
		return found.get().getId()+1L;
		else
		return 1l;
	}
	
}