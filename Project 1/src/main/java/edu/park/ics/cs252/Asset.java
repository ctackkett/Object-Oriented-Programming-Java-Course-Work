package edu.park.ics.cs252;

import java.time.LocalDate;
import java.time.ZonedDateTime;


public abstract class Asset {
    // private variables
    private String bookName;
    private String authorName;
    private String renterName;
    private LocalDate dueDate;

    //rented book constructor
    public Asset(String bName, String aName, String rName, LocalDate dDate) {
        bookName = bName;
        authorName = aName;
        renterName = rName;
        dueDate = dDate;
    }

    //un-rented book constructor
    public Asset(String bName, String aName) {
        bookName = bName;
        authorName = aName;
        renterName = null;
        dueDate = null;
    }

    public String serialize() {

        String assetString = "";
        String assetType = this.getClass().getSimpleName();
        String assetName = this.getName();
        String assetAuthor = this.getAuthor();
        String assetRenter = this.getRenter();
        String assetDueDate = String.valueOf(this.getDueDate());

        assetString = assetString.concat(assetType + ",\"" + assetName + "\",\"" + assetAuthor + "\",");

        if (assetRenter == "null") {
            assetString = assetString.concat("null");
        }
        else {
            assetString = assetString.concat("\"" + assetRenter + "\"");
        }

        if (assetDueDate == "null") {
            assetString = assetString.concat("null");
        }
        else {
            assetString = assetString.concat("\"" + assetDueDate + "\"");
        }


        return assetString;

    }


    public boolean canRent(int age) {
        return true;
    }
    //method that returns name of the book
    public String getName() {
        return this.bookName;
    }

    //method that returns name of the book author
    public String getAuthor() {
        return this.authorName;
    }

    //method that checks if the book is rented
    public boolean isRented() {
        return (this.dueDate != null);
    }

    //method that gets the due date of the rented book
    public LocalDate getDueDate() {
        return this.dueDate;
    }

    //method that sets the renter's name and due date when a book is rented
    public void rent(String renterName) {
        this.renterName = renterName;
        this.dueDate = LocalDate.from(ZonedDateTime.now()).plusDays((7));
    }

    //method that removes rented book information for when the book is returned to the library
    public void returnToLibrary() {
        this.dueDate = null;
        this.renterName = null;
    }

    //method that returns the name of the renter and nothing if there is no renter
    public String getRenter() {
        if (this.renterName == null) {
            return null;
        }
        else {
            return this.renterName;
        }
    }
}
class Book extends Asset {

    Book(String bName, String aName, String rName, LocalDate dDate) {
        super(bName, aName, rName, dDate);
    }

    Book(String bName, String aName) {
        super(bName, aName);
    }

    @Override
    public boolean canRent(int age) {
        return true;
    }

    public String serialize() {

        String assetString = "";
        String assetType = this.getClass().getSimpleName();
        String assetName = this.getName();
        String assetAuthor = this.getAuthor();
        String assetRenter = this.getRenter();
        String assetDueDate = String.valueOf(this.getDueDate());

        assetString = assetString.concat(assetType + ",\"" + assetName + "\",\"" + assetAuthor + "\",");

        if (assetRenter == null) {
            assetString = assetString.concat("null,");
        }
        else {
            assetString = assetString.concat("\"" + assetRenter + "\",");
        }

        if (assetDueDate == "null") {
            assetString = assetString.concat("null");
        }
        else {
            assetString = assetString.concat("\"" + assetDueDate + "\"");
        }

        return assetString;

    }
}

class Video extends Asset {

    private Rating rating;

    Video(String bName, String aName, Rating vRating, String rName, LocalDate dDate) {
        super(bName, aName, rName, dDate);
        this.rating = vRating;
    }

    Video(String bName, String aName, Rating vRating) {
        super(bName, aName);
        this.rating = vRating;
    }

    public String serialize() {

        String assetString = "";
        String assetType = this.getClass().getSimpleName();
        String assetName = this.getName();
        String assetAuthor = this.getAuthor();
        String assetRenter = this.getRenter();
        String assetDueDate = String.valueOf(this.getDueDate());
        String assetRating = String.valueOf(this.getRating());

        assetString = assetString.concat(assetType + ",\"" + assetName + "\",\"" + assetAuthor + "\",");

        if (assetRenter == null) {
            assetString = assetString.concat("null,");
        }
        else {
            assetString = assetString.concat("\"" + assetRenter + "\",");
        }

        if (assetDueDate == "null") {
            assetString = assetString.concat("null,");
        }
        else {
            assetString = assetString.concat("\"" + assetDueDate + "\",");
        }

        if (assetRating == "null") {
            assetString = assetString.concat("null");
        }
        else {
            assetString = assetString.concat("\"" + assetRating + "\"");
        }

        return assetString;

    }


    void setRating(Rating rating) {
        this.rating = rating;
    }

    Rating getRating() {
        return this.rating;
    }

    @Override
    public boolean canRent(int age) {
        if (age < this.rating.getMinimumAge()) {
            return false;
        }
        else return true;
    }
}
