package hello.world.controllers;

import hello.world.models.DepartmentNews;
import hello.world.services.DepartmentNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DepartmentNewsController {
   /* private final DepartmentNewsService newsService;

    @GetMapping("/departments/{departmentId}/news")
    public String listNews(@PathVariable Long departmentId, Model model) {
        model.addAttribute("news", newsService.getNewsByDepartmentId(departmentId));
        return "redirect:/departments/" + departmentId ;
    }

    @PostMapping("/departments/{departmentId}/news")
    public String createNews(@PathVariable Long departmentId, DepartmentNews news) {
        news.setDepartmentId(departmentId);
        newsService.addNews(news);
        return "redirect:/departments/" + departmentId ;
    }

    @PostMapping("/departments/{departmentId}/news/delete/{id}")
    public String deleteNews(@PathVariable Long departmentId, @PathVariable Long id) {
        newsService.deleteNews(id);
        return "redirect:/departments/" + departmentId ;
    }
*/
}
