package com.yan.finance.fund.ods.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yan.finance.fund.ods.schema.FincOdsEMFundValuation;

@Mapper
public interface FincOdsEMFundValuationMapper {

	void insertFincEMFundValuation(FincOdsEMFundValuation fincEMFundValuation);
	
	FincOdsEMFundValuation findFincEMFundValuationByPK(FincOdsEMFundValuation fincEMFundValuation);
	
}
