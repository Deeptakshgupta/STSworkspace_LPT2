package com.wcs.auth.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class LoginRequest {
    @NotBlank
//    @ApiModelProperty(example = "abc@gmail.com", required = true)
    private String userId;
    @NotBlank
//    @ApiModelProperty(example = "Xyz#123", required = true)
    private String password;
}
