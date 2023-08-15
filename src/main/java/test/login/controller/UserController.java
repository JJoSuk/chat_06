package test.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import test.login.entity.User;
import test.login.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입 이동
    @GetMapping("/signup")
    public String signup() {
        return "membership";
    }

    // 로그인 이동
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String register(User user) {
        // 데이터베이스에 회원 정보 저장
        userService.save(user);
        return "redirect:/"; // Redirect to the main page after registration
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findByEmail(currentUser.getUsername());
        model.addAttribute("user", user);
        return "profile"; // Profile page to show user details and edit form
    }

    @PostMapping("/profile/edit")
    public String editProfile(User user, @AuthenticationPrincipal UserDetails currentUser) {
        User existingUser = userService.findByEmail(currentUser.getUsername());
        existingUser.setUsername(user.getUsername());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            // 회원 비밀번호 암호화
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.save(existingUser);
        return "redirect:/profile";
    }

    @GetMapping("/user/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(authentication.getPrincipal());
    }

    @GetMapping("/api/isAuthenticated")
    public ResponseEntity<?> isAuthenticated(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.ok().body(false);
        }
    }
}