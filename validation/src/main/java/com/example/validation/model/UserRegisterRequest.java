package com.example.validation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonNaming(SnakeCaseStrategy.class)
public class UserRegisterRequest {

    @NotNull // != null
    @NotEmpty // != null && != ""
    @NotBlank // != null && != "" && != "  "
    private String name;

    @Size(min = 4, max = 8) // 4-8 자리의 비밀번호
    @NotBlank
    private String password;

    @NotNull
    @Min(20)
    @Max(40)
    private Integer age;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 형식에 맞지 않습니다.")
    private String phoneNumber;

    @FutureOrPresent
    private LocalDateTime registerAt;

}
