CREATE TABLE finc_163_index (
	trade_date varchar(20) COMMENT '日期',
	index_code varchar(20) COMMENT '股票代码',
	name varchar(120) COMMENT '名称',
	closing_price DECIMAL(10,4) COMMENT '收盘价',
	highest_price DECIMAL(10,4) COMMENT '最高价',
	lowest_price DECIMAL(10,4) COMMENT '最低价',
	opening_price DECIMAL(10,4) COMMENT '开盘价',
	pre_price DECIMAL(10,4) COMMENT '前收盘',
	change_amount DECIMAL(8,4) COMMENT '涨跌额',
	change_rate DECIMAL(8,4) COMMENT '涨跌幅',
	total_volume DECIMAL(30,4) COMMENT '成交量',
	total_amount DECIMAL(30,4) COMMENT '成交金额',
	type varchar(20) COMMENT '类型',
	insertTime datetime DEFAULT NULL,
	updateTime datetime DEFAULT NULL,
 	PRIMARY KEY (trade_date, index_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE finc_fund_info_item (
	fund_code varchar(20) COMMENT '基金代码',
	trade_date varchar(20) COMMENT '日期',
	valuation_time varchar(40) COMMENT '估值时间',
	valuation DECIMAL(10,6) COMMENT '估值',
	change_rate_month DECIMAL(10,6) COMMENT '近1月涨跌',
	change_rate_year DECIMAL(10,6) COMMENT '近1年涨跌',
	type varchar(20) COMMENT '类型',
	insert_time datetime DEFAULT NULL,
	update_time datetime DEFAULT NULL,
 	PRIMARY KEY (fund_code, trade_date, valuation_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE finc_em_fund_valuation (
	fundcode varchar(20) COMMENT '基金代码',
	name varchar(255) COMMENT '基金名称',
	jzrq varchar(20) COMMENT '净值日期',
	gztime varchar(40) COMMENT '估值时间',
	dwjz DECIMAL(10,6) COMMENT '单位净值',
	gsz DECIMAL(10,6) COMMENT '估算值',
	gszzl DECIMAL(10,6) COMMENT '估算日增长率',
	insert_time datetime DEFAULT NULL,
	update_time datetime DEFAULT NULL,
 	PRIMARY KEY (fundcode, gztime)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


