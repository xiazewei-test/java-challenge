package jp.co.axa.apidemo.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

  @NotBlank
  private String name;

  @NotNull
  private Integer salary;

  @NotBlank
  private String department;
}