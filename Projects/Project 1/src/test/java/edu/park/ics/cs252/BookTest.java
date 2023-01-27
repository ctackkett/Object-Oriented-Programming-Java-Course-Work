package edu.park.ics.cs252;

import org.junit.Test;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class BookTest {

  @Test
  public void testBookBasicStructure() {
	  assertEquals("All fields should be private", 0, Book.class.getFields().length);
  }

  @Test
  public void testCreateBook() {
	  var book1 = new Book("Some title", "Some author");
	  assertNotNull(book1);
  }

  @Test
  public void testSerialize() {
	  var book1 = new Book("Some Book", "Some Guy");
	  assertEquals("Book,\"Some Book\",\"Some Guy\",null,null", book1.serialize());
  }
 
  @Test
  public void testSerializeRented() {
	  var book1 = new Book("Some Book", "Some Guy", "Renter Dude", LocalDate.now().plusDays(7));
	  assertEquals("Book,\"Some Book\",\"Some Guy\",\"Renter Dude\",\""+LocalDate.now().plusDays(7)+"\"", book1.serialize());
  }
 
 
 
  @Test
  public void testgetNameBasic() {
	  var book1 = new Book("dumb book", "dumb author");
	  assertEquals("dumb book", book1.getName());
	  var book2 = new Book("dumber book", "dumber author");
	  assertEquals("dumber book", book2.getName());
  }

  @Test
  public void testgetNameInstanceBased() {
	  var book1 = new Book("dumb book", "dumb author");
	  var book2 = new Book("dumber book", "dumber author");
	  assertEquals("make sure your fields aren't static", "dumb book", book1.getName());
	  assertEquals("make sure your fields aren't static", "dumber book", book2.getName());
  }

  @Test
  public void testgetAuthorBasic() {
	  var book1 = new Book("dumb book", "dumb author");
	  assertEquals("dumb author", book1.getAuthor());
	  var book2 = new Book("dumber book", "dumber author");
	  assertEquals("dumber author", book2.getAuthor());
  }

  @Test
  public void testgetAuthorInstanceBased() {
	  var book1 = new Book("dumb book", "dumb author");
	  var book2 = new Book("dumber book", "dumber author");
	  assertEquals("make sure your fields aren't static", "dumb author", book1.getAuthor());
	  assertEquals("make sure your fields aren't static", "dumber author", book2.getAuthor());
  }

}

