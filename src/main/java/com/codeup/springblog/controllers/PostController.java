package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private PostRepository postsDao;
    private UserRepository usersDao;
    private EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository usersDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;

    }

    @GetMapping("/posts")
    public String showAllPosts(Model model) {
        //new array list that specifies Post class and contains Post objects
//        List<Post> allPosts = new ArrayList<>();
//        Post secondPost = new Post(2, "Second Post", "This is my second post!");
//        Post thirdPost = new Post(3, "Third Post", "This is my third post! Woah!");

        //adding post objects to the allPosts array
//        allPosts.add(secondPost);
//        allPosts.add(thirdPost);

//        model.addAttribute("allPosts", allPosts);

        model.addAttribute("allPosts", postsDao.findAll());

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
//        Post firstPost = new Post(1,"First Post","This is my first post!");
        model.addAttribute("individualPost", postsDao.getById(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("newPost", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String submitCreatePost(@ModelAttribute Post newPost)
//    public String submitCreatePost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body)
    {
//        Post newPost = new Post(title, body);
        newPost.setUser(usersDao.getById(1L));
        emailService.prepareAndSend(newPost, "Testing post creation", "This is a test email.");
        postsDao.save(newPost);

        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        Post postToEdit = postsDao.getById(id);
        model.addAttribute("postToEdit", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String submitEdit(@ModelAttribute Post postToEdit, @PathVariable long id)
//    public String submitEdit(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @PathVariable long id)
    {
//        Post postToEdit = postsDao.getById(id);
//        postToEdit.setTitle(title);
//        postToEdit.setBody(body);
        postsDao.save(postToEdit);
        return "redirect:/posts";
    }


    @GetMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

}
