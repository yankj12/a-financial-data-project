package com.yan.finance.fund.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yan.finance.fund.schema.FincEMFundValuation;

@Mapper
public interface FincEMFundValuationMapper {

	void insertFincEMFundValuation(FincEMFundValuation fincEMFundValuation);
	
	FincEMFundValuation findFincEMFundValuationByPK(FincEMFundValuation fincEMFundValuation);
	
}
