package com.winston.practice.jdk.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class htmlUnitSample {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String sUrl = "http://localhost:8080/ACPSample_WuTiaoZhuan_Token/pages/token/token_openCard_front.jsp";// 网址
        // webclient设置
        WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER); // 创建一个webclient
        webClient.getOptions().setJavaScriptEnabled(true); // 启动JS
        webClient.getOptions().setUseInsecureSSL(true);// 忽略ssl认证
        webClient.getOptions().setCssEnabled(false);// 禁用Css，可避免自动二次请求CSS进行渲染
        webClient.getOptions().setThrowExceptionOnScriptError(true);// 运行错误时，不抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 设置Ajax异步
        webClient.getOptions().setRedirectEnabled(true); //客户端重定向
        webClient.getOptions().setTimeout(50000);
        // 登录
        try {
            HtmlPage page01 = (HtmlPage) webClient.getPage(sUrl);
            HtmlForm form01 = page01.getForms().get(0);// page.getFormByName("")getForms().get(0);
            HtmlTextInput merId = (HtmlTextInput) form01.getInputByName("merId"); //商户号
            HtmlTextInput txnTime = (HtmlTextInput) form01.getInputByName("txnTime"); //订单发送时间
            HtmlTextInput orderId = (HtmlTextInput) form01.getInputByName("orderId"); //商户订单号
            merId.setValueAttribute("777290058110097");
            txnTime.setValueAttribute("20190125155055");
            orderId.setValueAttribute("20190125155055369");

            // submit没有name，只有class和value属性，通过value属性定位元素
            HtmlSubmitInput submit01 = (HtmlSubmitInput) form01.getInputByValue("跳转到银联页面开通");
            /**第一个页面提交后page重新赋值为新的跳转页面*/
            System.out.println("################Page1###############");
            System.out.println(page01.asXml());
            HtmlPage page02 = (HtmlPage) submit01.click(); // 跳转
            /**等待渲染结果*/
            webClient.waitForBackgroundJavaScript(10000);// 等待10秒


            StringBuilder js = new StringBuilder();
            js.append("document.getElementById('smsCode').value='111111'").append(";");
            js.append("document.getElementById('cardNumber').value='6216261000000000018'").append(";");
            js.append("$('#btnCardPay').mousedown()").append(";");
            //js.append("$('#cardPay').submit()").append(";");
            ScriptResult page04 = page02.executeJavaScript(js.toString());

            webClient.waitForBackgroundJavaScript(100000);// 等待10秒

            HtmlPage page = (HtmlPage) page02.getWebClient().getCurrentWindow().getEnclosedPage();
            System.out.println("###########End############");
            System.out.println(page.asXml());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            webClient.close();
        }
    }
}