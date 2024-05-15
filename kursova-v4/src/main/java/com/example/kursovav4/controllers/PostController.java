package com.example.kursovav4.controllers;

import com.example.kursovav4.models.Account;
import com.example.kursovav4.models.Post;
import com.example.kursovav4.services.AccountService;
import com.example.kursovav4.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
   private AccountService accountService;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {

        // пошук     айді
        Optional<Post> optionalPost = this.postService.getById(id);
        // якщо знайшов то сюди
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        } else {
            return "pomilka";
        }
    }

    @GetMapping("/posts/create")
    public String createNewPost(Model model) {


        Optional<Account> optionalAccount = accountService.findByEmail("user@hello.world");
        if (optionalAccount.isPresent()) {
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "post_create";
        } else {
            return "pomilka";
        }
    }
    @PostMapping("/posts/create")
    public String createNewPost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

}