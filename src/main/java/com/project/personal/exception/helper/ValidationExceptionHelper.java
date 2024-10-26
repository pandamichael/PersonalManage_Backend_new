package com.project.personal.exception.helper;

import com.project.personal.dto.response.ApiResponse;
import com.project.personal.enums.CommonCode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationExceptionHelper {

    public static final String NOT_BLANK = "NotBlank";
    public static final String NOT_NULL = "NotNull";
    public static final String SIZE = "Size";
    public static final String DIGITS = "Digits";

    // RequestBody 的驗證會來這邊
    public static <T extends ApiResponse> T methodArgumentNotValidExceptionHandle(T resp, MethodArgumentNotValidException e) {
        FieldError fieldError = getFirstFieldErrors(e);

        String fieldName = fieldError.getField();
        String defaultMessage = fieldError.getDefaultMessage();
        String validateAnnotation = fieldError.getCode();
        Object[] arguments = fieldError.getArguments();

        String message = "";
        CommonCode commonCode = null;

        switch (validateAnnotation) {
            case NOT_BLANK, NOT_NULL -> {
                commonCode = CommonCode.N2001;
                message = commonCode.makeMessage(new String[]{fieldName});
            }
            case DIGITS -> {
                commonCode = CommonCode.N2002;
                List<String> digits = extractDigits(defaultMessage);
                message = commonCode.makeMessage(new String[]{fieldName + "做多只能 " + digits.get(0) + " 位數整數與 " + digits.get(1) + " 位數小數"});
            }
            case SIZE -> {
                commonCode = CommonCode.N2002;
                // arguments[1]：max
                // arguments[2]：min
                if (arguments[1] == arguments[2]) {
                    message = commonCode.makeMessage(new String[]{fieldName + " " + "長度必須等於" + " " + arguments[1]});
                } else if (arguments[1] != arguments[2] && !arguments[2].toString().equals("0") && !arguments[2].toString().equals("2147483647")) {
                    message = commonCode.makeMessage(new String[]{fieldName + " " + "長度必須介於" + " " + arguments[2] + " 至 " + arguments[1]});
                } else if (arguments[1] != arguments[2] && !arguments[2].toString().equals("0")) {
                    message = commonCode.makeMessage(new String[]{fieldName + " " + "長度必須大於" + " " + arguments[2]});
                } else {
                    message = commonCode.makeMessage(new String[]{fieldName + " " + "長度必須小於" + " " + arguments[1]});
                }
            }
            case null, default -> {
                commonCode = CommonCode.N2002;
                message = commonCode.makeMessage(new String[]{fieldName + " " + defaultMessage});
            }
        }

        setCommonResponse(resp, commonCode, message);

        return resp;
    }

    /**
     * Spring validation 預設會抓全部沒有驗證過的，我們這邊只抓第一個
     */
    private static FieldError getFirstFieldErrors(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return fieldErrors.get(0);
    }

    private static List<String> extractDigits(String input) {
        List<String> resultList = new ArrayList<>();
        Pattern pattern = Pattern.compile("<(.*?) digits>");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            resultList.add(matcher.group(1));
        }
        return resultList;
    }

    private static void setCommonResponse(ApiResponse<HashMap<Object, Object>> resp, CommonCode code, String message) {
        resp.setCode(code.getCode());
        resp.setMessage(message);
        resp.setResult(new HashMap<>());
    }
}
