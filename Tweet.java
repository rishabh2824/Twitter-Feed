//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Tweet.java
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

import java.util.Calendar;
import java.util.Date;

/**
 * A class representing a tweet, containing information such as its text, user, engagement metrics,
 * and timestamp.
 */
public class Tweet {
  private static Calendar dateGenerator;
  private static final int MAX_LENGTH = 280;
  private User user;
  private String text;
  private int numLikes;
  private int numRetweets;
  private Date timestamp;

  /**
   * Constructs a new Tweet object with the given user and text. The timestamp is set to the current time.
   * @param user The user who created the tweet.
   * @param text  The text content of the tweet.
   */
  public Tweet(User user, String text) {
    if (dateGenerator == null) {
      throw new IllegalStateException("dateGenerator has not been initialized");
    }
    if (user == null || text == null) {
      throw new NullPointerException("User or text cannot be null");
    }
    if (text.length() > MAX_LENGTH) {
      throw new IllegalArgumentException("Tweet text exceeds maximum length");
    }
    this.user = user;
    this.text = text;
    this.numLikes = 0;
    this.numRetweets = 0;
    this.timestamp = dateGenerator.getTime();
  }

  /**
   * Sets the calendar object used to generate timestamps for new Tweet objects.
   * @param c The calendar object to be used for generating timestamps.
   */
  public static void setCalendar(Calendar c) {
    dateGenerator = c;
  }

  /**
   * Returns the text content of the tweet.
   * @return The text content of the tweet.
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the total number of engagements (likes and retweets) of the tweet.
   * @return The total number of engagements (likes and retweets) of the tweet.
   */
  public int getTotalEngagement() {
    return numLikes + numRetweets;
  }

  /**
   * Returns whether the user who created the tweet is verified.
   * @return true if the user who created the tweet is verified, false otherwise.
   */
  public boolean isUserVerified() {
    return user.isVerified();
  }

  /**
   * Returns the ratio of likes to total engagements (likes and retweets) of the tweet.
   * @return The ratio of likes to total engagements (likes and retweets) of the tweet.
   */
  public double getLikesRatio() {
    int totalEngagement = getTotalEngagement();
    if (totalEngagement == 0) {
      return -1;
    }
    return (double) numLikes / totalEngagement;
  }

  /**
   * Increments the number of likes of the tweet by 1.
   */
  public void like() {
    numLikes++;
  }

  /**
   * Increments the number of retweets of the tweet by 1.
   */
  public void retweet() {
    numRetweets++;
  }

  /**
   *
   * @param o Returns true if this tweet is equal to the given object.
   * @return true if this tweet is equal to the given object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tweet tweet = (Tweet) o;

    boolean userEquals = (user == null ? tweet.user == null : user.toString().equals(tweet.user.toString()));
    boolean textEquals = (text == null ? tweet.text == null : text.equals(tweet.text));

    if (!userEquals || !textEquals) {
      return false;
    }

    if (timestamp == null && tweet.timestamp == null) {
      return true;
    } else if (timestamp == null || tweet.timestamp == null) {
      return false;
    } else {
      Calendar calendar1 = Calendar.getInstance();
      Calendar calendar2 = Calendar.getInstance();
      calendar1.setTime(timestamp);
      calendar2.setTime(tweet.timestamp);

      return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
          && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
          && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
          && calendar1.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY)
          && calendar1.get(Calendar.MINUTE) == calendar2.get(Calendar.MINUTE)
          && calendar1.get(Calendar.SECOND) == calendar2.get(Calendar.SECOND);
    }
  }

  /**
   * Returns a string representation of the tweet.
   * @return a string representation of the tweet
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("tweet from @").append(user.getUsername());
    if (user.isVerified()) {
      sb.append("*");
    }
    sb.append(" at ").append(timestamp).append(":\n");
    sb.append("-- ").append(text).append("\n");
    sb.append("-- likes: ").append(numLikes).append("\n");
    sb.append("-- retweets: ").append(numRetweets);
    return sb.toString();
  }
}
