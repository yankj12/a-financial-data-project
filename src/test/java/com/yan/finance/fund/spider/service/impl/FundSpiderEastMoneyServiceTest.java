package com.yan.finance.fund.spider.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yan.finance.FinanceApplication;
import com.yan.finance.fund.schema.FincFundInfoItem;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { FinanceApplication.class })
public class FundSpiderEastMoneyServiceTest {

	@Autowired
	public FundSpiderEastMoneyService fundSpiderEastMoneyService;
	
	@Test
	public void testCrawlFound() {
		String webRootUrl = "http://fund.eastmoney.com";
		String fundCode = "006087";
		
		FincFundInfoItem fincFundInfoItem = fundSpiderEastMoneyService.crawlFound(webRootUrl, fundCode);
		System.out.println(fincFundInfoItem.getValuation());
	}

}
