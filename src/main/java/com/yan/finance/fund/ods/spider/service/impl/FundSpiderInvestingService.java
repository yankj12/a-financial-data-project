package com.yan.finance.fund.ods.spider.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yan.finance.fund.ods.schema.FincOdsInvIndexDataHis;

@Service
public class FundSpiderInvestingService{
	
	private static final Logger logger = LoggerFactory.getLogger(FundSpiderInvestingService.class);
	
	
	// 是否使用代理
	@Value("${proxy.useProxy}")
	private String useProxy;
	
	@Value("${proxy.ip}")
	private String proxyIp;
	
	@Value("${proxy.port}")
	private int port;
	
	@Value("${proxy.protocol}")
	private String proxyProtocol;
	
	
	public List<FincOdsInvIndexDataHis> crawlHistoricalData(String indexCode, String currId, String name, String startDateStr, String endDateStr){
		logger.debug("startDate={}, endDate={}", new Object[] {startDateStr, endDateStr});
		
		List<FincOdsInvIndexDataHis> fincInvIndexDataHisList = new ArrayList<FincOdsInvIndexDataHis>();
        
		List<String> startEndDateStrs = this.splitDatePeriod(startDateStr, endDateStr);
		
		if(startEndDateStrs != null && startEndDateStrs.size() > 0) {
			for(String startEndDateStr:startEndDateStrs) {
				String[] ary = startEndDateStr.split(",");
				String sDateStr = ary[0];
				String eDateStr = ary[1];
				
				logger.debug("indexCode={}, currId={}, name={}, startDate={}, endDate={}", new Object[] {indexCode, currId, name, sDateStr, eDateStr});
				List<FincOdsInvIndexDataHis> list = this.crawlHistoricalDataByShortDatePeriod(indexCode, currId, name, sDateStr, eDateStr);
				fincInvIndexDataHisList.addAll(list);
			}
		}
		
		return fincInvIndexDataHisList;
	}
	
	public List<FincOdsInvIndexDataHis> crawlHistoricalDataByShortDatePeriod(String indexCode, String currId, String name, String startDate, String endDate){
		
		List<FincOdsInvIndexDataHis> fincInvIndexDataHisList = new ArrayList<FincOdsInvIndexDataHis>();
		
		String url = "https://cn.investing.com/instruments/HistoricalDataAjax";

		/**
			curr_id:255
			smlID:1159492
			header:F历史数据
			st_date:2018/01/03
			end_date:2019/01/03
			interval_sec:Daily
			sort_col:date
			sort_ord:DESC
			action:historical_data
			根据参数我们可以看到：
			开始日期、结束日期、表名、数据类型（日数据Daily）、排序、内容、方式都有了，就是CURR_ID和SMLID不知道是什么，
			修改测试，CURR_ID和SMLID删除任意一项，返回空白，修改CURR_ID，返回不同的数字，测试了几个，发现这个参数为股票名成，以网站自定义顺序排列的；
			修改SMLID修改，任意7为数字，数据不变，这个应该是匹配随机数的，随意即可
		*/
		String smlID = this.random7Num();
		String intervalSec = "Daily";
		String sortCol = "date";
		String sortOrd = "DESC";
		String action = "historical_data";
		
        try {
        	// curr_id=20&smlID=2030165&header=%E7%BA%B3%E6%96%AF%E8%BE%BE%E5%85%8B100%E6%8C%87%E6%95%B0%E5%8E%86%E5%8F%B2%E6%95%B0%E6%8D%AE&st_date=2000%2F02%2F14&end_date=2020%2F03%2F14&interval_sec=Daily&sort_col=date&sort_ord=DESC&action=historical_data
        	StringBuilder bodyBuilder = new StringBuilder();
        	bodyBuilder.append("curr_id=").append(currId)
        	.append("&smlID=").append(smlID)
        	.append("&header=").append(URLEncoder.encode(name, "UTF-8"))
        	.append("&st_date=").append(URLEncoder.encode(startDate, "UTF-8"))
        	.append("&end_date=").append(URLEncoder.encode(endDate, "UTF-8"))
        	.append("&interval_sec=").append(intervalSec)
        	.append("&sort_col=").append(sortCol)
        	.append("&sort_ord").append(sortOrd)
        	.append("&action").append(action);
        	
        	String body = bodyBuilder.toString();
        	
			// 请求这个url，获取返回的html内容
			String content = this.requestUrlByPostMethod(url, body);
			//System.out.println(content);
			fincInvIndexDataHisList = this.parseResponse(content);
			
			if(fincInvIndexDataHisList != null && fincInvIndexDataHisList.size() > 0) {
				for(FincOdsInvIndexDataHis invIndexDataHis:fincInvIndexDataHisList) {
					
					invIndexDataHis.setIndexCode(indexCode);
					invIndexDataHis.setCurrId(currId);
					invIndexDataHis.setType("index");
					invIndexDataHis.setName(name);
					
					invIndexDataHis.setInsertTime(new Date());
					invIndexDataHis.setUpdateTime(new Date());
				}
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return fincInvIndexDataHisList;
	}
	
	public List<FincOdsInvIndexDataHis> parseResponse(String text) throws DocumentException {
		List<FincOdsInvIndexDataHis> odsIndexDataVos = new ArrayList<FincOdsInvIndexDataHis>();

		text = "<html><body>" + text + "</body></html>";
		Document document = Jsoup.parse(text);
		Element tableElement = document.select("table#curr_table").first();

		Element tbodyElement = tableElement.select("tbody").first();
		List<Element> trEles = tableElement.select("tr");

		for (Element trEle : trEles) {
			List<Element> tdEles = trEle.select("td");
			if(tdEles != null && tdEles.size() > 0) {
				FincOdsInvIndexDataHis indexDataVo = new FincOdsInvIndexDataHis();
				// 日期、收盘价、开盘价、高、低、交易量、涨跌幅
				int colIndex = 0;
				for (Element tdEle : tdEles) {
					//System.out.println(tdEle.ownText());
					try {
						if (colIndex == 0) {
							// 日期
							String tradeDateChineseStr = tdEle.ownText();
							//String tradeDate = this.findDateStrByRegex(tradeDateChineseStr);
							indexDataVo.setTradeDate(tradeDateChineseStr);
						} else if (colIndex == 1) {
							// 收盘价
							String closingPriceStr = tdEle.ownText();
							indexDataVo.setClosingPrice(closingPriceStr);
						} else if (colIndex == 2) {
							// 开盘价
							String openingPriceStr = tdEle.ownText();
							indexDataVo.setOpeningPrice(openingPriceStr);
						} else if (colIndex == 3) {
							// 高
							String highestPriceStr = tdEle.ownText();
							indexDataVo.setHighestPrice(highestPriceStr);
						} else if (colIndex == 4) {
							// 低
							String lowestPriceStr = tdEle.ownText();
							indexDataVo.setLowestPrice(lowestPriceStr);
						} else if (colIndex == 5) {
							// 交易量
							String totalVolume = tdEle.ownText();
							indexDataVo.setTotalVolume(totalVolume);
						} else if (colIndex == 6) {
							// 涨跌幅
							String changeRateStr = tdEle.ownText();
//						if(changeRateStr.startsWith("-")) {
//							changeRateStr = changeRateStr.replace("-", "");
//							isNegative = true;
//						}
							indexDataVo.setChangeRate(changeRateStr);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} 
					
					colIndex++;
				}
				odsIndexDataVos.add(indexDataVo);
			}
		}
		
		return odsIndexDataVos;
	}
	
	public String requestUrlByPostMethod(String url, String body) {
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("Host", " cn.investing.com");
		requestHeaders.put("User-Agent", " Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.27 Safari/537.36 OPR/62.0.3331.10 (Edition beta)");
		requestHeaders.put("Accept", " text/plain, */*; q=0.01");
		requestHeaders.put("Accept-Language", " zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		requestHeaders.put("Accept-Encoding", " gzip, deflate, br");
		requestHeaders.put("Content-Type", " application/x-www-form-urlencoded");
		requestHeaders.put("X-Requested-With", " XMLHttpRequest");
		
		String html = this.requestUrlByPostMethod(url, requestHeaders, body, "UTF-8");
		return html;
	}
	
	
	/**
	 * 通过get的方式请求一个url
	 * @param url
	 * @param requestHeaders
	 * @param charset
	 * @return
	 */
	public String requestUrlByPostMethod(String url, Map<String, String> requestHeaders, String body, String charset) {
		CloseableHttpClient httpclient = null;

		//实例化CloseableHttpClient对象
        if("on".equalsIgnoreCase(useProxy)){
        	// 使用代理服务器
        	
        	//设置代理IP、端口、协议（请分别替换）
        	HttpHost proxy = new HttpHost(proxyIp, port, proxyProtocol);
        	
        	//把代理设置到请求配置
        	RequestConfig defaultRequestConfig = RequestConfig.custom().setProxy(proxy).build();
        	httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        }else{
        	// 不使用代理服务器
        	httpclient = HttpClients.createDefault();
        }
		
		HttpPost httpPost = new HttpPost(url);

		StringEntity requestEntity = new StringEntity(body, charset);
		requestEntity.setContentEncoding(charset);
		requestEntity.setContentType("application/x-www-form-urlencoded");
        httpPost.setEntity(requestEntity);
		
		if(requestHeaders != null && requestHeaders.size() > 0) {
			for(Entry<String, String> entry:requestHeaders.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
		String content = null;
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				content = EntityUtils.toString(entity, charset);
                
		    }
			
			// 请求最小间隔3s
//			Thread.sleep(3 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    try {
				if(response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
	
	public String random7Num(){
		StringBuilder result = new StringBuilder();
		Random random = new Random();
		for(int i=0;i<7;i++) {
			result.append(random.nextInt(10));
		}
		return result.toString();
	}
	
	public String findDateStrByRegex(String line) {
		
		//2020年3月4日
		Pattern r = Pattern.compile("\\d+");

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		int index = 0;
		StringBuilder dateStrBuilder = new StringBuilder();
		while (m.find()) {
			if(index > 0) {
				dateStrBuilder.append("-");
			}
			String numStr = m.group();
			
			if(index == 0 && numStr.length() == 4) {
				dateStrBuilder.append(numStr);
			}else if(index > 0 && numStr.length() == 1) {
				dateStrBuilder.append("0").append(numStr);
			}else if(index > 0 && numStr.length() == 2) {
				dateStrBuilder.append(numStr);
			}else {
				throw new RuntimeException("日期格式不正确");
			}
			index++;
		}
		//System.out.println(dateStrBuilder.toString());
		return dateStrBuilder.toString();
	}

	public String findNumByRegex(String line) {
		String numStr = null;
		Pattern r = Pattern.compile("(\\-{0,1}\\d+\\.\\d+)");

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.find()) {
			numStr = m.group(0);
			//System.out.println(m.group(0));
		}else {
			numStr = "";
		}
		return numStr;
	}
	
	public boolean isDecimal(String line) {
		Pattern r = Pattern.compile("(\\-{0,1}\\d+\\.\\d+)");

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		
		return m.matches();
	}
	
	public List<String> splitDatePeriod(String startDateStr, String endDateStr){
		List<String> startEndDateStrs = new ArrayList<String>();
		try {
//			String startDateStr = "1990/01/01";
//			String endDateStr = "2020/01/31";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			Date startDate = sdf.parse(startDateStr);
			Date endDate = sdf.parse(endDateStr);
			
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(startDate);

			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endDate);

			while(startCalendar.before(endCalendar)) {
				
				String sDate = sdf.format(startCalendar.getTime());
				
				startCalendar.add(Calendar.DATE, 29);
				
				String eDate = sdf.format(startCalendar.getTime());
				if(startCalendar.before(endCalendar) || startCalendar.equals(endCalendar)) {
					eDate = sdf.format(startCalendar.getTime());
				}else {
					eDate = sdf.format(endCalendar.getTime());
				}
				
				String startEndDateStr = sDate + "," + eDate;
				//System.out.println(startEndDateStr);
				
				startEndDateStrs.add(startEndDateStr);
				
				//起始日期加1天，避免日期段重叠
				startCalendar.add(Calendar.DATE, 1);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return startEndDateStrs;
	}
	
}
