package com.yan.finance.fund.ods.schema;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class FincOdsFundInfoItem{

	/**
	 * 基金代码
	 */
	public String fundCode;

	/**
	 * 交易日期
	 */
	public String tradeDate;
	
	/**
	 * 估值时间
	 */
	public String valuationTime;
	
	/**
	 * 基金估值
	 */
	public BigDecimal valuation;
	
	/**
	 * 近1月涨跌
	 */
	public BigDecimal changeRateMonth;
	
	/**
	 * 近1年涨跌
	 */
	public BigDecimal changeRateYear;

	public Date insertTime;
	
	public Date updateTime;
}
