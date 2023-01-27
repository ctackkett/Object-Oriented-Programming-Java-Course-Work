package edu.park.ics.cs252;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class VideoTest {

  @Test
  public void testVideoBasicStructure() {
	  assertEquals("All fields should be private", 0, Video.class.getFields().length);
  }

  @Test
  public void testCreateVideo() {
	  var video1 = new Video("Some title", "Some author", Rating.PG);
	  assertNotNull(video1);
  }
 
  @Test
  public void testGetters() {
	  var video1 = new Video("dumb video", "dumb author", Rating.G);
	  var video2 = new Video("dumber video", "dumber author", Rating.R);
	  assertEquals("dumb video", video1.getName());
	  assertEquals("dumb author", video1.getAuthor());
	  assertEquals(Rating.G, video1.getRating());
	  assertEquals("dumber video", video2.getName());
	  assertEquals("dumber author", video2.getAuthor());
	  assertEquals(Rating.R, video2.getRating());
  }

  @Test
  public void testSerialize() {
          var v= new Video("Some Video", "Some Guy", Rating.PG);
          assertEquals("Video,\"Some Video\",\"Some Guy\",null,null,\"PG\"", v.serialize());
  }

  @Test
  public void testSerializeRented() {
          var v= new Video("Some Book", "Some Guy", Rating.NC17, "Renter Dude", LocalDate.now().plusDays(7));
          assertEquals("Video,\"Some Book\",\"Some Guy\",\"Renter Dude\",\""+LocalDate.now().plusDays(7)+"\",\"NC17\"", v.serialize());
  }


  @Test
  public void testCanRent() {
	  var video1 = new Video("dumb video", "dumb author", Rating.PG13);
	  var video2 = new Video("dumber video", "dumber author", Rating.R);
	  assertFalse(video1.canRent(3));
	  assertFalse(video1.canRent(12));
	  assertFalse(video2.canRent(12));
	  assertFalse(video2.canRent(16));
	  assertTrue(video1.canRent(13));
	  assertTrue(video1.canRent(33));
	  assertTrue(video1.canRent(103));
	  assertTrue(video2.canRent(17));
	  assertTrue(video2.canRent(33));
	  assertTrue(video2.canRent(103));
  }

}

