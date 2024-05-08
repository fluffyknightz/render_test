package com.spcu.dims.spcu_dims.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndexDto implements Serializable {

    private int parish;
    private int activity;

}
