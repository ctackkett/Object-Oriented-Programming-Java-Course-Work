package edu.park.ics.cs252;

/** Enumeration of the MPAA Film rating system */
public enum Rating {

    G     (0),		// General Audiences
    PG    (0),		// Parental Guidance Suggested
    PG13 (13),		// Parents Strongly Cautioned
    R     (17),		// Restricted
    NC17 (17);		// Adults Only

    private final int minimumAge;

    /** constructs an MPAA rating with the specified minimum age for viewing */
    Rating(int minimumAge) {
        this.minimumAge= minimumAge;
    }

    /** minors must have this age or higher to view this film by themselves */
    public int getMinimumAge() { return minimumAge; }

}
