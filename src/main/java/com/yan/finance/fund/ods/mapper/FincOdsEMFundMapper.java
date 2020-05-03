package com.yan.finance.fund.ods.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yan.finance.fund.ods.schema.FincOdsEMFund;

@Mapper
public interface FincOdsEMFundMapper {

	void insertFincFincEMFund(FincOdsEMFund fincEMFund);
	
	FincOdsEMFund findFincEMFundByPK(String fundcode);
	
	List<FincOdsEMFund> findFincEMFunds();
	
}
