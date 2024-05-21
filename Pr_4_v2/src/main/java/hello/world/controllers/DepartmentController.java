package hello.world.controllers;

import hello.world.models.Department;
import hello.world.models.DepartmentNews;
import hello.world.services.DepartmentNewsService;
import hello.world.services.DepartmentService;
import hello.world.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final SubjectService subjectService;
  //  private final DepartmentNewsService newsService;
    @GetMapping("/departments")
    public String departments(Model model) {
        model.addAttribute("departments", departmentService.listDepartments());
        return "departments";
    }
    @GetMapping("/departments/{id}")
    public String departmentInfo(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            model.addAttribute("department", department);
            model.addAttribute("allSubjects", subjectService.listSubjects());
            return "department-info";
        }
        return "redirect:/departments";
    }
    @PostMapping("/departments/{id}")
    public String addSubjectsToDepartment(@PathVariable Long id,
                                          @RequestParam List<Long> subjectIds) {
        departmentService.addSubjectsToDepartment(id, subjectIds);
        return "redirect:/departments/" + id;
    }






    @PostMapping("/departments/create")
    public String createDepartment(Department department) {
        departmentService.addDepartment(department);
        return "redirect:/departments";
    }
    @PostMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
    @GetMapping("/departments/edit/{id}")
    public String editDepartment(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            model.addAttribute("department", department);
            return "edit-department";
        }
        return "redirect:/departments";
    }


    @PostMapping("/departments/update")
    public String updateDepartment(@ModelAttribute Department department) {
        departmentService.updateDepartment(department);
        return "redirect:/departments";
    }

}
