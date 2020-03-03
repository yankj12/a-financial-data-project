package com.yan.finance.fund.spider.main;

import com.yan.finance.fund.spider.service.impl.FundSpiderEastMoneyService;

public class FundMain {

	public static void main(String[] args) {

		crawlFundValuation();
	}
	
	/**
	 * 爬取基金估值
	 */
	public static void crawlFundValuation() {
		//http://fund.eastmoney.com/006087.html
		String webRootUrl = "http://fund.eastmoney.com";
		String fundCode = "006087";
		
		FundSpiderEastMoneyService fundSpiderService = new FundSpiderEastMoneyService();
		try {
			fundSpiderService.crawlFound(webRootUrl, fundCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
