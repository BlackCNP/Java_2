package com.example.kursovav4;

import com.example.kursovav4.controllers.PostController;
import com.example.kursovav4.models.Account;
import com.example.kursovav4.models.Post;
import com.example.kursovav4.services.AccountService;
import com.example.kursovav4.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerIntegrationTest {

    @Mock
    private PostService postService;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void testGetPost() throws Exception {
        Long postId = 1L;
        Post post = new Post();
        when(postService.getById(postId)).thenReturn(Optional.of(post));

        mockMvc.perform(get("/posts/{id}", postId))
                .andExpect(status().isOk())
                .andExpect(view().name("post"));

        verify(postService, times(1)).getById(postId);
    }

    @Test
    public void testUpdatePost() throws Exception {
        Long postId = 1L;
        Account account = new Account();
        account.setEmail("test@example.com");
        Post post = new Post();
        post.setId(postId);
        post.setAccount(account);
        when(postService.getById(postId)).thenReturn(Optional.of(post));

        mockMvc.perform(post("/posts/{id}", postId)
                        .param("title", "Updated Title")
                        .param("body", "Updated Body"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));

        verify(postService, times(1)).getById(postId);
        verify(postService, times(1)).save(post);
    }

    @Test
    public void testDeletePost() throws Exception {
        Long postId = 1L;
        Post post = new Post();
        when(postService.getById(postId)).thenReturn(Optional.of(post));

        // Встановлення аутентифікації з правильними типами
        TestingAuthenticationToken authentication = new TestingAuthenticationToken("test@example.com", "", "ROLE_ADMIN");
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(get("/posts/{id}/delete", postId))
                .andExpect(status().is3xxRedirection()) // Перевіряємо, що статус відповіді є редиректом
                .andExpect(redirectedUrl("/")); // Перевіряємо, що редирект відбувається на головну сторінку

        verify(postService, times(1)).getById(postId);
        verify(postService, times(1)).delete(post);
    }

    @Test
    public void testCreateNewPost() throws Exception {
        Account account = new Account();
        account.setEmail("test@example.com");
        when(accountService.findByEmail("test@example.com")).thenReturn(Optional.of(account));

        mockMvc.perform(get("/posts/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("post_create")); // Перевіряємо, що повертається правильне відображення

        verify(accountService, times(1)).findByEmail("test@example.com");
    }
}