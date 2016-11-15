package com.harmonywisdom.dshbcbp.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpClientUtil {


	/**
	 * http或https get请求 如果为https请求 则信任所有证书，不检查证书
	 * 
	 * @param url
	 * @return 
	 */
	public static String httpOrHttpsGet(String url) throws Exception {
		CloseableHttpClient client = null;
		if (url.indexOf("https") >= 0) {
			client = createSSLClientDefault();
		} else {
			client = HttpClients.createDefault();
		}
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse httpReponse = client.execute(httpget);
		
		HttpEntity entity = httpReponse.getEntity();
		// 返回内容
		String result = EntityUtils.toString(entity);
		client.close();
		return result;

		//请求返回状态码等于200时，返回请求返回的内容字符串；请求返回状态码等于其他时，返回状态码
		/*int resStatu = httpReponse.getStatusLine().getStatusCode();
		if (resStatu == HttpStatus.SC_OK) {
			HttpEntity entity = httpReponse.getEntity();
			// 返回内容
			String result = EntityUtils.toString(entity);
			client.close();
			return result;
		} else {
			log.debug("返回的状态码：" + resStatu);
			return String.valueOf(resStatu);
		}*/

	}


	// https
	public static CloseableHttpClient createSSLClientDefault() throws Exception {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			// 信任所有
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}

	public static void main(String[] args) throws Exception {
		String url="http://110.19.109.61:9875/15DayAirQualityChangeCity.aspx?action=GetData";
//		url="https://kyfw.12306.cn/otn/regist/init";
		System.out.println(httpOrHttpsGet(url));
		
		
	}


}

