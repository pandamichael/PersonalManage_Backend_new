package com.project.personal.dto.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserResponse {

    @Schema(description = "id", example = "")
    private String id;

    @Schema(description = "信箱", example = "")
    private String email;

    @Schema(description = "姓名", example = "")
    private String name;

    @Schema(description = "性別", example = "")
    private String gender;

    @Schema(description = "電話", example = "")
    private String phone;

    @Schema(description = "生日", example = "")
    private Long birthday;
}
