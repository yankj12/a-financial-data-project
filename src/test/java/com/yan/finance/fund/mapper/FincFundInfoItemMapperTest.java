package com.yan.finance.fund.mapper;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yan.finance.FinanceApplication;
import com.yan.finance.fund.ods.mapper.FincOdsFundInfoItemMapper;
import com.yan.finance.fund.ods.schema.FincOdsFundInfoItem;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { FinanceApplication.class })
public class FincFundInfoItemMapperTest {

	@Autowired
	public FincOdsFundInfoItemMapper fincFundInfoItemMapper;
	
	@Test
	public void testInsertFincFundInfoItem() {
		FincOdsFundInfoItem fincFundInfoItem = new FincOdsFundInfoItem();
		fincFundInfoItem.setFundCode("006087");
		fincFundInfoItem.setTradeDate("2020-03-02");
		fincFundInfoItem.setValuationTime("2020-03-02 15:00");
		fincFundInfoItem.setValuation(new BigDecimal("0.6287"));
		fincFundInfoItem.setChangeRateYear(new BigDecimal("0.0549"));
		fincFundInfoItem.setChangeRateMonth(new BigDecimal("0.1630"));
		fincFundInfoItem.setInsertTime(new Date());
		fincFundInfoItem.setUpdateTime(new Date());
		fincFundInfoItemMapper.insertFincFundInfoItem(fincFundInfoItem);
	}

	@Test
	public void testFindFincFundInfoItemByPK() {
		FincOdsFundInfoItem fincFundInfoItem = new FincOdsFundInfoItem();
		fincFundInfoItem.setFundCode("006087");
		fincFundInfoItem.setTradeDate("2020-03-02");
		fincFundInfoItem.setValuationTime("2020-03-02 15:00");
		fincFundInfoItem = fincFundInfoItemMapper.findFincFundInfoItemByPK(fincFundInfoItem);
		System.out.println(fincFundInfoItem.getValuation());
	}

}
