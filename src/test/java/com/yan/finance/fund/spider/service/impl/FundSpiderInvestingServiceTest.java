package com.yan.finance.fund.spider.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yan.finance.FinanceApplication;
import com.yan.finance.fund.ods.mapper.FincOdsInvIndexDataHisMapper;
import com.yan.finance.fund.ods.schema.FincOdsInvIndexDataHis;
import com.yan.finance.fund.ods.spider.service.impl.FundSpiderInvestingService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { FinanceApplication.class })
public class FundSpiderInvestingServiceTest {

	@Autowired
	public FundSpiderInvestingService fundSpiderInvestingService;
	
	@Autowired
	public FincOdsInvIndexDataHisMapper fincInvIndexDataHisMapper;
	
	//@Test
	public void testDateMonthPeriod() {
		try {
			String startDateStr = "1990/01/01";
			String endDateStr = "2020/01/31";
			
			List<String> startEndDateStrs = new ArrayList<String>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			Date startDate = sdf.parse(startDateStr);
			Date endDate = sdf.parse(endDateStr);
			
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(startDate);

			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endDate);

			while(startCalendar.before(endCalendar)) {
				String sDate = sdf.format(startCalendar.getTime());
				
				startCalendar.add(Calendar.DATE, 30);
				
				String eDate = sdf.format(startCalendar.getTime());
				if(startCalendar.before(endCalendar) || startCalendar.equals(endCalendar)) {
					eDate = sdf.format(startCalendar.getTime());
				}else {
					eDate = sdf.format(endCalendar.getTime());
				}
				
				String startEndDateStr = sDate + "," + eDate;
				//System.out.println(startEndDateStr);
				
				startEndDateStrs.add(startEndDateStr);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	//@Test
	public void testCrawlHistoricalData() {
		String indexCode = "000300";
		String currId = "940801";
		String name = "沪深300";
		String startDate = "2005/01/01";
		String endDate = "2020/04/20";
		List<FincOdsInvIndexDataHis> fincInvIndexDataHisList = fundSpiderInvestingService.crawlHistoricalData(indexCode, currId, name, startDate, endDate);
		
		if(fincInvIndexDataHisList != null && fincInvIndexDataHisList.size() > 0) {
			
			// 每50条存一次
			int period = 50;
			
			int fromIndex = 0;
			int endIndex = fincInvIndexDataHisList.size();
			
			while(fromIndex <= endIndex) {

				int toIndex = fromIndex + period;
				if(toIndex > endIndex) {
					toIndex = endIndex;
				}
				
				List<FincOdsInvIndexDataHis> subList = fincInvIndexDataHisList.subList(fromIndex, toIndex);
				fincInvIndexDataHisMapper.deleteBatchFincInvIndexDataHisByPK(subList);
				fincInvIndexDataHisMapper.insertBatchFincInvIndexDataHis(subList);
				
				fromIndex = toIndex + 1;
			}
			
		}
	}

	
	@Test
	public void testCrawlHistoricalData2() {
//		String indexCode = "000300";
//		String currId = "940801";
//		String name = "沪深300";
//		String startDate = "2005/01/01";
//		String endDate = "2020/04/20";
		
		String indexCode = "000001";
		String currId = "40820";
		String name = "上证指数";
		String startDate = "2000/01/01";
		String endDate = "2001/01/01";
		
		List<FincOdsInvIndexDataHis> fincInvIndexDataHisList = fundSpiderInvestingService.crawlHistoricalDataByShortDatePeriod(indexCode, currId, name, startDate, endDate);
		
		if(fincInvIndexDataHisList != null && fincInvIndexDataHisList.size() > 0) {
			
			for(FincOdsInvIndexDataHis InvIndexDataHis : fincInvIndexDataHisList) {
				fincInvIndexDataHisMapper.deleteFincInvIndexDataHisByPK(InvIndexDataHis);
				fincInvIndexDataHisMapper.insertFincInvIndexDataHis(InvIndexDataHis);
			}
		}
	}
}
