package com.yan.finance.fund.ods.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yan.finance.fund.ods.schema.FincOdsFundInfoItem;

@Mapper
public interface FincOdsFundInfoItemMapper {

	void insertFincFundInfoItem(FincOdsFundInfoItem fincFundInfoItem);
	
	FincOdsFundInfoItem findFincFundInfoItemByPK(FincOdsFundInfoItem fincFundInfoItem);
	
}
