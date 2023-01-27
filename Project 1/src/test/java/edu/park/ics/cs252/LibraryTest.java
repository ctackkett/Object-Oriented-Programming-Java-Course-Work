package edu.park.ics.cs252;

import java.util.Scanner;
import org.junit.Test;
import java.io.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;

public class LibraryTest {
  @Test
  public void testGetBook() {
    Library.initializeDatabase();
    assertNotNull(Library.getAsset("Dune"));
    assertNotNull(Library.getAsset("Hitchhiker's Guide to the Galaxy"));
    assertNotNull(Library.getAsset("Snow Crash"));
    assertEquals("Neil Stephenson", Library.getAsset("Snow Crash").getAuthor());
    assertNotNull(Library.getAsset("Neuromancer"));
    assertNotNull(Library.getAsset("Star Wars"));
    assertNull(Library.getAsset("The Handmaid's Tale"));
    assertNull(Library.getAsset("Ender's Game"));
    assertNull(Library.getAsset("It (fake video)"));
    assertNull(Library.getAsset("The Stand"));
    assertTrue(Library.getAsset("Neuromancer") instanceof Book);
    assertTrue(Library.getAsset("Star Wars") instanceof Video);
    assertEquals(Rating.PG, ((Video)Library.getAsset("Star Wars")).getRating());
  }


   @Rule
   public TemporaryFolder tempFolder = new TemporaryFolder();

   @Test(expected = IllegalArgumentException.class)
   public void testBadBook1() throws IOException {
	   var asset = Library.deserializeAsset("Book,\"a\",\"y\"");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testBadBook2() throws IOException {
	   var asset = Library.deserializeAsset("book,\"a\",\"y\",null,null");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testBadBook3() throws IOException {
	   var asset = Library.deserializeAsset("Book,null,null,null,null");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testBadBook4() throws IOException {
	   var asset = Library.deserializeAsset("Book,\"a\",y,null,null");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testBadBook5() throws IOException {
	   var asset = Library.deserializeAsset("Book,\"a\",\"y\",dsg,\"2020-01-01\"");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testBadBook6() throws IOException {
	   var asset = Library.deserializeAsset("Book,\"a\",\"y\",\"dsg\",\"20-201-20\"");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testBadVideo() throws IOException {
	   var asset = Library.deserializeAsset("Video,\"a\",\"y\",null,null");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testBadVideo2() throws IOException {
	   var asset = Library.deserializeAsset("Video,\"a\",\"y\",null,null,PG13");
   }

   @Test
   public void testDeserializeBook() throws IOException {
	   var asset = Library.deserializeAsset("Book,\"a\",\"y\",null,null");
	   assertNotNull(asset);
	   assertTrue(asset instanceof Book);
	   Book b = (Book)asset;
	   assertEquals("a", b.getName());
	   assertEquals("y", b.getAuthor());
	   assertFalse(b.isRented());
	   assertNull(b.getRenter());
	   assertNull(b.getDueDate());
   }

   @Test
   public void testDeserializeBookRented() throws IOException {
	   var asset = Library.deserializeAsset("Book,\"a\",\"y\",\"h\",\"2020-04-05\"");
	   assertNotNull(asset);
	   assertTrue(asset instanceof Book);
	   Book b = (Book)asset;
	   assertEquals("a", b.getName());
	   assertEquals("y", b.getAuthor());
	   assertTrue(b.isRented());
	   assertEquals("h", b.getRenter());
	   assertEquals("2020-04-05", b.getDueDate().toString());
   }

   @Test
   public void testDeserializeVideo() throws IOException {
	   var asset = Library.deserializeAsset("Video,\"ya\",\"yb\",null,null,\"PG13\"");
	   assertTrue(asset instanceof Video);
	   Video v = (Video)asset;
	   assertEquals("ya", v.getName());
	   assertEquals("yb", v.getAuthor());
	   assertEquals(Rating.PG13, v.getRating());
	   assertFalse(v.isRented());
	   assertNull(v.getRenter());
	   assertNull(v.getDueDate());
   }

   @Test
   public void testDeserializeVideoRented() throws IOException {
	   var asset = Library.deserializeAsset("Video,\"ya\",\"yb\",\"u\",\"2020-02-03\",\"PG13\"");
	   assertTrue(asset instanceof Video);
	   Video v = (Video)asset;
	   assertEquals("ya", v.getName());
	   assertEquals("yb", v.getAuthor());
	   assertEquals(Rating.PG13, v.getRating());
	   assertTrue(v.isRented());
	   assertEquals("u", v.getRenter());
	   assertEquals("2020-02-03", v.getDueDate().toString());
   }

   @Test
   public void testLoadAndSaveVideoNoRental() throws IOException {
	   File f1 = tempFolder.newFile("f1.csv");
	   File f2 = tempFolder.newFile("f2.csv");
	   PrintWriter finit = new PrintWriter(new FileWriter(f1));
	   finit.println("Video,\"a\",\"b\",null,null,\"PG13\"");
	   finit.println("Book,\"d\",\"d\",null,null");
	   finit.close();
	   Library.loadAssets(f1);
	   var a1 = Library.getAsset("a");
	   assertFalse(a1.isRented());
	   assertTrue(a1 instanceof Video);
	   Video v1 = (Video)a1;
	   assertEquals("b", v1.getAuthor());
	   assertEquals(Rating.PG13, v1.getRating());
	   var a2 = Library.getAsset("d");
	   assertFalse(a1.isRented());
	   assertTrue(a2 instanceof Book);
	   Library.saveAssets(f2);
	   Scanner res = new Scanner(f2);
	   assertTrue(res.hasNextLine());
	   assertEquals("Video,\"a\",\"b\",null,null,\"PG13\"", res.nextLine());
	   assertTrue(res.hasNextLine());
	   assertEquals("Book,\"d\",\"d\",null,null", res.nextLine());
   }

   @Test(expected = IllegalArgumentException.class)
   public void testBadFile1() throws IOException {
	   File f1 = tempFolder.newFile("f1.csv");
	   PrintWriter finit = new PrintWriter(new FileWriter(f1));
	   finit.println("Video,\"a\",\"b\",\"g\",\"2020-08-01\",\"PG13\"");
	   finit.println("Book,\"d\",\"d\",\"2020-07-03\"");
	   finit.close();
	   Library.loadAssets(f1);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testBadFile2() throws IOException {
	   File f1 = tempFolder.newFile("f1.csv");
	   PrintWriter finit = new PrintWriter(new FileWriter(f1));
	   finit.println("Video,\"a\",\"b\",\"g\",\"2020-08-01\",\"PG13\"");
	   finit.println("ook,\"d\",\"d\",\"h\",\"2020-07-03\"");
	   finit.close();
	   Library.loadAssets(f1);
   }

   @Test
   public void testLoadAndSaveVideoWithRental() throws IOException {
	   File f1 = tempFolder.newFile("f1.csv");
	   File f2 = tempFolder.newFile("f2.csv");
	   PrintWriter finit = new PrintWriter(new FileWriter(f1));
	   finit.println("Video,\"a\",\"b\",\"g\",\"2020-08-01\",\"PG13\"");
	   finit.println("Book,\"d\",\"d\",\"h\",\"2020-07-03\"");
	   finit.close();
	   Library.loadAssets(f1);
	   var a1 = Library.getAsset("a");
	   assertTrue(a1.isRented());
	   assertEquals("g", a1.getRenter());
	   assertEquals("2020-08-01", a1.getDueDate().toString());
	   assertTrue(a1 instanceof Video);
	   Video v1 = (Video)a1;
	   assertEquals("b", v1.getAuthor());
	   assertEquals(Rating.PG13, v1.getRating());
	   var a2 = Library.getAsset("d");
	   assertTrue(a1.isRented());
	   assertTrue(a2 instanceof Book);
	   Library.saveAssets(f2);
	   Scanner res = new Scanner(f2);
	   assertTrue(res.hasNextLine());
	   assertEquals("Video,\"a\",\"b\",\"g\",\"2020-08-01\",\"PG13\"", res.nextLine());
	   assertTrue(res.hasNextLine());
	   assertEquals("Book,\"d\",\"d\",\"h\",\"2020-07-03\"", res.nextLine());
   }
}

