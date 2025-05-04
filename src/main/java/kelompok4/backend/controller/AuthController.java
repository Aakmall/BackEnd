package kelompok4.backend.controller;

import kelompok4.backend.entity.User;
import kelompok4.backend.repository.UserRepository;
import kelompok4.backend.util.JWTUtil;
import kelompok4.backend.dto.LoginRequest;
import kelompok4.backend.dto.LoginResponse;
import kelompok4.backend.dto.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JWTUtil JWTUtil;

    // Constructor Injection
    public AuthController(UserRepository userRepository, JWTUtil JWTUtil) {
        this.userRepository = userRepository;
        this.JWTUtil = JWTUtil;
    }

    // Login untuk user biasa
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MessageResponse("User tidak ditemukan"));
            }
            User user = userOptional.get();
            if (!user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Password salah"));
            }

            String token = JWTUtil.generateToken(user);
            // kirim token + userId
            return ResponseEntity.ok(new LoginResponse(token, user.getId()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    // Login untuk admin
    @PostMapping("/login_admin")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MessageResponse("User tidak ditemukan"));
            }
            User user = userOptional.get();
            if (user.getRole() != User.Role.ADMIN) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new MessageResponse("Hanya admin yang bisa login di sini"));
            }
            if (!user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Password salah"));
            }

            String token = JWTUtil.generateToken(user);
            // kirim token + adminId
            return ResponseEntity.ok(new LoginResponse(token, user.getId()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    // Validasi Token
    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestParam String token, @RequestParam String email) {
        try {
            boolean isValid = JWTUtil.validateToken(token, email);
            if (isValid) {
                return ResponseEntity.ok(new MessageResponse("Token valid"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                        new MessageResponse("Token tidak valid atau expired")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("Terjadi kesalahan saat memvalidasi token: " + e.getMessage())
            );
        }
    }
}
