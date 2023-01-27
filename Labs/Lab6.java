import java.util.ArrayList;
import java.util.Arrays;

public class Tweet {
// setting the number of likes
    private int numberOfLikes = 0;
// setting the content, for example, what is in the tweet
    private String content;

    public void setContent(String newContent) {
        content = newContent;
    }
// getting the content
    public String getContent() {
        return content;
    }

    private void like() { //increase the likes of the tweet by one
        numberOfLikes++;
    }

    private int getLikes() { //shows how many likes the tweet has received
        return numberOfLikes;
    }

    private ArrayList<String> getAllHashTags() { //returns an ArrayList with all the hashtags in the tweet
        ArrayList<String> hashtagArray  = new ArrayList<>();
        System.out.println(content);
        String[] hashtags = content.split("(?=#)|(?= )"); //using regex allows for the hashtag to remain after the split
        for(String part: hashtags) { // for each part in hashtags, if it begins with a hashtag, add the part to the ArrayList
            if (part.startsWith("#"))
                hashtagArray.add(part);
        }
        return hashtagArray;
    }

    public static void main(String[] args) { //test area for running methods
        Tweet t1 = new Tweet();
        Tweet t2 = new Tweet();
        t1.setContent("This #hashtag1 #hashtag2 #hashtag3 is a tweet.");
        t2.setContent("This is a second tweet.");
        System.out.println(t1.getContent());
        System.out.println(t2.getContent());
        t1.like();t1.like();
        t2.like();t2.like();t2.like();
        System.out.println(t1.getLikes());
        System.out.println(t2.getLikes());
        System.out.println(t1.getAllHashTags());
        System.out.println(t2.getAllHashTags());
    }
}
