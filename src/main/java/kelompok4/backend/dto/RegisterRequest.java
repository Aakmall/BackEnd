package kelompok4.backend.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private Integer age;
    private String email;
    private String password;
}
