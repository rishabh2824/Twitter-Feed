//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    RatioTwiterator.java
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * It is an Iterator implementation that iterates over a sequence of tweets in order of their
 * like ratios. The iterator starts from a given TweetNode and moves forward to iterate through
 * each tweet until there are no more tweets left to iterate. The implementation guarantees that
 * the tweets will be returned in the order of their like ratios, as long as the ratios are
 * greater than or equal to a specified threshold.
 */
public class RatioTwiterator implements Iterator<Tweet> {
  private TweetNode next;
  private final double THRESHOLD;

  /**
   * Constructs a new RatioTwiterator starting at the specified TweetNode with the specified
   * threshold for like ratios.
   *
   * @param startNode the TweetNode from which to start iterating.
   * @param threshold the minimum threshold for like ratios, below which tweets will be skipped.
   */
  public RatioTwiterator(TweetNode startNode, double threshold) {
    THRESHOLD = threshold;
    while (startNode != null && startNode.getTweet().getLikesRatio() < THRESHOLD) {
      startNode = startNode.getNext();
    }
    next = startNode;
  }

  /**
   * checks is hasNext is true
   * @return if hasNext is true or not
   */
  public boolean hasNext() {
    return next != null;
  }

  /**
   * Returns the next tweet in the iteration, as long as its like ratio is greater than or equal to
   * the specified threshold.
   *
   * @return the next tweet in the iteration.
   * @throws NoSuchElementException if there are no more tweets available in the iteration.
   */
  public Tweet next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("No more tweets available.");
    }

    Tweet currentTweet = next.getTweet();
    next = next.getNext();

    while (next != null && next.getTweet().getLikesRatio() < THRESHOLD) {
      next = next.getNext();
    }

    return currentTweet;
  }
}
