package com.hfut.invigilate.service;

import com.hfut.invigilate.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hfut.invigilate.model.user.UserDepartmentVO;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author 常珂洁
 * @since 2022-02-10
 */
public interface UserService extends IService<User> {

    List<UserDepartmentVO> getDepartmentUser(Integer departmentId, LocalDate startDate);
}
