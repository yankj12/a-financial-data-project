package com.yan.finance.fund.schedule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yan.finance.fund.mapper.FincFundInfoItemMapper;
import com.yan.finance.fund.schema.FincFundInfoItem;
import com.yan.finance.fund.spider.service.impl.FundSpiderEastMoneyService;

@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	public FincFundInfoItemMapper fincFundInfoItemMapper;
	
	@Autowired
	public FundSpiderEastMoneyService fundSpiderEastMoneyService;
	
	@Scheduled(initialDelayString = "20000", fixedDelayString = "60000")    //定时器将在1秒后每隔20秒执行
    public void fundValuationTask() {
		
		logger.info("fundValuationTask begin");
		
		String webRootUrl = "http://fund.eastmoney.com";
		String fundCode = "006087";
		
		FincFundInfoItem fincFundInfoItem = fundSpiderEastMoneyService.crawlFound(webRootUrl, fundCode);
		
		FincFundInfoItem fundInfoItemTmp = fincFundInfoItemMapper.findFincFundInfoItemByPK(fincFundInfoItem);
		
		if(fundInfoItemTmp == null) {
			fincFundInfoItem.setInsertTime(new Date());
			fincFundInfoItem.setUpdateTime(new Date());
			
			fincFundInfoItemMapper.insertFincFundInfoItem(fincFundInfoItem);
			logger.info("fundValuationTask insert FincFundInfoItem.");
		}else {
			logger.info("fundValuationTask FincFundInfoItem exists.");
		}
		
		logger.info("fundValuationTask end");
		
	}
}
