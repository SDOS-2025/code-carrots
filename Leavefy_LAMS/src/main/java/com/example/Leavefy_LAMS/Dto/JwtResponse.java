package com.example.Leavefy_LAMS.Dto;



import com.example.Leavefy_LAMS.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private User user;

    public JwtResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public JwtResponse(String jwt) {
        this.token = jwt;
    }
}