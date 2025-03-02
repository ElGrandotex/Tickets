package com.jv.Tickets.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    Boolean isSuccess;
    String message;
    T data;
}
