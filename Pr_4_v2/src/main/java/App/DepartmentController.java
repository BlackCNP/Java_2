package App;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
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
            return "department-info";
        }
        return "redirect:/departments";
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
