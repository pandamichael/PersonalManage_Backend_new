package com.project.personal.util;

import com.project.personal.exception.RequestDataFormatException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 驗證密碼格式：8-12 碼，至少包含一個小寫英文、一個大寫英文和一個數字
     * @param password
     * @return
     */
    public static void validatePasswordFormat(String password) throws RequestDataFormatException {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,12}$";

        if(!password.matches(passwordPattern)) {
            throw new RequestDataFormatException("密碼格式錯誤");
        }
    }

    /**
     * 密碼加密
     */
    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * 驗證密碼是否匹配
     */
    public static boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
