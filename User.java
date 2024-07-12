//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    User.java
// Course:   CS 300 Spring 2023
//
// Author:   Rishabh Jain
// Email:    rvjain@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////


/**
 * The User class represents a Twitter user, storing their username and verification status.
 */
public class User {
  private String username;
  private boolean isVerified;

  /**
   * Constructs a new User object with the given username.
   * @param username the username of the new User object
   */
  public User(String username) {
    if (username == null || username.trim().isEmpty() || username.contains("*")) {
      throw new IllegalArgumentException("Invalid username");
    }
    this.username = username;
    this.isVerified = false;
  }

  /**
   * Returns the username of this User.
   * @return the username of this User
   */
  public String getUsername() {
    return username;
  }

  /**
   * Returns the verification status of this User.
   * @return  true if this User is verified, false otherwise
   */

  public boolean isVerified() {
    return isVerified;
  }

  /**
   * sets the isVerified variable to true
   */
  public void verify() {
    isVerified = true;
  }

  /**
   * Sets the isVerified variable to false
   */
  public void revokeVerification() {
    isVerified = false;
  }

  /**
   * Returns a string representation of this User, including an asterisk if the User is verified.
   * @return a string representation of this User
   */
  @Override
  public String toString() {
    String asterisk = isVerified ? "*" : "";
    return "@" + username + asterisk;
  }
}
