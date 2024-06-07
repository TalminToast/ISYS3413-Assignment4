
public class Main {
    public static void main(String[] args) {
        String [] tags = {"tag", "more tag", "other tag"};
        String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent auctor cursus pharetra. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Integer tortor sem, commodo sed pulvinar et, aliquet vitae leo. Phasellus non viverra quam. Sed vulputate lacus condimentum sem ornare, ut efficitur sem commodo. Donec tellus nunc, tempor ut velit eu, tristique ullamcorper velit. Maecenas nec convallis massa. Nullam imperdiet consequat dictum. Maecenas eget luctus enim. Duis suscipit mauris enim, finibus ornare enim vulputate sit amet.";
        Post post = new Post(1);
        post.addPost("Example Title", body, tags, "Difficult", "Highly Needed");

        if (post.addComment(1, "This is the body of the comment.")) {
            System.out.println("Post added successfully.");
        } else {
            System.out.println("Failed to add post.");
        }
    }
}
