package com.yan.finance.fund.ods.schedule;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yan.finance.fund.ods.mapper.FincOdsEMFundMapper;
import com.yan.finance.fund.ods.mapper.FincOdsEMFundValuationMapper;
import com.yan.finance.fund.ods.mapper.FincOdsFundInfoItemMapper;
import com.yan.finance.fund.ods.schema.FincOdsEMFund;
import com.yan.finance.fund.ods.schema.FincOdsEMFundValuation;
import com.yan.finance.fund.ods.spider.service.impl.FundSpiderEastMoneyService;

@Component
public class FincOdsScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(FincOdsScheduledTasks.class);

	@Autowired
	public FincOdsFundInfoItemMapper fincFundInfoItemMapper;
	
	@Autowired
	public FincOdsEMFundValuationMapper fincEMFundValuationMapper;
	
	@Autowired
	public FincOdsEMFundMapper fincEMFundMapper;
	
	@Autowired
	public FundSpiderEastMoneyService fundSpiderEastMoneyService;
	
	@Scheduled(initialDelayString = "60000", fixedDelayString = "60000")    //定时器将在1秒后每隔20秒执行
    public void fundValuationTask() {
		
		logger.info("fundValuationTask begin");
		
		String webRootUrl = "http://fund.eastmoney.com";
		
		List<FincOdsEMFund> fincEMFunds = fincEMFundMapper.findFincEMFunds();
		
		if(fincEMFunds != null) {
			for(FincOdsEMFund fincEMFund: fincEMFunds) {
				String fundCode = fincEMFund.getFundcode();
				
				FincOdsEMFundValuation fincEMFundValuation = fundSpiderEastMoneyService.crawlFoundValuationFromJson(webRootUrl, fundCode);
				//System.out.println(fincEMFundValuation.getGsz());
				
				if(fincEMFundValuation != null) {
					FincOdsEMFundValuation fincEMFundValuationTmp = fincEMFundValuationMapper.findFincEMFundValuationByPK(fincEMFundValuation);
					if(fincEMFundValuationTmp == null) {
						fincEMFundValuation.setInsertTime(new Date());
						fincEMFundValuation.setUpdateTime(new Date());
						
						fincEMFundValuationMapper.insertFincEMFundValuation(fincEMFundValuation);
						logger.info("fundValuationTask insert fincEMFundValuation. valuation:" + fincEMFundValuation.getGsz());
					}else {
						logger.info("fundValuationTask fincEMFundValuation exists.");
					}
				}else {
					logger.info("fundValuationTask [" + fundCode + "] 获取数据错误。");
				}
				
			}
		}
		
		logger.info("fundValuationTask end");
		
	}
}
