package com.application.miniproject.user.dto;

import com.application.miniproject.user.User;
import com.application.miniproject.util.type.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserRequest {

    @Getter
    @Setter
    public static class JoinDTO {
        @NotBlank(message = "유저명은 공백일 수 없습니다.")
        private String username;
        @NotBlank(message = "email 은 공백일 수 없습니다.")
        @Email(message = "유효하지 않는 이메일 형태입니다.")
        private String email;
        @NotBlank(message = "패스워드는 공백일 수 없습니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,15}$",
                message = "최소 하나의 알파벳, 하나의 숫자, 하나의 특수 문자를 포함해야 합니다.")
        private String password;

        public User toCipherEntity(String password) {
            return User.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .role(UserType.USER)
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
        }
    }

    @Getter
    @Setter
    public static class LoginDTO {
        @Email(message = "유효하지 않는 이메일 형태입니다.")
        private String email;
        @NotBlank(message = "패스워드는 공백일 수 없습니다.")
        private String password;
    }

    @Getter
    @Setter
    public static class EmailDTO {
        @Email(message = "유효하지 않는 이메일 형태입니다.")
        private String email;
    }
}
