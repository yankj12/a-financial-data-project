package com.yan.finance.fund.ods.schema;

import java.util.Date;

import lombok.Data;

@Data
public class FincOdsEMFund{

	public String fundcode;
	
	public String name;
	
	public Date insertTime;
	
	public Date updateTime;
}
