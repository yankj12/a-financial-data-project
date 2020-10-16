package com.yan.finance.fund.mapper;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yan.finance.FinanceApplication;
import com.yan.finance.fund.ods.mapper.FincOdsEMFundMapper;
import com.yan.finance.fund.ods.schema.FincOdsEMFund;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { FinanceApplication.class })
public class FincEMFundMapperTest {

	@Autowired
	public FincOdsEMFundMapper fincEMFundMapper;
	
	@Ignore
	@Test
	public void testInsertFincFincEMFund() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testFindFincEMFundByPK() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindFincEMFunds() {
		List<FincOdsEMFund> fincEMFunds = fincEMFundMapper.findFincEMFunds();
		
		for(FincOdsEMFund fincEMFund: fincEMFunds) {
			System.out.println(fincEMFund.getFundcode());
		}
	}

}
