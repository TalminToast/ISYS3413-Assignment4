import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Post{

    //Post Attributes
    private int postID;
    private String postTitle;
    private String postBody;
    private String [] postTags;
    private String [] postTypes = {"Very Difficult", "Difficult", "Easy"};
    private String postType;
    private String [] postEmergency = {"Immediately Needed", "Highly Needed", "Ordinary"};
    private String postLevel;

    //Comment Attributes
    private int commentID;
    private String commentBody;
    private int numComments = 0;

    //CONSTRUCTOR ---------------------------------------------------------------
    public Post(int postID) {
        this.postID = postID;
    }

    //Function for validating if target is in inputArray
    public boolean validateArray(String[] inputArray, String target) { 
        for (int i = 0; i < inputArray.length; i++) { //Iterate through postTypes array
            if (target.equals(inputArray[i])) { //Validate if target = currentArraySelection
                return true; //Return true if match
            } 
        }

        return false; //If no matches, return false
    }

    public boolean addPost(String postTitle, String postBody ,String [] postTags, String postType, String postLevel) {

        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postLevel = postLevel;

        // Post Type Checker ---------------------------------------------------------------
        if (!validateArray(postTypes, this.postType)) {
            System.out.println("Invalid post type: " + this.postType);
            return false;
        }

        // Post Emergency Checker ---------------------------------------------------------------
        if (!validateArray(postEmergency, this.postLevel)) {
            System.out.println("Invalid post emergency level: " + this.postLevel);
            return false;
        }

        // Post Title Checks ---------------------------------------------------------------
        if (this.postTitle.length() < 10 || this.postTitle.length() > 250) { // Validate if postTitle has >= 10 and < 250 characters
            System.out.println("Post title length is invalid: " + this.postTitle.length());
            return false;
        }

        String initialChars = this.postTitle.substring(0, 5); // Creates string of first 5 characters of post title
        if (!initialChars.matches("[a-zA-Z]+")) { // Validate first 5 characters are alphabetical
            System.out.println("First 5 characters of post title are not alphabetical: " + initialChars);
            return false;
        }

        // Post Body Size Checker ---------------------------------------------------------------
        if (this.postType.equals("Very Difficult") || this.postType.equals("Difficult")) { // Determine Difficulty of post
            if (this.postBody.length() < 300) { // If Difficult or Very Difficult, check body >= 300 chars
                System.out.println("Post body is too short for " + this.postType + ": " + this.postBody.length());
                return false;
            }
        } else {
            if (this.postBody.length() < 250) { // If Easy, check body >= 250 chars
                System.out.println("Post body is too short for Easy: " + this.postBody.length());
                return false;
            }
        }

        // Post Tag Checker ---------------------------------------------------------------
        if (this.postTags.length < 2 || this.postTags.length > 5) { // Validate if post has > 2 and < 5 tags
            System.out.println("Invalid number of post tags: " + this.postTags.length);
            return false;
        }
        if (this.postType.equals("Easy")) { // Detect if post difficulty is easy
            if (this.postTags.length < 2 || this.postTags.length > 3) { // Validate if post has > 2 and < 3 tags
                System.out.println("Invalid number of tags for Easy post: " + this.postTags.length);
                return false;
            }
        }
        for (String postTag : this.postTags) { // Iterate through postTags
            if (postTag.length() < 2 || postTag.length() > 10) { // Validate each tag is > 2 and < 10 characters in length
                System.out.println("Invalid tag length: " + postTag);
                return false;
            }

            for (int j = 0; j < postTag.length(); j++) { // Iterate through each character in each tag
                if (Character.isUpperCase(postTag.charAt(j))) { // Validate each character is lowercase
                    System.out.println("Tag contains uppercase character: " + postTag);
                    return false;
                }
            }
        }

        // Post Emergency Validator ---------------------------------------------------------------
        if (this.postType.equals("Easy")) { // Identify if post is Easy
            if (!this.postLevel.equals("Ordinary")) { // Validate post emergency is Ordinary
                System.out.println("Invalid emergency level for Easy post: " + this.postLevel);
                return false;
            }
        }
        if (this.postType.equals("Very Difficult") || this.postType.equals("Difficult")) { // Identify if post is Very Difficult or Difficult
            if (this.postLevel.equals("Ordinary")) { // Validate post emergency is Ordinary
                System.out.println("Invalid emergency level for " + this.postType + ": " + this.postLevel);
                return false;
            }
        }

        // ALL CHECKS PASS ---------------------------------------------------------------
        try {
            FileWriter writer = new FileWriter("posts.txt", true); // Appending to file
            writer.write("postID: " + this.postID + "\n");
            writer.write("postTitle: " + this.postTitle + "\n");
            writer.write("postBody: " + this.postBody + "\n");
            writer.write("postType: " + this.postType + "\n");
            writer.write("postEmergency: " + this.postLevel + "\n");
            writer.write("\n");
            writer.close();
            System.out.println("Post written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false if there is an error writing to the file
        }

        return true;
    }

    public boolean addComment(int commentID, String commentBody) {

        this.commentID = commentID;
        this.commentBody = commentBody;

        //Comment Format Checker ---------------------------------------------------------------
        String [] commentWords = this.commentBody.split("\\s"); //Splits string into words
        int numWords = commentWords.length; //Find number of words
        if (numWords < 4 || numWords > 10) { //Validate comment has > 4 and < 10 words
            return false;
        } 

        if (!Character.isUpperCase(commentWords[0].charAt(0))) { //Validate first character is upper case.
            return false;
        }

        //Increase numComments to ensure correct number of comments is kept
        numComments++;

        //NUMCOMMENTS CHECKER ---------------------------------------------------------------
        if (this.postType.equals("Easy") || this.postLevel.equals("Ordinary")) {
            if (numComments > 3) {
                return false;
            }
        } else {
            if (numComments > 5) {
                return false;
            }
        }

        // ALL CHECKS PASS ---------------------------------------------------------------
        try {
            FileWriter writer = new FileWriter("comments.txt", true); // Appending to file
            writer.write("commentID: " + this.commentID + "\n");
            writer.write("commentBody: " + this.commentBody + "\n");
            writer.write("\n");
            writer.close();
            System.out.println("Comment written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false if there is an error writing to the file
        }

        return true;
    }
}
