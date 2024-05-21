package hello.world.services;

import hello.world.models.Department;
import hello.world.models.Subject;
import hello.world.repositoty.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    public void addDepartment(Department department) {
        department.setId(++ID);
        departments.add(department);

        departmentRepository.save(department);
    }


    private final SubjectService subjectService;
    private final List<Department> departments = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();

    private long ID = 0;
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }


    public List<Department> listDepartments() {
        return departmentRepository.findAll();
    }


    public DepartmentService(@Autowired SubjectService subjectService) {
        this.subjectService = subjectService;

        departments.add(new Department(++ID, "ІНЖЕНЕРІЇ ПРОГРАМНОГО ЗАБЕЗПЕЧЕННЯ", "ІПЗ", "249-25-96", "315 (кім. 3, 4)", new ArrayList<>(), new ArrayList<>()));
        departments.add(new Department(++ID, "КОМП'ЮТЕРНОЇ ІНЖЕНЕРІЇ", "КІ", "(097)-527-49-26", "218", new ArrayList<>(), new ArrayList<>()));

    }
  /*  public void addDepartment(Department department) {
        department.setId(++ID);
        departments.add(department);
    }
*/


    public void addSubjectsToDepartment(Long departmentId, List<Long> subjectIds) {
        Department department = getDepartmentById(departmentId);
        if (department != null) {
            List<Subject> departmentSubjects = department.getSubjects();

            subjects = subjectService.listSubjects();

            subjectIds.forEach(id -> subjects.stream()
                    .filter(subject -> subject.getId().equals(id))
                    .filter(subject -> !department.getSubjects().contains(subject))
                    .findFirst()
                    .ifPresent(departmentSubjects::add));
        }
    }




    public void deleteDepartment(Long id) {

        departments.removeIf(department -> department.getId().equals(id));

        departmentRepository.deleteById(id);
    }




    public void updateDepartment(Department department) {
        Department existingDepartment = getDepartmentById(department.getId());
        if (existingDepartment != null) {
            existingDepartment.setName(department.getName());
            existingDepartment.setTitle(department.getTitle());
            existingDepartment.setPhone(department.getPhone());
            existingDepartment.setClassroom(department.getClassroom());
        }
    }
}
