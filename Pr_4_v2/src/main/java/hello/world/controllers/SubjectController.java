package hello.world.controllers;

import hello.world.models.Subject;
import hello.world.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/subjects")
    public String listSubjects(Model model) {
        model.addAttribute("subjects", subjectService.listSubjects());
        return "subjects";
    }

    @PostMapping("/subjects")
    public String createSubject(Subject subject) {
        subjectService.addSubject(subject);
        return "redirect:/subjects";
    }
}
