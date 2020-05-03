CREATE TABLE finc_ods_163_index (
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


CREATE TABLE finc_ods_inv_index (
	trade_date varchar(20) COMMENT '日期',
	index_code varchar(20) COMMENT '指数代码',
	name varchar(120) COMMENT '名称',
	closing_price DECIMAL(10,4) COMMENT '收盘价',
	highest_price DECIMAL(10,4) COMMENT '最高价',
	lowest_price DECIMAL(10,4) COMMENT '最低价',
	opening_price DECIMAL(10,4) COMMENT '开盘价',
	change_rate DECIMAL(8,4) COMMENT '涨跌幅',
	total_volume varchar(20) COMMENT '成交量',
	insertTime datetime DEFAULT NULL,
	updateTime datetime DEFAULT NULL,
 	PRIMARY KEY (trade_date, index_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE finc_ods_fund_info_item (
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

CREATE TABLE finc_ods_em_fund_valuation (
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

CREATE TABLE finc_ods_em_fund (
	fundcode varchar(20) COMMENT '基金代码',
	name varchar(255) COMMENT '基金名称',
	insert_time datetime DEFAULT NULL,
	update_time datetime DEFAULT NULL,
 	PRIMARY KEY (fundcode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE finc_ods_index (
	index_code_full varchar(20) COMMENT '指数代码(带交易所)',
	index_code varchar(20) COMMENT '指数代码',
	name varchar(255) COMMENT '指数名称',
	exchange_code varchar(20) COMMENT '交易所代码',
	region_code varchar(20) COMMENT '国际域名缩写',
	sina_param varchar(20) COMMENT '新浪代码参数',
	insert_time datetime DEFAULT NULL,
	update_time datetime DEFAULT NULL,
 	PRIMARY KEY (index_code_full)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



insert into finc_ods_index (index_code_full, name) values ('HSI.HI', '恒生指数');
insert into finc_ods_index (index_code_full, name) values ('HSCEI.HI', '恒生国企');
insert into finc_ods_index (index_code_full, name) values ('000016.SH', '上证50');
insert into finc_ods_index (index_code_full, name) values ('000989.SH', '可选消费');
insert into finc_ods_index (index_code_full, name) values ('000905.SH', '中证500');
insert into finc_ods_index (index_code_full, name) values ('000922.CSI', '中证红利');
insert into finc_ods_index (index_code_full, name) values ('000300.SH', '沪深300');
insert into finc_ods_index (index_code_full, name) values ('000991.SH', '全指医药');
insert into finc_ods_index (index_code_full, name) values ('SPX.GI', '标普500');
insert into finc_ods_index (index_code_full, name) values ('NDX.GI', '纳斯达克100');
insert into finc_ods_index (index_code_full, name) values ('399975.CSI', '证券公司');



CREATE TABLE region (
	region_code varchar(10) COMMENT '国际域名缩写',
	name varchar(120) COMMENT '国家或地区中文名',
	ename varchar(120) COMMENT '国家或地区英文名',
	insert_time datetime DEFAULT NULL,
	update_time datetime DEFAULT NULL,
 	PRIMARY KEY (region_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into region (region_code, name, ename) values ('AD', '安道尔共和国', 'Andorra');
insert into region (region_code, name, ename) values ('AE', '阿拉伯联合酋长国', 'United Arab Emirates');
insert into region (region_code, name, ename) values ('AF', '阿富汗', 'Afghanistan');
insert into region (region_code, name, ename) values ('AG', '安提瓜和巴布达', 'Antigua and Barbuda');
insert into region (region_code, name, ename) values ('AI', '安圭拉岛', 'Anguilla');
insert into region (region_code, name, ename) values ('AL', '阿尔巴尼亚', 'Albania');
insert into region (region_code, name, ename) values ('AM', '亚美尼亚', 'Armenia');
insert into region (region_code, name, ename) values ('AO', '安哥拉', 'Angola');
insert into region (region_code, name, ename) values ('AR', '阿根廷', 'Argentina');
insert into region (region_code, name, ename) values ('AT', '奥地利', 'Austria');
insert into region (region_code, name, ename) values ('AU', '澳大利亚', 'Australia');
insert into region (region_code, name, ename) values ('AZ', '阿塞拜疆', 'Azerbaijan');
insert into region (region_code, name, ename) values ('BB', '巴巴多斯', 'Barbados');
insert into region (region_code, name, ename) values ('BD', '孟加拉国', 'Bangladesh');
insert into region (region_code, name, ename) values ('BE', '比利时', 'Belgium');
insert into region (region_code, name, ename) values ('BF', '布基纳法索', 'Burkina-faso');
insert into region (region_code, name, ename) values ('BG', '保加利亚', 'Bulgaria');
insert into region (region_code, name, ename) values ('BH', '巴林', 'Bahrain');
insert into region (region_code, name, ename) values ('BI', '布隆迪', 'Burundi');
insert into region (region_code, name, ename) values ('BJ', '贝宁', 'Benin');
insert into region (region_code, name, ename) values ('BL', '巴勒斯坦', 'Palestine');
insert into region (region_code, name, ename) values ('BM', '百慕大群岛', 'Bermuda Is.');
insert into region (region_code, name, ename) values ('BN', '文莱', 'Brunei');
insert into region (region_code, name, ename) values ('BO', '玻利维亚', 'Bolivia');
insert into region (region_code, name, ename) values ('BR', '巴西', 'Brazil');
insert into region (region_code, name, ename) values ('BS', '巴哈马', 'Bahamas');
insert into region (region_code, name, ename) values ('BW', '博茨瓦纳', 'Botswana');
insert into region (region_code, name, ename) values ('BY', '白俄罗斯', 'Belarus');
insert into region (region_code, name, ename) values ('BZ', '伯利兹', 'Belize');
insert into region (region_code, name, ename) values ('CA', '加拿大', 'Canada');
insert into region (region_code, name, ename) values ('CF', '中非共和国', 'Central African Republic');
insert into region (region_code, name, ename) values ('CG', '刚果', 'Congo');
insert into region (region_code, name, ename) values ('CH', '瑞士', 'Switzerland');
insert into region (region_code, name, ename) values ('CK', '库克群岛', 'Cook Is.');
insert into region (region_code, name, ename) values ('CL', '智利', 'Chile');
insert into region (region_code, name, ename) values ('CM', '喀麦隆', 'Cameroon');
insert into region (region_code, name, ename) values ('CN', '中国', 'China');
insert into region (region_code, name, ename) values ('CO', '哥伦比亚', 'Colombia');
insert into region (region_code, name, ename) values ('CR', '哥斯达黎加', 'Costa Rica');
insert into region (region_code, name, ename) values ('CS', '捷克', 'Czech');
insert into region (region_code, name, ename) values ('CU', '古巴', 'Cuba');
insert into region (region_code, name, ename) values ('CY', '塞浦路斯', 'Cyprus');
insert into region (region_code, name, ename) values ('CZ', '捷克', 'Czech Republic');
insert into region (region_code, name, ename) values ('DE', '德国', 'Germany');
insert into region (region_code, name, ename) values ('DJ', '吉布提', 'Djibouti');
insert into region (region_code, name, ename) values ('DK', '丹麦', 'Denmark');
insert into region (region_code, name, ename) values ('DO', '多米尼加共和国', 'Dominica Rep.');
insert into region (region_code, name, ename) values ('DZ', '阿尔及利亚', 'Algeria');
insert into region (region_code, name, ename) values ('EC', '厄瓜多尔', 'Ecuador');
insert into region (region_code, name, ename) values ('EE', '爱沙尼亚', 'Estonia');
insert into region (region_code, name, ename) values ('EG', '埃及', 'Egypt');
insert into region (region_code, name, ename) values ('ES', '西班牙', 'Spain');
insert into region (region_code, name, ename) values ('ET', '埃塞俄比亚', 'Ethiopia');
insert into region (region_code, name, ename) values ('FI', '芬兰', 'Finland');
insert into region (region_code, name, ename) values ('FJ', '斐济', 'Fiji');
insert into region (region_code, name, ename) values ('FR', '法国', 'France');
insert into region (region_code, name, ename) values ('GA', '加蓬', 'Gabon');
insert into region (region_code, name, ename) values ('GB', '英国', 'United Kiongdom');
insert into region (region_code, name, ename) values ('GD', '格林纳达', 'Grenada');
insert into region (region_code, name, ename) values ('GE', '格鲁吉亚', 'Georgia');
insert into region (region_code, name, ename) values ('GF', '法属圭亚那', 'French Guiana');
insert into region (region_code, name, ename) values ('GH', '加纳', 'Ghana');
insert into region (region_code, name, ename) values ('GI', '直布罗陀', 'Gibraltar');
insert into region (region_code, name, ename) values ('GM', '冈比亚', 'Gambia');
insert into region (region_code, name, ename) values ('GN', '几内亚', 'Guinea');
insert into region (region_code, name, ename) values ('GR', '希腊', 'Greece');
insert into region (region_code, name, ename) values ('GT', '危地马拉', 'Guatemala');
insert into region (region_code, name, ename) values ('GU', '关岛', 'Guam');
insert into region (region_code, name, ename) values ('GY', '圭亚那', 'Guyana');
insert into region (region_code, name, ename) values ('HK', '香港特别行政区', 'Hongkong');
insert into region (region_code, name, ename) values ('HN', '洪都拉斯', 'Honduras');
insert into region (region_code, name, ename) values ('HT', '海地', 'Haiti');
insert into region (region_code, name, ename) values ('HU', '匈牙利', 'Hungary');
insert into region (region_code, name, ename) values ('ID', '印度尼西亚', 'Indonesia');
insert into region (region_code, name, ename) values ('IE', '爱尔兰', 'Ireland');
insert into region (region_code, name, ename) values ('IL', '以色列', 'Israel');
insert into region (region_code, name, ename) values ('IN', '印度', 'India');
insert into region (region_code, name, ename) values ('IQ', '伊拉克', 'Iraq');
insert into region (region_code, name, ename) values ('IR', '伊朗', 'Iran');
insert into region (region_code, name, ename) values ('IS', '冰岛', 'Iceland');
insert into region (region_code, name, ename) values ('IT', '意大利', 'Italy');
insert into region (region_code, name, ename) values ('JM', '牙买加', 'Jamaica');
insert into region (region_code, name, ename) values ('JO', '约旦', 'Jordan');
insert into region (region_code, name, ename) values ('JP', '日本', 'Japan');
insert into region (region_code, name, ename) values ('KE', '肯尼亚', 'Kenya');
insert into region (region_code, name, ename) values ('KG', '吉尔吉斯坦', 'Kyrgyzstan');
insert into region (region_code, name, ename) values ('KH', '柬埔寨', 'Kampuchea (Cambodia )');
insert into region (region_code, name, ename) values ('KP', '朝鲜', 'North Korea');
insert into region (region_code, name, ename) values ('KR', '韩国', 'Korea');
insert into region (region_code, name, ename) values ('KT', '科特迪瓦共和国', 'Republic of Ivory Coast');
insert into region (region_code, name, ename) values ('KW', '科威特', 'Kuwait');
insert into region (region_code, name, ename) values ('KZ', '哈萨克斯坦', 'Kazakstan');
insert into region (region_code, name, ename) values ('LA', '老挝', 'Laos');
insert into region (region_code, name, ename) values ('LB', '黎巴嫩', 'Lebanon');
insert into region (region_code, name, ename) values ('LC', '圣卢西亚', 'St.Lucia');
insert into region (region_code, name, ename) values ('LI', '列支敦士登', 'Liechtenstein');
insert into region (region_code, name, ename) values ('LK', '斯里兰卡', 'Sri Lanka');
insert into region (region_code, name, ename) values ('LR', '利比里亚', 'Liberia');
insert into region (region_code, name, ename) values ('LS', '莱索托', 'Lesotho');
insert into region (region_code, name, ename) values ('LT', '立陶宛', 'Lithuania');
insert into region (region_code, name, ename) values ('LU', '卢森堡', 'Luxembourg');
insert into region (region_code, name, ename) values ('LV', '拉脱维亚', 'Latvia');
insert into region (region_code, name, ename) values ('LY', '利比亚', 'Libya');
insert into region (region_code, name, ename) values ('MA', '摩洛哥', 'Morocco');
insert into region (region_code, name, ename) values ('MC', '摩纳哥', 'Monaco');
insert into region (region_code, name, ename) values ('MD', '摩尔多瓦', 'Moldova, Republic of');
insert into region (region_code, name, ename) values ('MG', '马达加斯加', 'Madagascar');
insert into region (region_code, name, ename) values ('ML', '马里', 'Mali');
insert into region (region_code, name, ename) values ('MM', '缅甸', 'Burma');
insert into region (region_code, name, ename) values ('MN', '蒙古', 'Mongolia');
insert into region (region_code, name, ename) values ('MO', '澳门', 'Macao');
insert into region (region_code, name, ename) values ('MS', '蒙特塞拉特岛', 'Montserrat Is');
insert into region (region_code, name, ename) values ('MT', '马耳他', 'Malta');
insert into region (region_code, name, ename) values ('MU', '毛里求斯', 'Mauritius');
insert into region (region_code, name, ename) values ('MV', '马尔代夫', 'Maldives');
insert into region (region_code, name, ename) values ('MW', '马拉维', 'Malawi');
insert into region (region_code, name, ename) values ('MX', '墨西哥', 'Mexico');
insert into region (region_code, name, ename) values ('MY', '马来西亚', 'Malaysia');
insert into region (region_code, name, ename) values ('MZ', '莫桑比克', 'Mozambique');
insert into region (region_code, name, ename) values ('NA', '纳米比亚', 'Namibia');
insert into region (region_code, name, ename) values ('NE', '尼日尔', 'Niger');
insert into region (region_code, name, ename) values ('NG', '尼日利亚', 'Nigeria');
insert into region (region_code, name, ename) values ('NI', '尼加拉瓜', 'Nicaragua');
insert into region (region_code, name, ename) values ('NL', '荷兰', 'Netherlands');
insert into region (region_code, name, ename) values ('NO', '挪威', 'Norway');
insert into region (region_code, name, ename) values ('NP', '尼泊尔', 'Nepal');
insert into region (region_code, name, ename) values ('NR', '瑙鲁', 'Nauru');
insert into region (region_code, name, ename) values ('NZ', '新西兰', 'New Zealand');
insert into region (region_code, name, ename) values ('OM', '阿曼', 'Oman');
insert into region (region_code, name, ename) values ('PA', '巴拿马', 'Panama');
insert into region (region_code, name, ename) values ('PE', '秘鲁', 'Peru');
insert into region (region_code, name, ename) values ('PF', '法属玻利尼西亚', 'French Polynesia');
insert into region (region_code, name, ename) values ('PG', '巴布亚新几内亚', 'Papua New Cuinea');
insert into region (region_code, name, ename) values ('PH', '菲律宾', 'Philippines');
insert into region (region_code, name, ename) values ('PK', '巴基斯坦', 'Pakistan');
insert into region (region_code, name, ename) values ('PL', '波兰', 'Poland');
insert into region (region_code, name, ename) values ('PR', '波多黎各', 'Puerto Rico');
insert into region (region_code, name, ename) values ('PT', '葡萄牙', 'Portugal');
insert into region (region_code, name, ename) values ('PY', '巴拉圭', 'Paraguay');
insert into region (region_code, name, ename) values ('QA', '卡塔尔', 'Qatar');
insert into region (region_code, name, ename) values ('RO', '罗马尼亚', 'Romania');
insert into region (region_code, name, ename) values ('RU', '俄罗斯', 'Russia');
insert into region (region_code, name, ename) values ('SA', '沙特阿拉伯', 'Saudi Arabia');
insert into region (region_code, name, ename) values ('SB', '所罗门群岛', 'Solomon Is');
insert into region (region_code, name, ename) values ('SC', '塞舌尔', 'Seychelles');
insert into region (region_code, name, ename) values ('SD', '苏丹', 'Sudan');
insert into region (region_code, name, ename) values ('SE', '瑞典', 'Sweden');
insert into region (region_code, name, ename) values ('SG', '新加坡', 'Singapore');
insert into region (region_code, name, ename) values ('SI', '斯洛文尼亚', 'Slovenia');
insert into region (region_code, name, ename) values ('SK', '斯洛伐克', 'Slovakia');
insert into region (region_code, name, ename) values ('SL', '塞拉利昂', 'Sierra Leone');
insert into region (region_code, name, ename) values ('SM', '圣马力诺', 'San Marino');
insert into region (region_code, name, ename) values ('SN', '塞内加尔', 'Senegal');
insert into region (region_code, name, ename) values ('SO', '索马里', 'Somali');
insert into region (region_code, name, ename) values ('SR', '苏里南', 'Suriname');
insert into region (region_code, name, ename) values ('ST', '圣多美和普林西比', 'Sao Tome and Principe');
insert into region (region_code, name, ename) values ('SV', '萨尔瓦多', 'EI Salvador');
insert into region (region_code, name, ename) values ('SY', '叙利亚', 'Syria');
insert into region (region_code, name, ename) values ('SZ', '斯威士兰', 'Swaziland');
insert into region (region_code, name, ename) values ('TD', '乍得', 'Chad');
insert into region (region_code, name, ename) values ('TG', '多哥', 'Togo');
insert into region (region_code, name, ename) values ('TH', '泰国', 'Thailand');
insert into region (region_code, name, ename) values ('TJ', '塔吉克斯坦', 'Tajikstan');
insert into region (region_code, name, ename) values ('TM', '土库曼斯坦', 'Turkmenistan');
insert into region (region_code, name, ename) values ('TN', '突尼斯', 'Tunisia');
insert into region (region_code, name, ename) values ('TO', '汤加', 'Tonga');
insert into region (region_code, name, ename) values ('TR', '土耳其', 'Turkey');
insert into region (region_code, name, ename) values ('TT', '特立尼达和多巴哥', 'Trinidad and Tobago');
insert into region (region_code, name, ename) values ('TW', '台湾省', 'Taiwan');
insert into region (region_code, name, ename) values ('TZ', '坦桑尼亚', 'Tanzania');
insert into region (region_code, name, ename) values ('UA', '乌克兰', 'Ukraine');
insert into region (region_code, name, ename) values ('UG', '乌干达', 'Uganda');
insert into region (region_code, name, ename) values ('US', '美国', 'United States of America');
insert into region (region_code, name, ename) values ('UY', '乌拉圭', 'Uruguay');
insert into region (region_code, name, ename) values ('UZ', '乌兹别克斯坦', 'Uzbekistan');
insert into region (region_code, name, ename) values ('VC', '圣文森特岛', 'Saint Vincent');
insert into region (region_code, name, ename) values ('VE', '委内瑞拉', 'Venezuela');
insert into region (region_code, name, ename) values ('VN', '越南', 'Vietnam');
insert into region (region_code, name, ename) values ('YE', '也门', 'Yemen');
insert into region (region_code, name, ename) values ('YU', '南斯拉夫', 'Yugoslavia');
insert into region (region_code, name, ename) values ('ZA', '南非', 'South Africa');
insert into region (region_code, name, ename) values ('ZM', '赞比亚', 'Zambia');
insert into region (region_code, name, ename) values ('ZR', '扎伊尔', 'Zaire');
insert into region (region_code, name, ename) values ('ZW', '津巴布韦', 'Zimbabwe');

update region set insert_time = now(), update_time = now();


CREATE TABLE finc_ods_inv_index (
	index_code varchar(20) COMMENT '代码',
	type varchar(20) COMMENT '类型',
	name varchar(120) COMMENT '名称',
	curr_id varchar(20) COMMENT 'curr_id',
	first_trade_date varchar(20) COMMENT '首个交易日',
	insertTime datetime DEFAULT NULL,
	updateTime datetime DEFAULT NULL,
 	PRIMARY KEY (index_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE finc_ods_inv_index_data_his (
	index_code varchar(20) COMMENT '代码',
	trade_date varchar(20) COMMENT '日期',
	type varchar(20) COMMENT '类型',
	name varchar(120) COMMENT '名称',
	curr_id varchar(20) COMMENT 'curr_id',
	closing_price varchar(20) COMMENT '收盘价',
	highest_price varchar(20) COMMENT '最高价',
	lowest_price varchar(20) COMMENT '最低价',
	opening_price varchar(20) COMMENT '开盘价',
	change_amount varchar(20) COMMENT '涨跌额',
	change_rate varchar(20) COMMENT '涨跌幅',
	total_volume varchar(30) COMMENT '成交量',
	insertTime datetime DEFAULT NULL,
	updateTime datetime DEFAULT NULL,
 	PRIMARY KEY (index_code, trade_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE finc_select (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	name varchar(255) COMMENT '名称',
	code varchar(20) COMMENT '代码',
	type varchar(20) COMMENT '类型：fund,index',
	latest_trade_date varchar(40) COMMENT '数据最新交易日',
	date_sync_time varchar(40) COMMENT '数据同步日期',
	valid_status varchar(2) COMMENT '有效状态：0,1',
	insert_time datetime DEFAULT NULL,
	update_time datetime DEFAULT NULL,
 	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
