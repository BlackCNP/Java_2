package App;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final List<Department> departments = new ArrayList<>();
    private long ID = 0;

    public List<Department> listDepartments() {
        return departments;
    }

    public void addDepartment(Department department) {
        department.setId(++ID);
        departments.add(department);
    }

    public void deleteDepartment(Long id) {
        departments.removeIf(department -> department.getId().equals(id));
    }

    public Department getDepartmentById(Long id) {
        for (Department department : departments) {
            if (department.getId().equals(id)) return department;
        }
        return null;
    }
    {
        departments.add(new Department(++ID, "ІНЖЕНЕРІЇ ПРОГРАМНОГО ЗАБЕЗПЕЧЕННЯ", "ІПЗ", "249-25-96", "315 (кім. 3, 4)"));
        departments.add(new Department(++ID, "КОМП'ЮТЕРНОЇ ІНЖЕНЕРІЇ", "КІ", "(097)-527-49-26", "218"));
    }

}
