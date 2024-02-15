package com.spcu.dims.spcu_dims.dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {

    private String username;
    private String password;


}
