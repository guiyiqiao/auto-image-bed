package ink.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

/**
 * @Author 桂乙侨
 * @Date 2020/4/1 14:35
 * @Version 1.0
 */
public class SimpleResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SimpleResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public SimpleResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
