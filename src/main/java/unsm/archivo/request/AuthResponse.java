package unsm.archivo.request;

public class AuthResponse 
{
	String token;
	String role;
	String username;
	
	public AuthResponse(String token) {
		super();
		this.token = token;
	}
	public AuthResponse() {
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
