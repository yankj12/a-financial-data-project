package com.yan.finance.fund.schedule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yan.finance.fund.mapper.FincEMFundValuationMapper;
import com.yan.finance.fund.mapper.FincFundInfoItemMapper;
import com.yan.finance.fund.schema.FincEMFundValuation;
import com.yan.finance.fund.schema.FincFundInfoItem;
import com.yan.finance.fund.spider.service.impl.FundSpiderEastMoneyService;

@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	public FincFundInfoItemMapper fincFundInfoItemMapper;
	
	@Autowired
	public FincEMFundValuationMapper fincEMFundValuationMapper;
	
	@Autowired
	public FundSpiderEastMoneyService fundSpiderEastMoneyService;
	
	@Scheduled(initialDelayString = "20000", fixedDelayString = "60000")    //定时器将在1秒后每隔20秒执行
    public void fundValuationTask() {
		
		logger.info("fundValuationTask begin");
		
		String webRootUrl = "http://fund.eastmoney.com";
		String fundCode = "006087";
		
		FincEMFundValuation fincEMFundValuation = fundSpiderEastMoneyService.crawlFoundValuationFromJson(webRootUrl, fundCode);
		System.out.println(fincEMFundValuation.getGsz());
		
		FincEMFundValuation fincEMFundValuationTmp = fincEMFundValuationMapper.findFincEMFundValuationByPK(fincEMFundValuation);
		if(fincEMFundValuationTmp == null) {
			fincEMFundValuation.setInsertTime(new Date());
			fincEMFundValuation.setUpdateTime(new Date());
			
			fincEMFundValuationMapper.insertFincEMFundValuation(fincEMFundValuation);
			logger.info("fundValuationTask insert fincEMFundValuation.");
		}else {
			logger.info("fundValuationTask fincEMFundValuation exists.");
		}
		
		logger.info("fundValuationTask end");
		
	}
}
