package com.yan.finance.fund.spider.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.yan.finance.fund.schema.FincEMFundValuation;
import com.yan.finance.fund.schema.FincFundInfoItem;

@Service
public class FundSpiderEastMoneyService{
	
	private static final Logger logger = LoggerFactory.getLogger(FundSpiderEastMoneyService.class);
	
	public FincFundInfoItem crawlFound(String webRootUrl, String fundCode){
		FincFundInfoItem fincFundInfoItem = new FincFundInfoItem();
		fincFundInfoItem.setFundCode(fundCode);
		
		String fundUrl = webRootUrl + "/" + fundCode + ".html";

		// 请求这个url，获取返回的html内容
		String content = this.requestUrlByGetMethod(fundUrl);
		
		// 使用jsoup解析html内容
		Document document=Jsoup.parse(content);
		Element element = document.select("div.fundInfoItem").first();
		
		Element dataOfFundElement = element.select("div.dataOfFund").first();
		Element dataItem01Element = dataOfFundElement.select("dl.dataItem01").first();
		String valuationTimeStr = dataItem01Element.select("span#gz_gztime").first().text();
		Date valuationTime = this.findDateTimeByRegex(valuationTimeStr);
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		fincFundInfoItem.setTradeDate(format1.format(valuationTime));
		fincFundInfoItem.setValuationTime(format2.format(valuationTime));
		
		//logger.debug(valuationTimeStr);
		//System.out.println(valuationTime);

		String valuation = dataItem01Element.select("span#gz_gsz").first().text();
		//System.out.println(valuation);
		fincFundInfoItem.setValuation(new BigDecimal(valuation));
		
		List<Element> ddElements = dataItem01Element.select("dd");
		
		if(ddElements != null && ddElements.size() >= 3) {
			// 近1月涨跌
			Element changeRateMonthElement = ddElements.get(1);
			
			
			String changeRateMonthStr = changeRateMonthElement.select("span").get(1).text();
			changeRateMonthStr = this.findNumByRegex(changeRateMonthStr);
			BigDecimal changeRateMonth = null;
			if(!StringUtils.isEmpty(changeRateMonthStr)) {
				changeRateMonth = new BigDecimal(changeRateMonthStr).divide(new BigDecimal("100"));
			}
			fincFundInfoItem.setChangeRateMonth(changeRateMonth);
			//System.out.println(changeRateMonth);

			// 近1年涨跌
			Element changeRateYearElement = ddElements.get(2);
			String changeRateYearStr = changeRateYearElement.select("span").get(1).text();
			changeRateYearStr = this.findNumByRegex(changeRateYearStr);
			BigDecimal changeRateYear = null;
			if(!StringUtils.isEmpty(changeRateYearStr)) {
				changeRateYear = new BigDecimal(changeRateYearStr).divide(new BigDecimal("100"));
			}
			fincFundInfoItem.setChangeRateYear(changeRateYear);
			//System.out.println(changeRateYear);
			
		}
		
		return fincFundInfoItem;
	}
	
	public FincEMFundValuation crawlFoundValuationFromJson(String webRootUrl, String fundCode){

		Long rt = System.currentTimeMillis();
		//http://fundgz.1234567.com.cn/js/006087.js?rt=1583289676821
		String fundUrl = "http://fundgz.1234567.com.cn" + "/js/" + fundCode + ".js?rt=" + rt;

		// 请求这个url，获取返回的html内容
		String content = this.requestUrlByGetMethod(fundUrl);
		String jsonString = this.findJsonByRegex(content);
		logger.info(jsonString);
		FincEMFundValuation fincEMFundValuation = this.parseFundInfoItem(jsonString);
		
		return fincEMFundValuation;
	}
	
	public String requestUrlByGetMethod(String url) {
		Map<String, String> requestHeaders = new HashMap<>();
		
		
		String html = this.requestUrlByGetMethod(url, requestHeaders, "UTF-8");
		return html;
	}
	
	
	/**
	 * 通过get的方式请求一个url
	 * @param url
	 * @param requestHeaders
	 * @param charset
	 * @return
	 */
	public String requestUrlByGetMethod(String url, Map<String, String> requestHeaders, String charset) {
		CloseableHttpClient httpclient = null;
    	// 不使用代理服务器
    	httpclient = HttpClients.createDefault();
        
		HttpGet httpget = new HttpGet(url);
		
		if(requestHeaders != null && requestHeaders.size() > 0) {
			for(Entry<String, String> entry:requestHeaders.entrySet()) {
				httpget.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
		String content = null;
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
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
	
	public Date findDateTimeByRegex(String line) {
		Date date = null;
		
		//(20-02-27 15:00)
		Pattern r = Pattern.compile("(\\d+\\-\\d+\\-\\d+ \\d+:\\d+)");

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.find()) {
			//System.out.println(m.group(0));
			//System.out.println(m.group(1));
			
			String dateTimeStr = m.group(0);
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
			
			try {
				date = sdf.parse(dateTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//System.out.println(date);
		}
		return date;
	}

	public String findNumByRegex(String line) {
		String numStr = null;
		Pattern r = Pattern.compile("(\\d+\\.\\d+)");

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
	
	public String findJsonByRegex(String line) {
		String numStr = null;
		Pattern r = Pattern.compile("(\\{.*?\\})");

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
	
	public FincEMFundValuation parseFundInfoItem(String json) {
		FincEMFundValuation fundValuation = new FincEMFundValuation();
		
		//{"fundcode":"006087","name":"华泰柏瑞中证500ETF联接C","jzrq":"2020-03-05","dwjz":"0.6428","gsz":"0.6389","gszzl":"-0.61","gztime":"2020-03-06 15:00"}
		JSONObject jsonObject = JSONObject.parseObject(json);
		String fundcode = jsonObject.getString("fundcode");
		fundValuation.setFundcode(fundcode);
		
		String name = jsonObject.getString("name");
		fundValuation.setName(name);;
		
		//净值日期
		String jzrq = jsonObject.getString("jzrq");
		fundValuation.setJzrq(jzrq);
		
		// 单位净值
		BigDecimal dwjz = jsonObject.getBigDecimal("dwjz");
		fundValuation.setDwjz(dwjz);
		
		// 估算值
		BigDecimal gsz = jsonObject.getBigDecimal("gsz");
		fundValuation.setGsz(gsz);
		
		// 估算日增长率
		BigDecimal gszzl = jsonObject.getBigDecimal("gszzl");
		fundValuation.setGszzl(gszzl);
		
		// 估算时间
		String gztime = jsonObject.getString("gztime");
		fundValuation.setGztime(gztime);
		
		return fundValuation;
	}
}
