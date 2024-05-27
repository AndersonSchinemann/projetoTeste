package com.evision.useraccess.component;
//
//import java.util.Collection;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class CustomAuthenticationToken implements Authentication {
//
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	private final UserDetails userDetails;
//    private final Object credentials;
//    private final Collection<? extends GrantedAuthority> authorities;
//    private boolean authenticated = false;
//
//    public CustomAuthenticationToken(UserDetails userDetails, Object credentials, Collection<? extends GrantedAuthority> authorities) {
//        this.userDetails = userDetails;
//        this.credentials = credentials;
//        this.authorities = authorities;
//    }
//
//    @Override
//    public UserDetails getPrincipal() {
//        return userDetails;
//    }
//
//    @Override
//    public Object getCredentials() {
//        return credentials;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//        this.authenticated = isAuthenticated;
//    }
//
//    @Override
//    public boolean isAuthenticated() {
//        return authenticated;
//    }
//
//	@Override
//	public String getName() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object getDetails() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
//
















