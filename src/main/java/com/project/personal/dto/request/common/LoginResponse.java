
package com.project.personal.dto.request.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginResponse {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "信箱")
    private String email;

    @Schema(description = "姓名")
    private String name;

}
