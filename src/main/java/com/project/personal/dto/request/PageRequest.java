package com.project.personal.dto.request;

import lombok.Data;

@Data
public class PageRequest {

    private Integer offset = 0;

    private Integer limit = 10;
}
