package lctech.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


import com.dao.UserDao;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider  {

	@Autowired
	private UserDao udao;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		    String name = authentication.getName();
	        String password = authentication.getCredentials().toString();
	        
	        boolean check = udao.check(name, password);
	        if(check){
	        	
	        	List<String> r = udao.getRole(name);
	        
	        	
	        	List<GrantedAuthority> grantedAuths = new ArrayList<>();
	        
	        	
	        	for(String role : r){
		        	
		            grantedAuths.add(new SimpleGrantedAuthority(role));
	        	}
		            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
		            return auth;
	        }else{
	        	
	        	return null;
	        }
	  
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
