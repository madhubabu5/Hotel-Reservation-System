package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
	private String firstName;
	private String lastName;
	private String email;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, emailRegex, firstName, lastName, pattern);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(email, other.email) && Objects.equals(emailRegex, other.emailRegex)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(pattern, other.pattern);
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailRegex() {
		return emailRegex;
	}

	public Pattern getPattern() {
		return pattern;
	}

	private final String emailRegex = "^(.+)@(.+).com$";
	private final Pattern pattern  = Pattern.compile(emailRegex);
	
	public Customer(String firstName,String lastName,String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.setEmail(email);
		
		// regex
		
		if(!pattern.matcher(this.getEmail()).matches()) {
			throw new IllegalArgumentException("Error , invalid email");
		}

	}
	
	public String toString() {
		return "Customer{\n" + "name="+ this.firstName+this.lastName+"\nemail="+this.getEmail() + "\n}";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
