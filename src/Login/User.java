package Login;

import java.io.Serializable;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String password;
    private String emailAddress;
	
    private String loginFlag;
    private String userRights;
	
	/**
	 * Create an instance of User.
	 */
	public User() {
	}

	/**
	 * Get the ID of the user.
	 * 
	 * @return the ID of the user.
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Set the ID of the user.
	 * 
	 * @param userID the ID of the user.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * Get the first name of the user.
	 * 
	 * @return the first name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Set the first name of the user.
	 * 
	 * @param firstName the first name of the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Get the middile initial of the user.
	 * 
	 * @return the middle initial of the user.
	 */
	public String getMiddleInitial() {
		return middleInitial;
	}
	
	/**
	 * Set the middle initial of the user.
	 * 
	 * @param middleInitial the middle initial of the user.
	 */
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	
	/**
	 * Get the last name of the user.
	 * 
	 * @return the last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Set the last name of the user.
	 *  
	 * @param lastName the last name of the user.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Get the full name of the user.
	 * 
	 * @return the full name of the user.
	 */
	public String getFullName(){
		return firstName + (middleInitial != null ? " " + middleInitial : "") + " " + lastName;
	}
	
	/**
	 * Get the full name as a 'Last Name, First Name' combination.
	 * 
	 * @return the full name as a 'Last Name, First Name' combination.
	 */
	public String getLastAndFirstName(){
		return lastName + ", " + firstName;
	}
	
	/**
	 * Get the password of the user.
	 * 
	 * @return the password of the user.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set the password of the user.
	 * 
	 * @param password the password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
    
	/**
     * Get the emailAddress of the User.
     *
     * @return the emailAddress of the User.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Set the emailAddress of the User.
     * 
     * @param emailAddress the emailAddress of the User.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

   	public String toString() {
		return getFullName();
	}

	public boolean equals(Object obj) {
		boolean result = false;
		
		if(obj instanceof User){
			User u = (User) obj;
			
			result = userId.equals(u.getUserId()); 
		}
		
		return result;
	}

	public int hashCode() {
		return this.getUserId().hashCode();
	}

	
	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	public String getUserRights() {
		return userRights;
	}

	public void setUserRights(String userRights) {
		this.userRights = userRights;
	}
	
	
}
