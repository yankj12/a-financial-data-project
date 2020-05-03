package com.yan.finance.fund.ods.schema;

import java.util.Date;

import lombok.Data;

@Data
public class FincOdsInvIndex{
	
	/**
	 * 指数代码
	 */
	public String indexCode;

	public String type;
	public String name;
	
	/**
	 * 英为财情的curr_id
	 */
	public String currId;
	
	/**
	 * 首个交易日
	 */
	public String firstTradeDate;
	
	public Date insertTime;
	
	public Date updateTime;
}
