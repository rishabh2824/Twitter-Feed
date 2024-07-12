//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TwiteratorTester.java
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

/**
 * The class that contains all the tester methods to check if all the classes are working properly.
 */

public class TwiteratorTester {

  /**
   * Main method which calls all the testers
   *
   * @param args
   */
  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    Tweet.setCalendar(calendar);


    System.out.println("testUser: " + testUser());
    System.out.println("testTweet: " + testTweet());
    System.out.println("testNode: " + testNode());
    System.out.println("testAddTweet: " + testAddTweet());
    System.out.println("testInsertTweet: " + testInsertTweet());
    System.out.println("testDeleteTweet: " + testDeleteTweet());
    System.out.println("testChronoTwiterator: " + testChronoTwiterator());
    System.out.println("testRatioTwiterator: " + testRatioTwiterator());
    System.out.println("testVerifiedTwiterator: " + testVerifiedTwiterator());
  }

  /**
   * This method tests the User.java class
   * @return true if and only all the tests pass, and false otherwise.
   */
  public static boolean testUser() {
    User user1 = new User("username1");
    User user2 = new User("username2");

    if (!user1.getUsername().equals("username1"))
      return false;
    if (user1.isVerified())
      return false;

    if (!user2.getUsername().equals("username2"))
      return false;
    if (user2.isVerified())
      return false;

    try {
      User user3 = new User(null);
      return false; // The exception should be thrown, so we should not reach this line.
    } catch (IllegalArgumentException e) {
      // Expected exception, do nothing.
    }

    try {
      User user4 = new User("");
      return false; // The exception should be thrown, so we should not reach this line.
    } catch (IllegalArgumentException e) {
      // Expected exception, do nothing.
    }

    return true;
  }

  /**
   * This method tests the Tweet.java class
   * @return true if and only all the tests pass, and false otherwise.
   */
  public static boolean testTweet() {
    Calendar calendar = Calendar.getInstance();
    Tweet.setCalendar(calendar);

    User user = new User("username");
    if (user == null)
      return false;

    Tweet tweet = new Tweet(user, "This is a sample tweet.");

    if (!tweet.getText().equals("This is a sample tweet."))
      return false;
    if (tweet.getTotalEngagement() != 0)
      return false;
    if (tweet.isUserVerified())
      return false;

    tweet.like();
    tweet.retweet();
    if (tweet.getTotalEngagement() != 2)
      return false;

    return true;
  }
  /**
   * This method tests the tweetNode.java class
   * @return true if and only all the tests pass, and false otherwise.
   */
  public static boolean testNode() {
    Calendar calendar = Calendar.getInstance();
    Tweet.setCalendar(calendar);

    User user = new User("username");
    Tweet tweet1 = new Tweet(user, "This is tweet 1.");
    Tweet tweet2 = new Tweet(user, "This is tweet 2.");
    TweetNode node1 = new TweetNode(tweet1);
    TweetNode node2 = new TweetNode(tweet2);

    if (node1.getTweet() == null)
      return false;
    if (node1.getNext() != null)
      return false;

    node1.setNext(node2);
    if (node1.getNext() != node2)
      return false;
    if (node2.getNext() != null)
      return false;

    // Additional check for two-argument constructor
    Tweet tweet3 = new Tweet(user, "This is tweet 3.");
    TweetNode node3 = new TweetNode(tweet2, node1);
    if (node3.getTweet() == null)
      return false;
    if (node3.getNext() != node1)
      return false;

    return true;
  }

  /**
   * This method tests the add method in Tweet.java class
   * @return true if and only all the tests pass, and false otherwise.
   */
  public static boolean testAddTweet() {
    User user = new User("username");
    Tweet tweet1 = new Tweet(user, "This is tweet 1.");
    Tweet tweet2 = new Tweet(user, "This is tweet 2.");
    TwitterFeed feed = new TwitterFeed();
    if (feed.size() != 0)
      return false;

    feed.addFirst(tweet1);
    if (feed.size() != 1)
      return false;
    if (!feed.get(0).equals(tweet1))
      return false;
    if (!feed.getHead().equals(tweet1))
      return false;
    if (!feed.getTail().equals(tweet1))
      return false;
    feed.addLast(tweet2);
    if (feed.size() != 2)
      return false;
    if (!feed.get(1).equals(tweet2))
      return false;
    if (!feed.getHead().equals(tweet1))
      return false;
    if (!feed.getTail().equals(tweet2))
      return false;

    return true;
  }

  /**
   * This method tests the insert method in Tweet.java class
   * @return true if and only all the tests pass, and false otherwise.
   */
  public static boolean testInsertTweet() {
    User user = new User("username");
    Tweet tweet1 = new Tweet(user, "This is tweet 1.");
    Tweet tweet2 = new Tweet(user, "This is tweet 2.");
    Tweet tweet3 = new Tweet(user, "This is tweet 3.");
    TwitterFeed feed = new TwitterFeed();
    feed.add(0, tweet1);
    feed.add(1, tweet2);
    feed.add(1, tweet3);

    if (feed.size() != 3)
      return false;
    if (!feed.get(0).equals(tweet1))
      return false;
    if (!feed.get(1).equals(tweet3))
      return false;
    if (!feed.get(2).equals(tweet2))
      return false;

    return true;
  }

  /**
   * This method tests the delete method in tweet.java class
   * @return true if and only all the tests pass, and false otherwise.
   */
  public static boolean testDeleteTweet() {
    User user = new User("username");
    Tweet tweet1 = new Tweet(user, "This is tweet 1.");
    Tweet tweet2 = new Tweet(user, "This is tweet 2.");
    Tweet tweet3 = new Tweet(user, "This is tweet 3.");
    TwitterFeed feed = new TwitterFeed();
    feed.addFirst(tweet1);
    feed.addLast(tweet2);
    feed.addLast(tweet3);

    feed.delete(2);
    if (feed.size() != 2)
      return false;
    if (!feed.getTail().equals(tweet2))
      return false;

    feed.delete(0);
    if (feed.size() != 1)
      return false;
    if (!feed.getHead().equals(tweet2))
      return false;

    return true;
  }

  /**
   * This method tests the chronoTwiterator.java class
   * @return true if and only all the tests pass, and false otherwise.
   */
  public static boolean testChronoTwiterator() {
    User user = new User("username");
    Tweet tweet1 = new Tweet(user, "This is tweet 1.");
    Tweet tweet2 = new Tweet(user, "This is tweet 2.");
    Tweet tweet3 = new Tweet(user, "This is tweet 3.");
    TwitterFeed feed = new TwitterFeed();
    feed.addFirst(tweet1);
    feed.addLast(tweet2);
    feed.addLast(tweet3);

    int index = 0;
    for (Tweet tweet : feed) {
      if (!tweet.equals(feed.get(index))) {
        return false;
      }
      index++;
    }

    return true;
  }

  /**
   * This method tests the ratioTwiterator.java class
   * @return true if and only all the tests pass, and false otherwise.
   */
  public static boolean testRatioTwiterator() {
    User user = new User("username");
    Tweet tweet1 = new Tweet(user, "This is tweet 1.");
    Tweet tweet2 = new Tweet(user, "This is tweet 2.");
    Tweet tweet3 = new Tweet(user, "This is tweet 3.");
    tweet1.like();
    tweet1.like();
    tweet2.like();
    tweet2.like();
    TwitterFeed feed = new TwitterFeed();
    feed.addFirst(tweet1);
    feed.addLast(tweet2);
    feed.addLast(tweet3);

    double threshold = 0.5;
    int index = 0;
    for (Tweet tweet : feed) {
      if (index == 0 || index == 1) {
        if (!tweet.equals(feed.get(index))) {
          return false;
        }
      } else if (index == 2) {
        if (tweet.getLikesRatio() >= threshold) {
          return false;
        }
      }
      index++;
    }

    return true;
  }

  /**
   * This method tests the verifiedTwiterator.java class
   * @return true if and only all the tests pass, and false otherwise.
   */
  public static boolean testVerifiedTwiterator() {
    User user1 = new User("user1");
    User user2 = new User("user2");
    User user3 = new User("user3");

    Tweet tweet1 = new Tweet(user1, "This is tweet 1.");
    Tweet tweet2 = new Tweet(user2, "This is tweet 2.");
    Tweet tweet3 = new Tweet(user3, "This is tweet 3.");
    Tweet tweet4 = new Tweet(user1, "This is tweet 4.");
    Tweet tweet5 = new Tweet(user2, "This is tweet 5.");
    Tweet tweet6 = new Tweet(user3, "This is tweet 6.");

    tweet1.isUserVerified();
    tweet2.isUserVerified();
    tweet4.isUserVerified();

    TwitterFeed feed = new TwitterFeed();
    feed.addFirst(tweet1);
    feed.addLast(tweet2);
    feed.addLast(tweet3);
    feed.addLast(tweet4);
    feed.addLast(tweet5);
    feed.addLast(tweet6);

    int index = 0;
    for (Tweet tweet : feed) {
      if (index == 0 || index == 3) {
        if (!tweet.equals(feed.get(index))) {
          return false;
        }
      } else if (index == 1 || index == 4) {
        if (!tweet.equals(feed.get(index))) {
          return false;
        }
      } else if (index == 2 || index == 5) {
        if (tweet.isUserVerified()) {
          return false;
        }
      }
      index++;
    }

    return true;
  }


}
