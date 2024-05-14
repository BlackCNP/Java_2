package com.example.kursovav4.controllers;

import com.example.kursovav4.models.Post;
import com.example.kursovav4.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

   // @Autowired
   // private AccountService accountService;

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
}