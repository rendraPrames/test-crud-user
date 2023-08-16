package com.rendra.test.dto;

import com.rendra.test.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SuccessResponse<T>{
    private int status;
    private String message;
    private T data;
}
