package com.wcs.auth.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class SignupRequest {
    @ApiModelProperty(example = "Abhishek", required = true)
    @NotBlank
    private String firstName;

    @ApiModelProperty(example = "Pandey")
    private String lastName;

    @ApiModelProperty(example = "abhi@1234", required = true)
    @Size(min = 8, max = 20)
    @NotBlank
    private String userId;

    @ApiModelProperty(example = "1234@gfhfg", required = true)
    @Size(min = 6, max = 10)
    @NotBlank
    private String password;

    @ApiModelProperty(example = "1234567890", required = true)
    @Size(max = 10)
    @NotBlank
    private String contact;
}
