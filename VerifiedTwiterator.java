//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    VerifiedTwiterator.java
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

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * An iterator that iterates over a collection of tweets, returning only the tweets of verified users.
 */
public class VerifiedTwiterator implements Iterator<Tweet> {
  private TweetNode next;

  /**
   * An iterator that iterates over a collection of tweets, returning only the tweets of verified users.
   * @param startNode the start of the list
   */
  public VerifiedTwiterator(TweetNode startNode) {
    this.next = startNode;
    // Advance to the first verified tweet
    while (next != null && !next.getTweet().isUserVerified()) {
      next = next.getNext();
    }
  }

  /**
   * checks if hasNext is true
   * @return if hasNext is true or not
   */
  @Override
  public boolean hasNext() {
    return next != null;
  }

  /**
   * This method advances to the next verified tweet in the iteration.
   * @return the next verified tweet in the iteration if it exist
   * @throws  NoSuchElementException If there are no more verified tweets in the iteration
   */
  @Override
  public Tweet next() {
    if (next == null) {
      throw new NoSuchElementException("No more verified tweets");
    }
    Tweet toReturn = next.getTweet();
    // Advance to the next verified tweet
    do {
      next = next.getNext();
    } while (next != null && !next.getTweet().isUserVerified());
    return toReturn;
  }

  // Remove method is inherited from the Iterator<Tweet> interface, no need to implement it
}
