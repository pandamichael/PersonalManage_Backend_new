package com.project.personal.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @NotBlank
    @Size(max = 20)
    @Schema(description = "姓名", example = "")
    private String name;

    @NotBlank
    @Schema(description = "性別", example = "")
    private String gender;

    @NotBlank
    @Size(max = 10)
    @Schema(description = "電話", example = "")
    private String phone;

    @NotNull
    @Schema(description = "生日", example = "")
    private Long birthday;
}
