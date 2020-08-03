package com.winston.practice.jdk.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.BrowserVersion.BrowserVersionBuilder;
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

public class Test3 {

    public static void main(String[] args) throws Exception {

        String url = "https://gateway.test.95516.com/gateway/api/frontTransReq.do?bizType=000902&tokenPayData=%7BtrId%3D62000000001%26tokenType%3D01%7D&txnSubType=00&backUrl=https%3A%2F%2F58.246.68.125%2Fcut%2Fnotice%2F210015010000210501&orderId=54289520190128185454100000335&signature=xHgWkiqEW0IsLirzzTwuaXJ%2FzXmDFVUez6s2NQld%2Fbhg3EEtXh91ztiLMZcitiP8BR5yDFNKceEdQdBEQKuYyQLwKOSRdRnOCMW%2BttSdpjTnfngbihTzWuBTu8UrUEpgkhRwUyUSS17hpdvKRJ3wGhazThFDDj38%2B%2F2EIEHSY6wcpSvpiq9MCjJi0XYuieVQ1RboriASqZ880i%2B9iFIuNbKhvhXOopC3SKIBKVJIIxp%2BuGZ%2BtV3Fsm6WizSbMal6rGPnyCB%2F7FVh39iC%2BeRWsei2lcj133EH%2BS25b2dJqc0n5Ma5VMN%2BBqkjrVAdJBy3JEdYW0JaN%2BoeldMFK2lZng%3D%3D&accNo=VhWfvL%2Br08Jp6VJa3j9m%2B3%2Bv%2BIVKDVPV8VOHO433XBe9aq8Bhj7GXraWvA9gAGMjY3oMUWu3ptHuP7teH%2FM0qaUReBwnwJvHwPabJqzVWy0OqcgZkOmcqr3%2BJdQ9wch2RnfwwiECI8b5OUV6rt3RZ8mT%2B9e94GL1SMMKIrOgw2ADPQ1WIS4oDbpUBjzAXbl809dTQaNzk4j%2F89AKmBFt3unIRnpB%2BIRv1d6NiZf%2FJg2%2B8dZxNLFIHHt98B0YEdezhtbZJSRKBUEoOl5k7KRXbTQXtO6kAWIuaaZ3n%2FQ4ZtkZxmk77ViFSHOpbuFagDkZKkeUXV46gy1BEAdZf8KEDQ%3D%3D&customerInfo=e2NlcnRpZklkPTUxMTQyMzE5ODgxMjIxMDAxNiZjZXJ0aWZUcD0wMSZjdXN0b21lck5tPeW8oOS4iSZlbmNyeXB0ZWRJbmZvPWZTUERNa0pFT0c3dGd3Nk5qc1R2U1VpOWQyemRkczY5RWoyRzg2ZVlBQnlpM3BqSit3Q1FhczdnUnNyQVJ2TFBmQ0M5Qi8yWVQrSGNwTVNVclhkVU94amhOQnRhMG9PMzh6NmREc05OVW5CVnl0LytqS3JtaGRjbndGeU1kaC83TWNoUGRhVVJqRVhvOWhlUVNZSHlWNURmbmhSQjNFTlVmcE5zdXVpN1RBR3Y4dE1tOGZUOEZsTXNFQ2F1eU5SY2lFbEM1RkF2QjZITFNjNVpCLy9rbmJYVFVEZCtmZ1hwN2lzUkVTOHJLdnJoNGx6dGFuY2UvL2MwYmZxVDZHSXRKOTRNOW0zUUhKVWhTYTZLcXdxMWdhZno4WllOcmREdk9HS2t3bWs0LzB6WmdNOWJmSWhNOXowQW5kOUhyWGd4M0lDUXJDdEE4WnBPSGloTy9VY2FNZz09fQ%3D%3D&txnType=79&channelType=07&frontUrl=http%3A%2F%2F182.150.31.132%3A30016%2Fbene-access%2Fuser%2Ftest%2FpageWeb.html&certId=68759663125&encoding=UTF-8&version=5.1.0&accessType=0&encryptCertId=69026275926&txnTime=20190128185454&merId=777290058110097&accType=02&currencyCode=156&signMethod=01&";
        WebRequest webRequest = new WebRequest(new URL(url.toString()), HttpMethod.POST);

        BrowserVersionBuilder browserVersionBuilder = new BrowserVersionBuilder(BrowserVersion.INTERNET_EXPLORER);
        browserVersionBuilder.setSystemLanguage("zh-CN");
        browserVersionBuilder.setBrowserLanguage("zh-CN");
        browserVersionBuilder.setUserLanguage("zh-CN");

        WebClient webClient = new WebClient(browserVersionBuilder.build());

        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);// 当HTTP的状态非200时是否抛出异常,
        webClient.getOptions().setThrowExceptionOnScriptError(false); // 此行必须要加
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(300000);
        webClient.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        // 忽略ssl
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.setCookieManager(new CookieManager());
        ProxyConfig cof = webClient.getOptions().getProxyConfig();
        cof.setProxyHost("127.0.0.1");
        cof.setProxyPort(8888);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        HtmlPage page2 = (HtmlPage) webClient.getPage(webRequest);

        System.out.println(page2.asXml());

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

        btnCardPay.click();
        int i = 0;
        HtmlPage page4 = null;
        boolean isSuc = false;
        while (i < 20) {
            i++;
            Thread.sleep(500);
            page4 = (HtmlPage) page2.getWebClient().getCurrentWindow().getEnclosedPage();
            if (page4.getUrl().getPath().toString().indexOf("Result.action") > -1
              || page4.getUrl().getPath().toString().indexOf("activateFrontCallBack.action") > -1
              || page4.getUrl().getPath().toString().indexOf("frontRcvResponse") > -1) {
                isSuc = true;
                break;
            }
        }

        //webClient.waitForBackgroundJavaScript(100000);// 等待1秒
        System.out.println("==3333=====================================================================================================");
        System.out.println(page4.asXml());
        System.out.println("==3333=====================================================================================================");


        webClient.close();


    }
}
