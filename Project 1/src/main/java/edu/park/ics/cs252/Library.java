package edu.park.ics.cs252;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/** Sample application/driver code for object-oriented programming labs in CS252. 
 *  Students will be responsible for developing datatypes used within this code and 
    extending it with I/O functionality down the line. 
    
    @author jkendallmorwick@park.edu
*/
public class Library {

    // internal list of assets in the library
    private static final ArrayList<Asset> assets = new ArrayList<Asset>();

    /** locates an asset by its name and returns the corresponding object */
    public static Asset getAsset(String name) {
	    for(Asset asset : assets)
		    if(asset.getName().equalsIgnoreCase(name))
			    return asset;
	    return null;
    }


    public static void loadAssets(File saveFile) throws IOException {
        assets.clear();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(saveFile));
            Asset asset = null;

            String contentLine = br.readLine();
            while (contentLine != null) {
                asset = null;
                try {
                    asset = deserializeAsset(contentLine);
                } catch(IllegalArgumentException iae) {
                    throw new IllegalArgumentException(iae);
                }
                assets.add(asset);
                contentLine = br.readLine();
            }

            br.close();
        } catch(IOException ioe) {
            System.out.println("Error processing file: " + ioe.getMessage());
        }
    }

    public static void saveAssets(File saveFile) throws IOException {
        try {
            if (!saveFile.exists()){
                saveFile.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(saveFile), "utf-8"))) {

                for(Asset a: assets){
                    writer.write(a.serialize());
                    writer.newLine();
                }
            }

        } catch (IOException ioe) {
            throw new IOException(ioe);
        }
    }

    public static Asset deserializeAsset(String csv) {
        var tokens = csv.split(",");
        for(int i=1; i<tokens.length; i++) {
            tokens[i] = tokens[i].trim();
            if(tokens[i].startsWith("\"")) {
                if(tokens[i].length() < 2 && !tokens[i].endsWith("\""))
                    throw new IllegalArgumentException("malformed value");
                tokens[i] = tokens[i].substring(1,tokens[i].length()-1);
            } else {
                if(!tokens[i].equals("null"))
                    throw new IllegalArgumentException("malformed value ("+i+"): " + tokens[i] );
                else tokens[i] = null;
            }
        }

        if(tokens[1] == null || tokens[2] == null)
            throw new IllegalArgumentException("incorrectly formatted value");

        try {
            switch(tokens[0]) {
                case "Book":
                    if(tokens[3] == null && tokens[4] == null)
                        return new Book(tokens[1], tokens[2]);
                    else
                        return new Book(tokens[1], tokens[2], tokens[3], LocalDate.parse(tokens[4]));
                case "Video":
                    if(tokens[3] == null && tokens[4] == null)
                        return new Video(tokens[1], tokens[2], Rating.valueOf(tokens[5]));
                    else
                        return new Video(tokens[1], tokens[2], Rating.valueOf(tokens[5]), tokens[3], LocalDate.parse(tokens[4]));
                default:
                    throw new IllegalArgumentException("no such asset type: " + tokens[0]);
            }
        } catch(Exception e) {
            throw new IllegalArgumentException("incorrectly formatted value");
        }
    }

    /** populates the library with example assets */
    public static void initializeDatabase() {
	    assets.clear();
	    assets.add(new Book("The Left Hand of Darkness", "Ursula Le Guin"));
	    assets.add(new Book("Dune", "Frank Herbert"));
	    assets.add(new Book("Hitchhiker's Guide to the Galaxy", "Douglas Adams"));
	    assets.add(new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick"));
	    assets.add(new Book("Frankenstein", "Mary Shelly"));
	    assets.add(new Book("The Hunger Games", "Suzanne Collins"));
	    assets.add(new Book("Never Let Me Go", "Kazuo Ishiguro"));
	    assets.add(new Book("Neuromancer", "William Gibson"));
	    assets.add(new Book("I Robot", "Isaac Asimov"));
	    assets.add(new Book("Snow Crash", "Neil Stephenson"));
        assets.add(new Video("Star Wars", "George Lucas", Rating.PG));
        assets.add(new Video("It", "Muschietti", Rating.R));
    }

    public static void main(String[] args) {
	    initializeDatabase();

        var in = new Scanner(System.in);
        while(true) { // run menu indefinitely
            System.out.println("\nWelcome to the CS 252 Library App!");
            System.out.println("");
            System.out.println("Menu: ");
            System.out.println(" 1) List Inventory ");
            System.out.println(" 2) List Assets on Loan ");
            System.out.println(" 3) Rent a Asset ");
            System.out.println(" 4) Return a Asset ");
            System.out.println(" 5) Load assets from a file");
            System.out.println(" 6) Save assets to a file");
            System.out.println(" 7) Quit ");
            System.out.println("");
            System.out.print("Please make a selection: ");

            var choice = in.nextLine();
            System.out.println("");
            switch (choice) {
                case "1": // list inventory
                    for(int i=0; i<assets.size(); i++)
                        System.out.println((i+1)+". " +
                                assets.get(i).getName() + " by " +
                                assets.get(i).getAuthor());
                    break;
                case "2": // list assets on loan
                    var anyRenters = false;
                    for(Asset asset: assets) {
                        if(asset.isRented()) {
                            anyRenters = true;
                            System.out.println(asset.getName());
                            System.out.println("rented by: " + asset.getRenter());
                            System.out.println("due on: " + asset.getDueDate());
                            System.out.println("");
                        }
		            }
                    if(!anyRenters) System.out.println("No assets are on loan. ");
                    break;
                case "3": // rent a asset
                    System.out.print("What asset will be rented: ");
                    var assetName = in.nextLine();
                    System.out.println("");
                    var asset = getAsset(assetName);
                    if(asset == null) { // make sure we stock the asset
                        System.out.println("Unknown asset");
                        break;
                    }
                    if(asset.isRented()) { // make sure the asset isn't rented
                        System.out.println("This asset is currently on loan");
                        break;
                    }
                    System.out.print("Who is renting the asset: ");
                    var renter = in.nextLine();
                    System.out.print("What is the renter's age: ");
                    var age = Integer.parseInt(in.nextLine());
                    if(asset.canRent(age)) {
                        asset.rent(renter);
                    } else {
                        System.out.println("Sorry, you are too young to rent this item. ");
                    }
                    break;
                case "4": // return a asset
                    System.out.print("What asset will be returned: ");
                    var returnedAssetName = in.nextLine();
                    System.out.println("");
		            asset = getAsset(returnedAssetName);
                    if(asset == null) { // make sure we stock the asset
                        System.out.println("Unknown asset");
                        break;
                    }
                    if(!asset.isRented()) { // make sure the asset isn't rented
                        System.out.println("This asset is not currently on loan");
                        break;
                    }
		            asset.returnToLibrary();
                    break;
                case "5": // load assets from file
                    System.out.println("Enter a filename to load assets from: ");
                    String filename = in.nextLine();
                    try {
                        loadAssets(new File(filename));
                    } catch(IOException e) {
                        System.out.println("Could not load file '"+filename+"' -- please try again");
                    }
                    break;
                case "6": // save assets to file
                    System.out.println("Enter a filename to save assets to: ");
                    filename = in.nextLine();
                    try {
                        saveAssets(new File(filename));
                    } catch(IOException e) {
                        System.out.println("Could not load file '"+filename+"' -- please try again");
                    }
                    break;
                case "7":// exit selected
                    System.out.println("Thank you for using the library!");
                    System.exit(0);  // user exit program
                    break;
                default:
                    System.out.println(choice + " is not a valid selection, try again");
                    break;
            }
        }
    }
}

