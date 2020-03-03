package com.yan.finance.fund.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yan.finance.fund.schema.FincFundInfoItem;

@Mapper
public interface FincFundInfoItemMapper {

	void insertFincFundInfoItem(FincFundInfoItem fincFundInfoItem);
	
	FincFundInfoItem findFincFundInfoItemByPK(FincFundInfoItem fincFundInfoItem);
	
}
