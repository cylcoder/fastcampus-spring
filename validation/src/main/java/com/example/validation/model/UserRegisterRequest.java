package com.example.validation.model;

import com.example.validation.annotation.PhoneNumber;
import com.example.validation.annotation.YearMonth;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonNaming(SnakeCaseStrategy.class)
public class UserRegisterRequest {

    private String name;

    private String nickName;

    @Size(min = 4, max = 8)
    @NotBlank
    private String password;

    @NotNull
    @Min(20)
    @Max(40)
    private Integer age;

    @Email
    private String email;

//    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 형식에 맞지 않습니다.")
    @PhoneNumber
    private String phoneNumber;

    @FutureOrPresent
    private LocalDateTime registerAt;

    @YearMonth(pattern = "yyyy-MM")
    private String birthDayYearMonth;

    @AssertTrue(message = "이름 또는 닉네임을 입력해야 합니다.")
    public boolean isNameCheck() { // isXXX 형식이여야 함
        if (name != null && !name.isBlank()) {
            return true;
        }

        if (nickName != null && !nickName.isBlank()) {
            return true;
        }

        return false;
    }

}
