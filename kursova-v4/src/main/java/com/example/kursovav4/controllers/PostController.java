package com.example.kursovav4.controllers;

import com.example.kursovav4.models.Account;
import com.example.kursovav4.models.Post;
import com.example.kursovav4.services.AccountService;
import com.example.kursovav4.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/posts/{id}")
    @Operation(summary = "Отримати пост за ID", description = "Отримати пост з бази даних за його ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пост успішно отримано"),
            @ApiResponse(responseCode = "404", description = "Пост не знайдено")
    })
    public String getPost(@PathVariable Long id, Model model) {
        Optional<Post> optionalPost = this.postService.getById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        } else {
            return "pomilka";
        }
    }

    @PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Оновити пост за ID", description = "Оновити деталі поста з бази даних за його ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Повертає оновлений пост, якщо він існує"),
            @ApiResponse(responseCode = "404", description = "Пост не знайдено в базі даних")
    })
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post, BindingResult result, Model model, Principal principal) {
        String authUsername = principal != null ? principal.getName() : "anonymousUser";

        Optional<Post> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            if (existingPost.getAccount() != null && existingPost.getAccount().getEmail().equalsIgnoreCase(authUsername)) {
                existingPost.setTitle(post.getTitle());
                existingPost.setBody(post.getBody());
                postService.save(existingPost);
                return "redirect:/posts/" + existingPost.getId();
            } else {
                return "pomilka";
            }
        } else {
            return "pomilka";
        }
    }

    @GetMapping("/posts/create")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Створити новий пост", description = "Створити новий пост у базі даних")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Створення поста успішне"),
            @ApiResponse(responseCode = "404", description = "Користувача не знайдено")
    })
    public String createNewPost(Model model, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<Account> optionalAccount = accountService.findByEmail(authUsername);
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
    @Operation(summary = "Створити новий пост", description = "Створити новий пост у базі даних")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Створення поста успішне"),
            @ApiResponse(responseCode = "404", description = "Користувача не знайдено")
    })
    public String createNewPost(@ModelAttribute Post post, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        if (post.getAccount() == null) {
            Optional<Account> optionalAccount = accountService.findByEmail(authUsername);
            if (optionalAccount.isPresent()) {
                post.setAccount(optionalAccount.get());
            } else {
                return "pomilka";
            }
        }

        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Отримати пост для редагування", description = "Отримати пост з бази даних для редагування за його ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне отримання поста"),
            @ApiResponse(responseCode = "401", description = "Неавторизований"),
            @ApiResponse(responseCode = "403", description = "Заборонено"),
            @ApiResponse(responseCode = "404", description = "Пост не знайдено")
    })
    public String getPostForEdit(@PathVariable Long id, Model model, Principal principal) {
        String authUsername = principal != null ? principal.getName() : "anonymousUser";

        Optional<Post> optionalPost = postService.getById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (post.getAccount().getEmail().equalsIgnoreCase(authUsername)) {
                model.addAttribute("post", post);
                return "post_edit";
            } else {
                return "pomilka";
            }
        } else {
            return "pomilka";
        }
    }

    @GetMapping("/posts/{id}/delete")
    @Operation(summary = "Видалити пост за ID", description = "Видалити існуючий пост з бази даних за його ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пост успішно видалено"),
            @ApiResponse(responseCode = "401", description = "Неавторизований користувач"),
            @ApiResponse(responseCode = "403", description = "Користувач не має прав на видалення цього поста"),
            @ApiResponse(responseCode = "404", description = "Пост не знайдено в базі даних")
    })
    public String deletePost(@PathVariable Long id, Principal principal) {
        String authUsername = principal != null ? principal.getName() : "anonymousUser";

        Optional<Post> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            String postAuthor = post.getAccount().getEmail();
            if (authUsername.equals(postAuthor) || SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                postService.delete(post);
                return "redirect:/";
            } else {
                return "pomilka";
            }
        } else {
            return "pomilka";
        }
    }
}
