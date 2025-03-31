package com.example.Leavefy_LAMS.Controller;



import com.example.Leavefy_LAMS.Model.User;
import com.example.Leavefy_LAMS.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return adminService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return adminService.getUserById(id)
                .map(existingUser -> {
                    user.setUserId(id);
                    User updatedUser = adminService.updateUser(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return adminService.getUserById(id)
                .map(user -> {
                    adminService.deleteUser(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<User>> getUsersByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(adminService.getUsersByDepartment(departmentId));
    }

    @GetMapping("/supervisor/{supervisorId}")
    public ResponseEntity<List<User>> getUsersBySupervisor(@PathVariable Long supervisorId) {
        return ResponseEntity.ok(adminService.getUsersBySupervisor(supervisorId));
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable Long roleId) {
        return ResponseEntity.ok(adminService.getUsersByRole(roleId));
    }
}

