package com.hfut.invigilate.model.user;

import io.swagger.annotations.ApiModelProperty;

public enum RoleEnum {

    @ApiModelProperty("管理员")
    Admin,
    @ApiModelProperty("老师")
    Teacher,
    @ApiModelProperty("二级管理")
    Manager;

}
