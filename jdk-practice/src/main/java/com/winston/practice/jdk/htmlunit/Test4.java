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

import java.net.URL;

public class Test4 {

    public static void main(String[] args) throws Exception {

        WebRequest requestSettings = new WebRequest(new URL("https://www.jianshu.com/sign_in"), HttpMethod.POST);

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

        HtmlInput btnCardPay = form2.getInputByName("btnCardPay");

        HtmlPage page3 = btnCardPay.click();

        webClient.waitForBackgroundJavaScript(100000);// 等待1秒

        HtmlPage page4 = (HtmlPage) page2.getWebClient().getCurrentWindow().getEnclosedPage();
        System.out.println("==3333=====================================================================================================");
        System.out.println(page4.asXml());
        System.out.println("==3333=====================================================================================================");


        webClient.close();


    }
}
