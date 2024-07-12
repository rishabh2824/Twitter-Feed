//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TweetNode.java
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
 * Represents a node in a linked list of tweets.
 */
public class TweetNode {
  private Tweet tweet;
  private TweetNode nextTweet;

  /**
   * Constructs a new tweet node with the given tweet and next tweet node.
   * @param tweet the tweet to be stored in the node.
   * @param next the next tweet node in the linked list.
   */
  public TweetNode(Tweet tweet, TweetNode next) {
    this.tweet = tweet;
    this.nextTweet = next;
  }

  /**
   * Constructs a new tweet node with the given tweet and no next tweet node.
   * @param tweet the tweet to be stored in the node.
   */
  public TweetNode(Tweet tweet) {
    this(tweet, null);
  }

  /**
   * Returns the tweet stored in the node.
   * @return the tweet stored in the node.
   */
  public Tweet getTweet() {
    return tweet;
  }

  /**
   * Returns the next tweet node in the linked list.
   * @return  the next tweet node in the linked list.
   */
  public TweetNode getNext() {
    return nextTweet;
  }

  /**
   * Sets the next tweet node in the linked list to the given tweet node.
   * @param next the next tweet node to be set.
   */
  public void setNext(TweetNode next) {
    this.nextTweet = next;
  }
}
