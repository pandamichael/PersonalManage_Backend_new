
package com.project.personal.dto.request.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * LoginRequest class: 用於接收用戶登入時提交的請求資料。
 * 使用 Lombok 的 @Data 來自動生成 getter、setter、toString 等常用方法，減少樣板程式碼。
 * 將 email 和 password 作為登入的必要欄位。
 */

@Data
public class LoginRequest {

 /**
     * @NotBlank：表示該欄位不能為空。
     * @Schema：用來描述 email 欄位的用途。
     * email：用戶的登入帳號，該註解說明會在 Swagger 文件中顯示，提示這個欄位必須符合 email 格式。
     */

    @NotBlank
    @Schema(description = "email，登入帳號，需驗證輸入資料為 email 格式")
    private String email;

    /**
     * @Schema：描述密碼的驗證規則，要求密碼長度為 8-12 個字符，且至少包含一個小寫字母、一個大寫字母和一個數字。
     * password：用戶的登入密碼。
     */

    @NotBlank
    @Schema(description = "密碼【8-12碼，至少包含一個小寫英文、大寫英文、數字】")
    private String password;

}
