package com.example.springbasicjsp.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private long id;

    @NotNull(message = "할 일은 필수 입력 항목입니다.")
    @Size(max = 200, message = "할 일은 최대 200자 이내로 입력해야 합니다.")
    private String work;

    @NotNull(message = "비밀번호는 필수 입력 항목입니다.")
    private Long password;

    @NotNull(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private String origindate;
    private String date;
    private String name;
    private long userId;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", origindate='" + origindate + '\'' +
                ", date='" + date + '\'' +
                ", work='" + work + '\'' +
                ", password=" + password +
                ", userId=" + userId +
                '}';
    }
}
