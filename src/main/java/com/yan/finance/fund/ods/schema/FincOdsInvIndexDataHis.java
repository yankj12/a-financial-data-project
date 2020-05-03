package com.yan.finance.fund.ods.schema;

import java.util.Date;

import lombok.Data;

@Data
public class FincOdsInvIndexDataHis{
	
	/**
	 * 指数代码
	 */
	public String indexCode;

	/**
	 * 交易日期
	 */
	public String tradeDate;
	
	public String type;
	public String name;
	
	/**
	 * 英为财情的curr_id
	 */
	public String currId;
	
	public String closingPrice;
	
	public String highestPrice;
	
	public String lowestPrice;
	
	public String openingPrice;
	
	/**
	 * 涨跌幅
	 */
	public String changeRate;
	
	/**
	 * 涨跌额
	 */
	public String changeAmount;
	
	/**
	 * 成交量
	 */
	public String totalVolume;
	
	public Date insertTime;
	
	public Date updateTime;
}
