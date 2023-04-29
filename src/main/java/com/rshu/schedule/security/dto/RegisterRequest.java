package com.rshu.schedule.security.dto;

import com.rshu.schedule.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String surname;
    private String login;
    private String password;
    private Role role;
}
