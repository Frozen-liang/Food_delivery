package com.ljd.Food_Delivery.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

//    @NotNull(groups = UpdateGroup.class, message = "The id cannot be empty.")
//    @Null(groups = InsertGroup.class, message = "The id must be null.")
    private Long id;

    private String name;

    private String username;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;

    private int status;
}
