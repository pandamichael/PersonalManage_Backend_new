package com.project.personal.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ApiPageResponse<T> {

    @Schema(description = "回傳資料")
    List<T> data;

    @Schema(description = "回傳資料總筆數")
    Integer count = 0;
}
