package com.yan.finance.fund.spider.service.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yan.finance.FinanceApplication;
import com.yan.finance.fund.ods.mapper.FincOdsEMFundValuationMapper;
import com.yan.finance.fund.ods.schema.FincOdsEMFundValuation;
import com.yan.finance.fund.ods.schema.FincOdsFundInfoItem;
import com.yan.finance.fund.ods.spider.service.impl.FundSpiderEastMoneyService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { FinanceApplication.class })
public class FundSpiderEastMoneyServiceTest {

	@Autowired
	public FundSpiderEastMoneyService fundSpiderEastMoneyService;
	
	@Autowired
	public FincOdsEMFundValuationMapper fincEMFundValuationMapper;
	
	@Ignore
	@Test
	public void testCrawlFound() {
		String webRootUrl = "http://fund.eastmoney.com";
		String fundCode = "006087";
		
		FincOdsFundInfoItem fincFundInfoItem = fundSpiderEastMoneyService.crawlFound(webRootUrl, fundCode);
		System.out.println(fincFundInfoItem.getValuation());
	}

	@Test
	public void testCrawlFoundValuationFromJson() {
		String webRootUrl = "http://fund.eastmoney.com";
		String fundCode = "006087";
		
		FincOdsEMFundValuation fincEMFundValuation = fundSpiderEastMoneyService.crawlFoundValuationFromJson(webRootUrl, fundCode);
		System.out.println(fincEMFundValuation.getGsz());
		fincEMFundValuationMapper.insertFincEMFundValuation(fincEMFundValuation);
	}
}
