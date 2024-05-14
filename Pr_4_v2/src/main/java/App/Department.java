package App;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Department {
    private Long id;
    private String name;
    private String title;
    private String phone;
    private String classroom;
}

