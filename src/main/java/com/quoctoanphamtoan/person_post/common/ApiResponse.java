package com.quoctoanphamtoan.person_post.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse {
    private Integer status;
    private Object data;
    private Object error;

    public ApiResponse() {
        this.status = HttpStatus.OK.value();
        this.data = data;
        this.error = error;
    }

}
