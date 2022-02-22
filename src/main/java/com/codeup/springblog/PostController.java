package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String posts() {
        return "This is where the posts are";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postId(@PathVariable long id) {
        return "This will show the individual post.";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createForm() {
        return "This is where the form for creating a post is.";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "This will create a post.";
    }

}
