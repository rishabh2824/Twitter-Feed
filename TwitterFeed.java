//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TwitterFeed.java
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
 * This class represents a Twitter feed, which implements the ListADT interface and the Iterable
 * interface for iterating through the tweets. It contains methods for adding, deleting, and
 * retrieving tweets, as well as methods for checking the size of the feed and determining if it is
 * empty. It also has a mode field that determines the order of iteration, and a ratio field used
 * for the LIKE_RATIO mode.
 */
public class TwitterFeed implements ListADT<Tweet>, Iterable<Tweet> {
  private TweetNode head;
  private TweetNode tail;
  private int size;
  private TimelineMode mode;
  private static double ratio = 0.5;

  /**
   * Constructs an empty TwitterFeed with default settings.
   */
  public TwitterFeed() {
    head = null;
    tail = null;
    size = 0;
    mode = TimelineMode.CHRONOLOGICAL;
  }

  /**
   * Returns the number of tweets in the TwitterFeed.
   * @return the number of tweets in the TwitterFeed
   */
  public int size() {
    return size;
  }

  /**
   * Returns true if the TwitterFeed contains no tweets.
   * @return true if the TwitterFeed contains no tweets
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns true if the TwitterFeed contains the specified tweet.
   * @param findObject  the tweet to search for
   * @return true if the TwitterFeed contains the specified tweet
   */
  public boolean contains(Tweet findObject) {
    TweetNode current = head;
    while (current != null) {
      if (current.getTweet().equals(findObject)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  /**
   * Returns the index of the first occurrence of the specified tweet in the TwitterFeed, or -1 if the tweet is not found.
   * @param findObject the tweet to search for
   * @return the index of the first occurrence of the specified tweet in the TwitterFeed, or -1 if the tweet is not found
   */
  public int indexOf(Tweet findObject) {
    TweetNode current = head;
    int index = 0;
    while (current != null) {
      if (current.getTweet().equals(findObject)) {
        return index;
      }
      current = current.getNext();
      index++;
    }
    return -1;
  }

  /**
   * Returns the tweet at the specified position in the TwitterFeed.
   * @param index the index of the tweet to return
   * @return the tweet at the specified position in the TwitterFeed
   */
  public Tweet get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    TweetNode current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    return current.getTweet();
  }

  /**
   * Returns the head tweet in the TwitterFeed.
   * @return  the head tweet in the TwitterFeed
   */
  public Tweet getHead() {
    if (isEmpty()) {
      throw new NoSuchElementException("TwitterFeed is empty");
    }
    return head.getTweet();
  }

  /**
   * Returns the tail of the TwitterFeed.
   * @return the tail of the TwitterFeed.
   */
  public Tweet getTail() {
    if (isEmpty()) {
      throw new NoSuchElementException("TwitterFeed is empty");
    }
    return tail.getTweet();
  }

  /**
   * Adds a new tweet to the end of the TwitterFeed.
   * @param newObject the tweet to add.
   */
  public void addLast(Tweet newObject) {
    TweetNode newNode = new TweetNode(newObject);
    if (isEmpty()) {
      head = tail = newNode;
    } else {
      tail.setNext(newNode);
      tail = newNode;
    }
    size++;
  }

  /**
   * Adds a new tweet to the beginning of the TwitterFeed.
   * @param newObject the tweet to add.
   */
  public void addFirst(Tweet newObject) {
    TweetNode newNode = new TweetNode(newObject);
    if (isEmpty()) {
      head = tail = newNode;
    } else {
      newNode.setNext(head);
      head = newNode;
    }
    size++;
  }

  /**
   * Inserts a new tweet at the specified index in the TwitterFeed.
   * @param index the index at which to insert the tweet.
   * @param newObject the tweet to insert.
   */
  public void add(int index, Tweet newObject) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    if (index == 0) {
      addFirst(newObject);
    } else if (index == size) {
      addLast(newObject);
    } else {
      TweetNode newNode = new TweetNode(newObject);
      TweetNode current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }
      newNode.setNext(current.getNext());
      current.setNext(newNode);
      size++;
    }
  }

  /**
   * Removes and returns the tweet at the specified index in the TwitterFeed.
   * @param index the index of the tweet to remove.
   * @return the removed tweet.
   */
  public Tweet delete(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    Tweet removedTweet;
    if (index == 0) {
      removedTweet = head.getTweet();
      head = head.getNext();
      if (head == null) {
        tail = null;
      }
    } else {
      TweetNode current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }
      removedTweet = current.getNext().getTweet();
      current.setNext(current.getNext().getNext());
      if (current.getNext() == null) {
        tail = current;
      }
    }
    size--;
    return removedTweet;
  }

  /**
   * Sets the mode variable
   * @param m the variable to set.
   */
  public void setMode(TimelineMode m) {
    mode = m;
  }

  /**
   * Returns an iterator over the tweets in the TwitterFeed, according to the current mode.
   * @return an iterator over the tweets in the TwitterFeed, according to the current mode.
   */
  public Iterator<Tweet> iterator() {
    switch (mode) {
      case CHRONOLOGICAL:
        return new ChronoTwiterator(head);
      case VERIFIED_ONLY:
        return new VerifiedTwiterator(head);
      case LIKE_RATIO:
        return new RatioTwiterator(head, ratio);
      default:
        throw new IllegalStateException("Invalid TimelineMode");
    }
  }
}
