package com.hfut.invigilate.mapper;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.hfut.invigilate.entity.Exam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hfut.invigilate.model.exam.ExamPageQueryDTO;
import com.hfut.invigilate.model.exam.ExamTeachersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考试 Mapper 接口
 * </p>
 *
 * @author 常珂洁
 * @since 2022-02-10
 */
public interface ExamMapper extends BaseMapper<Exam> {

    List<ExamTeachersVO> page(@Param("pos") Integer pos, @Param("limit") Integer limit, @Param("query") ExamPageQueryDTO query);

    Long count(@Param("query") ExamPageQueryDTO query);

}
