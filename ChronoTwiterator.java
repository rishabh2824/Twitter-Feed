//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    ChronoTwiterator.java
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
 * It is an Iterator implementation that iterates over a sequence of tweets in chronological
 * order. The iterator starts from a given TweetNode and moves forward in
 * chronological order to iterate through each tweet until there are no more tweets left to iterate.
 * The implementation guarantees that the tweets will be returned in chronological order,
 * based on the order of the TweetNode objects.
 */
public class ChronoTwiterator implements Iterator<Tweet> {
  private TweetNode next;

  public ChronoTwiterator(TweetNode startNode) {
    next = startNode;
  }

  public boolean hasNext() {
    return next != null;
  }

  /**
   * Returns the next Tweet in the iteration. This method advances the iterator by one position.
   * @return the next Tweet in the iteration
   * @throws NoSuchElementException if there are no more tweets available to iterate over.
   */
  public Tweet next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("No more tweets available.");
    }

    Tweet currentTweet = next.getTweet();
    next = next.getNext();
    return currentTweet;
  }
}
