package kelompok4.backend.controller;

import kelompok4.backend.dto.LoginRequest;
import kelompok4.backend.dto.RegisterRequest;
import kelompok4.backend.entity.User;
import kelompok4.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {
            return "User not found";
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return "Invalid password";
        }

        return "Login successful!";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        Optional<User> userOptional = userRepository.findByEmail(registerRequest.getEmail());

        if (userOptional.isPresent()) {
            return "Email already registered.";
        }

        User user = new User();
        user.setName(registerRequest.getName());
        user.setAge(registerRequest.getAge());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword()); // ❗ Nanti sebaiknya dienkripsi

        userRepository.save(user);

        return "Registration successful!";
    }
}
