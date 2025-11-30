package com.example.validation.model;

import com.example.validation.annotation.PhoneNumber;
import com.example.validation.annotation.YearMonth;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterRequest {

//  @NotBlank
  private String name;

  private String nickName;

  @NotBlank
  @Size(min = 1, max = 12)
  private String password;

  @NotNull
  @Min(1)
  @Max(100)
  private Integer age;

  @Email
  private String email;

//  @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
  @PhoneNumber
  private String phoneNumber;

  @FutureOrPresent
  private LocalDateTime registerAt;

  @YearMonth(pattern = "yyyy-MM")
  private String birthDayYearMonth;

  @AssertTrue(message = "Name or nickname is required")
  public boolean isNameCheck() {
      return StringUtils.hasText(name) || StringUtils.hasText(nickName);
  }

}
