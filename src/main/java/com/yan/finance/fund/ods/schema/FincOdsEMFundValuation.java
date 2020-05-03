package com.yan.finance.fund.ods.schema;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class FincOdsEMFundValuation{

	public String fundcode;
	
	public String name;
	//净值日期
	public String jzrq;
	// 单位净值
	public BigDecimal dwjz;
	// 估算值
	public BigDecimal gsz;
	// 估算日增长率
	public BigDecimal gszzl;
	// 估算时间
	public String gztime;
	
	public Date insertTime;
	
	public Date updateTime;
}
