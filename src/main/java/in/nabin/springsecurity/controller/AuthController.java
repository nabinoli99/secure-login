package in.nabin.springsecurity.controller;

import in.nabin.springsecurity.entities.AppUser;
import in.nabin.springsecurity.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    // --- NAVIGATION (GET METHODS) ---

    @GetMapping("/signup")
    public void showSignupPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/signup.html");
    }

    // This handles the browser address bar request for /login
    // It prevents the "GET not supported" error by showing Spring's default login
    @GetMapping("/login")
    public void redirectToDefaultLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login");
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication) {
        return "<h1>Success!</h1><p>Welcome, " + authentication.getName() +
                ". You are logged in with an encoded password.</p>" +
                "<a href='/logout'>Logout</a>";
    }

    // --- LOGIC (POST METHODS) ---

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "Registration successful!", "targetUrl", "/login"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.get("username"), loginRequest.get("password"))
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            return ResponseEntity.ok("Login successful for: " + auth.getName());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}