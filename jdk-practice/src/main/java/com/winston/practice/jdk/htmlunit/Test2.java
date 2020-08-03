package com.winston.practice.jdk.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test2 {

    public static void main(String[] args) throws Exception {

        WebRequest requestSettings = new WebRequest(new URL("http://192.168.0.119:8080/ACPSample_WuTiaoZhuan_Token/pages/token/token_openCard_front.jsp"),
          HttpMethod.POST);

        WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);


        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);// 当HTTP的状态非200时是否抛出异常,
        webClient.getOptions().setThrowExceptionOnScriptError(false); // 此行必须要加
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(300000);
        // 忽略ssl
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.setCookieManager(new CookieManager());
        ProxyConfig cof = webClient.getOptions().getProxyConfig();
        cof.setProxyHost("127.0.0.1");
        cof.setProxyPort(8888);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getCookieManager().addCookie(new Cookie("cashier.test.95516.com", "sensorsdata2015jssdkcross",
          "%7B%22distinct_id%22%3A%22168799167009f7-01344e952b8d9-51a2f73-2073600-168799167017b3%22%2C%22%24device_id%22%3A%22168799167009f7-01344e952b8d9-51a2f73-2073600-168799167017b3%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%7D%7D",
          "/", 100000, true));
        // 获取首页

        webClient.addRequestHeader("Accept-Language", "Accept-Language:zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
        webClient.addRequestHeader("Content-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        webClient.addRequestHeader("accept", "*/*");
        webClient.addRequestHeader("accept-language", "zh-CN");
        webClient.addRequestHeader("Accept-Encoding", "gzip, deflate");


        HtmlPage page = (HtmlPage) webClient.getPage("http://192.168.0.119:8080/ACPSample_WuTiaoZhuan_Token/pages/token/token_openCard_front.jsp");

        HtmlForm form1 = page.getForms().get(0);

        HtmlInput button1 = form1.getInputByValue("跳转到银联页面开通");
        HtmlPage page2 = button1.click();
        webClient.waitForBackgroundJavaScript(30000);// 等待1秒
        HtmlForm form2 = page2.getForms().get(0);
        HtmlTextInput smsCode = (HtmlTextInput) form2.getInputByName("smsCode");
        smsCode.click();
        smsCode.type("123456");
        System.out.println("==22222=====================================================================================================");
        System.out.println(page2.asXml());
        System.out.println("==2222=====================================================================================================");
        System.out.println(page2);


        Document html4 = Jsoup.parse(page2.asXml());
        Elements inputs4 = html4.select("input");
        HashMap<String, String> reqData4 = new HashMap<String, String>();
        String[] sss = {"merId", "credentialTypeEditable", "credentialNumberEditable", "realNameEditable", "mobileDisplayMode", "restrictPayDisplay", "aggrementType", "aggrementDesc", "bankNo", "cardType", "verifyPolicys_credential", "verifyPolicys_mobile", "verifyPolicys_smsCode", "verifyPolicys_cardNo", "verifyPolicys_password", "verifyPolicys_name", "verifyPolicys_expire", "verifyPolicys_cvn2", "foreignMerchant", "SMSBankCode_QID", "machineInfo", "cardNumber", "credentialType", "mockCVN2", "atmPassword", "mockLoginPassword", "cellPhoneNumber", "phoneNumber", "smsCode", "isCheckAgreement", "encryptKey"};
        List<String> ssss = Arrays.asList(sss);
        for (Element input : inputs4) {
            String name = input.attr("name");
            String value = input.attr("value");

            if (ssss.indexOf(name) == -1) {
                continue;
            }

            if ("smsCode".equals(input.attr("name"))) {
                value = "123456";
            }
            if ("isCheckAgreement".equals(name)) {
                value = "on";
            }
            value = value.replace(" ", "");
            reqData4.put(name, value);
            System.out.println(name + "=" + value);
        }
        reqData4.put("encryptKey", "");
        Elements form4 = html4.select("form");
        String url4 = form4.get(0).attr("action");
        url4 = url4.replace("/b2c/cardPay.action", "");
        System.out.println(url4);

        String reault4 = HttpRequestUtils.httpPost("https://cashier.test.95516.com/b2c/authpay/ActivateProcess.action" + url4, reqData4, webClient.getCookieManager().getCookies());
        System.out.println("======reault4=======");
        System.out.println(reault4);
        HtmlPage page5 = webClient.getPage(reault4);
        webClient.waitForBackgroundJavaScript(30000);// 等待1秒
        System.out.println("==3333=====================================================================================================");
        System.out.println(page5.asXml());
        System.out.println("==3333=====================================================================================================");


        //HtmlPage page6 = webClient.getPage("https://cashier.test.95516.com/b2c/activateFrontCallBack.action"+url4);

        String reault6 = HttpRequestUtils.httpPost("https://cashier.test.95516.com/b2c/activateFrontCallBack.action" + url4, reqData4, webClient.getCookieManager().getCookies());

        System.out.println("==66666=====================================================================================================");
        System.out.println(reault6);
        System.out.println("==66666======");

        Document html6 = Jsoup.parse(reault6);
        Elements inputs6 = html6.select("input");
        HashMap<String, String> reqData6 = new HashMap<String, String>();
        for (Element input : inputs6) {
            String name = input.attr("name");
            String value = input.attr("value");
            reqData6.put(name, value);
            System.out.println(name + "=" + value);

        }

        Elements form = html6.select("form");
        String url6 = form.get(0).attr("action");

        String reault7 = HttpRequestUtils.httpPost(url6, reqData6, webClient.getCookieManager().getCookies());
        System.out.println("==66666=====================================================================================================");
        System.out.println(reault7);
        System.out.println("==66666======");
		
		/*
		
		HtmlInput btnCardPay = form2.getInputByName("btnCardPay");
		
		HtmlPage page3 = btnCardPay.click();
		
		webClient.waitForBackgroundJavaScript(100000);// 等待1秒
		
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(page3);
		String localtionUrl = page3.getWebResponse().getResponseHeaderValue("Location");
		System.out.println("++++" + page3.getWebResponse().getStatusCode());
		System.out.println("++++" +localtionUrl);
		//HtmlPage page4 = webClient.getPage(localtionUrl);
		
		// ScriptResult result =
		// page2.executeJavaScript("document.getElementById(\"cardPay\").submit()");
		webClient.waitForBackgroundJavaScript(1000);// 等待1秒
		// Undefined page3 = (Undefined) result.getJavaScriptResult();
		// HtmlPage page04 = (HtmlPage) form03.click();// 跳转
		
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("==3333=====================================================================================================");
		System.out.println(page3.asXml());
		System.out.println("==3333=====================================================================================================");

		System.out.println("Cookies : " + webClient.getCookieManager().getCookies().toString());
		
		webClient.close();*/


    }
}
