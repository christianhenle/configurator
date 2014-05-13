package de.hs.furtwangen.bam.jee.configurator.web.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Password {

	/*
	 * ( # Start of group (?=.*\d) # must contains one digit from 0-9
	 * (?=.*[a-z]) # must contains one lowercase characters (?=.*[A-Z]) # must
	 * contains one uppercase characters (?=.*[@#$%]) # must contains one
	 * special symbols in the list "@#$%" . # match anything with previous
	 * condition checking {6,20} # length at least 6 characters and maximum of
	 * 20 ) # End of group
	 */

	@NotNull
	private String password1;

	@NotNull
	private String password2;

	@Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20}", message = "Ihr Password ist unsicher")
	private String newPassword;

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public boolean isPasswordEquals() {
		return password1.equals(password2);
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}