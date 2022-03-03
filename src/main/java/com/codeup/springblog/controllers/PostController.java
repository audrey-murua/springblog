package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String showAllPosts(Model model) {
        //new array list that specifies Post class and contains Post objects
        List<Post> allPosts = new ArrayList<>();
        Post secondPost = new Post(2, "Second Post", "This is my second post!");
        Post thirdPost = new Post(3, "Third Post", "This is my third post! Woah!");

        //adding post objects to the allPosts array
        allPosts.add(secondPost);
        allPosts.add(thirdPost);

        model.addAttribute("allPosts", allPosts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        Post firstPost = new Post(1,"First Post","This is my first post!");
        model.addAttribute("individualPost", firstPost);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String showCreateForm() {
        return "This is where the form for creating a post is.";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String submitCreatePost() {
        return "This will create a post.";
    }

}
