import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class programTest {

    //DECLARE VALID VALUES
    String postTitle = "Example Title";
    String postBody = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent auctor cursus pharetra. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Integer tortor sem, commodo sed pulvinar et, aliquet vitae leo. Phasellus non viverra quam. Sed vulputate lacus condimentum sem ornare, ut efficitur sem commodo. Donec tellus nunc, tempor ut velit eu, tristique ullamcorper velit. Maecenas nec convallis massa. Nullam imperdiet consequat dictum. Maecenas eget luctus enim. Duis suscipit mauris enim, finibus ornare enim vulputate sit amet.";
    String [] postTags = {"tag", "more tag", "other tag"};
    String postType = "Difficult";
    String postLevel = "Highly Needed";

    String commentBody = "This is the body of the comment.";
    
    //TEST TYPE VALIDATOR
    @Test
    public void testValidType() {
        Post post = new Post(1); //New Post Instance
        assertTrue(post.addPost(postTitle, postBody, postTags, postType, postLevel)); //Test if valid postType outputs true
    }
    @Test
    public void testInvalidType() {
        Post post = new Post(1); //New Post Instance
        String invType = "Simple"; //Invalid post type value
        assertFalse(post.addPost(postTitle, postBody, postTags, invType, postLevel)); //Test if invalid postType input outputs false
    }

    //TEST EMERGENCY VALIDATOR
    @Test
    public void testValidEmergency() {
        Post post = new Post(2); //New Post Instance
        assertTrue(post.addPost(postTitle, postBody, postTags, postType, postLevel)); //Test if valid postEmergency outputs true
    }
    @Test
    public void testInvalidEmergency() {
        Post post = new Post(2); //New Post Instance
        String invEmergency = "Urgent"; //Invalid post emergency value
        assertFalse(post.addPost(postTitle, postBody, postTags, postType, invEmergency)); //Test if invalid postEmergency input outputs false
    }

    //TEST TITLE VALIDATOR
    @Test
    public void testValidTitle() {
        Post post = new Post(3); //New Post Instance
        assertTrue(post.addPost(postTitle, postBody, postTags, postType, postLevel)); //Test if valid postTitle outputs true
    }
    @Test
    public void testInvalidTitle() {
        Post post = new Post(3); //New Post Instance
        String invTitle = "1 Title"; //Invalid post title value
        assertFalse(post.addPost(invTitle, postBody, postTags, postType, postLevel)); //Test if invalid postTitle input outputs false
    }

    //TEST BODY VALIDATOR
    @Test
    public void testValidBody() {
        Post post = new Post(4); //New Post Instance
        assertTrue(post.addPost(postTitle, postBody, postTags, postType, postLevel)); //Test if valid postBody outputs true
    }
    @Test
    public void testInvalidBody() {
        Post post = new Post(4); //New Post Instance
        String invBody = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent auctor cursus pharetra. Pellentesq"; //Invalid post body value
        assertFalse(post.addPost(postTitle, invBody, postTags, postType, postLevel)); //Test if invalid postBody input outputs false
    }

    //TEST TAGS VALIDATOR
    @Test
    public void testValidTags() {
        Post post = new Post(5); //New Post Instance
        assertTrue(post.addPost(postTitle, postBody, postTags, postType, postLevel)); //Test if valid postTags output true
    }
    @Test
    public void testInvalidTags() {
        Post post = new Post(5); //New Post Instance
        String [] invTags = {"Bad, Invalid, Incorrect Tag"}; //Invalid post tag values
        assertFalse(post.addPost(postTitle, postBody, invTags, postType, postLevel)); //Test if invalid postTags outputs false
    }

    //TEST COMMENT VALIDATOR
    @Test
    public void testValidComment() {
        Post post = new Post(6); //New Post Instance
        post.addPost(postTitle, postBody, postTags, postType, postLevel); //Creates post to comment on

        assertTrue(post.addComment(1, commentBody)); //Test if valid comment outputs true
    }
    @Test
    public void testInvalidComment() {
        Post post = new Post(6); //New Post Instance
        post.addPost(postTitle, postBody, postTags, postType, postLevel); //Create post to comment on
        String invCommentBody = "this comment is too long, and does not hava a starting capital letter"; //Invalid post body value
        
        assertFalse(post.addComment(1, invCommentBody)); //Test if invalid body outputs false
    }

    //TEST COMMENTNUM VALIDATOR
    @Test
    public void testLowNumComment() {
        Post post = new Post(7); //New Post Instance
        post.addPost(postTitle, postBody, postTags, postType, postLevel); //Creates post to comment on

        for (int i = 1; i < 5; i++) { //Create 5 comments
            post.addComment(i, commentBody); 
        }
        assertTrue(post.addComment(5, commentBody)); //Validate if creating a 5th comment returns true
    }
    @Test
    public void testHighNumComment() {
        Post post = new Post(7); //New Post Instance
        post.addPost(postTitle, postBody, postTags, postType, postLevel); //Creates post to comment on

        for (int i = 1; i < 6; i++) { //Create 5 comments
            post.addComment(i, commentBody); 
        }
        assertFalse(post.addComment(6, commentBody)); //Validate if creating a 6th comment returns false
    }
}
