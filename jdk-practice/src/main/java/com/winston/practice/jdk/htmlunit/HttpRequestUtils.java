package com.winston.practice.jdk.htmlunit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLContext;


/**
 * @author IH1661
 */
public class HttpRequestUtils {

    private static CloseableHttpClient httpClientBuilder = null;

    private static String proxyIp;

    private static int proxyPort;

    private static boolean useProxy;

    private static int timeOut = 10;

    private static final String[] POST_TYPE = {"http", "https"};

    private static final String[] DASH = {"=", "&"};

    //protected final static //LOGGER //LOGGER = //LOGGERFactory.get//LOGGER(HttpRequestUtils.class);

    static CookieStore cookieStore = new BasicCookieStore();

    /**
     * 构造注入，注入spring配置文件：p2p/src/main/resources/spring-basic.xml <bean id ="httpRequestUtils" ** />
     *
     * @param proxyIp
     * @param proxyPort
     * @param useProxy
     */
    public HttpRequestUtils(String proxyIp, Integer proxyPort, Boolean useProxy) {
        HttpRequestUtils.useProxy = useProxy;
        if (useProxy) {
            HttpRequestUtils.proxyIp = proxyIp;
            HttpRequestUtils.proxyPort = proxyPort;
        }
    }

    /**
     * https
     *
     * @return CloseableHttpClient
     */
    private static CloseableHttpClient createSSLClientDefault(CookieStore cookieStore) {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType)
                  throws CertificateException {
                    return true;
                }
            }).build();

            sslsf = new SSLConnectionSocketFactory(sslContext, new String[] {"TLSv1.2"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            httpClientBuilder = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultCookieStore(cookieStore).build();

            return httpClientBuilder;
        } catch (Exception e) {
            ////LOGGER.error("创建https错误"+e);
        }
        return HttpClients.createDefault();
    }

    /**
     * @param url    请求地址
     * @param params 请求参数
     * @param set
     * @param res    返回结果
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, Map<String, String> params, Set<com.gargoylesoftware.htmlunit.util.Cookie> set) throws Exception {
        String res = null;
        RequestConfig config = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        for (com.gargoylesoftware.htmlunit.util.Cookie cook : set) {
            BasicClientCookie basicClientCookie = new BasicClientCookie(cook.getName(), cook.getValue());
            basicClientCookie.setVersion(0);
            basicClientCookie.setDomain(cook.getDomain());   //设置范围
            basicClientCookie.setPath(cook.getPath());
            cookieStore.addCookie(basicClientCookie);
        }


        if (url.toLowerCase(Locale.US).startsWith(POST_TYPE[0] + ":")) {
            httpClient = HttpClients.createDefault();
        } else if (url.toLowerCase(Locale.US).startsWith(POST_TYPE[1] + ":")) {
            httpClient = createSSLClientDefault(cookieStore);
        }

        HttpPost httpPost = new HttpPost(url);
        config = RequestConfig.custom().setConnectTimeout(timeOut * 1000).build();

        HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
        config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(timeOut * 1000).build();

        httpPost.setConfig(config);
        List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            params2.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        StringEntity entityStr = new UrlEncodedFormEntity(params2, "UTF-8");
        entityStr.setContentEncoding("UTF-8");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpPost.setEntity(entityStr);
        httpPost.setHeader("Referer", "https://cashier.test.95516.com/b2c/authpay/Activate.action?transNumber=940a9b7540ee478c84fbc9ea850ffa28");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
        httpPost.setHeader("Accept-Language", "zh-CN");

        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            for (Cookie c : cookieStore.getCookies()) {
                System.out.println(c.getName() + ": " + c.getValue());
            }
            System.err.println();
            if (200 == response.getStatusLine().getStatusCode()) {
                res = EntityUtils.toString(entity, "UTF-8");
            } else {
                res = response.getHeaders("Location")[0].getValue();
            }
        } catch (IOException e) {
            //LOGGER.error("请求异常"+e);
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
        return res;
    }

    private static String paraBuffer(Map<String, String> params) {
        StringBuffer paramsBuffer = new StringBuffer();
        if (params != null) {
            paramsBuffer = new StringBuffer();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paramsBuffer.append(entry.getKey()).append(DASH[0]).append(entry.getValue()).append(DASH[1]);
            }
            paramsBuffer.deleteCharAt(paramsBuffer.lastIndexOf(DASH[1]));
        }
        return paramsBuffer.toString().replaceAll("\\+", "%2B");
    }

}
