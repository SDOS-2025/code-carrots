package com.example.Leavefy_LAMS.Service;



import com.example.Leavefy_LAMS.Model.User;
import com.example.Leavefy_LAMS.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<User> getUsersBySupervisor(Long supervisorId) {
        return adminRepository.findBySupervisorUserId(supervisorId);
    }

    public List<User> getUsersByRole(Long roleId) {
        return adminRepository.findByRoleRoleId(roleId);
    }

    public List<User> getUsersByDepartment(Long departmentId) {
        return adminRepository.findByDepartmentDepartmentId(departmentId);
    }

    public Optional<User> getUserById(Long id) {
        return adminRepository.findById(id);
    }

    public void deleteUser(Long id) {
        adminRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return adminRepository.save(user);
    }

    public User createUser(User user) {
        return adminRepository.save(user);
    }

    public List<User> getAllUsers() {
        return adminRepository.findAll();
    }

    // Add other methods as needed
}

