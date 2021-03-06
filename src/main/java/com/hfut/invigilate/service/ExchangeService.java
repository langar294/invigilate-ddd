package com.hfut.invigilate.service;

import com.hfut.invigilate.entity.Exchange;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hfut.invigilate.model.exchange.IntendVO;
import com.hfut.invigilate.model.exchange.InvigilateExchangeVO;
import com.hfut.invigilate.model.exchange.SelfExchangeIntendVO;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 交换记录 服务类
 * </p>
 *
 * @author 常珂洁
 * @since 2022-02-10
 */
public interface ExchangeService extends IService<Exchange> {

    Exchange getByCode(Long code);

    List<SelfExchangeIntendVO> listMyIntend(Integer workId);

    List<IntendVO> listOtherIntend(Long invigilateCode);

    List<InvigilateExchangeVO> listInvigilateExchanges(Integer workId, LocalDate startDate, LocalDate endDate);

}
