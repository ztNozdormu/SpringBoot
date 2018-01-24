package org.dcm4chee.arc.knd.util.http;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;
import net.sf.json.JSONObject;
public class HttpRequestUtils {

    
    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @return
     */
    public static JSONObject httpPost(String url,Object params){
    	CloseableHttpClient httpclient = HttpClients.createDefault();
    	HttpPost httpPost = new HttpPost(url);
    	List <NameValuePair> nvps = new ArrayList <NameValuePair>();
    	try {
    		Field[] field = params.getClass().getDeclaredFields();
    		for (int i = 0; i < field.length; i++) {
    			String name = field[i].getName();
    			String methodName = name.substring(0,1).toUpperCase()+name.substring(1);
    			Method m = params.getClass().getMethod("get"+methodName);
    			Object value = m.invoke(params);
    			if(value!=null){
    				nvps.add(new BasicNameValuePair(name, value.toString()));
    			}
    		}
    		UrlEncodedFormEntity urlEncodedFormentity = new UrlEncodedFormEntity(nvps,HTTP.UTF_8);
    		httpPost.setEntity(urlEncodedFormentity);
    		CloseableHttpResponse response = httpclient.execute(httpPost);
			System.out.println(url +" "+ response.getStatusLine());
			HttpEntity entity = response.getEntity();
			if(entity!=null&&entity.isStreaming()){	
		        String result = EntityUtils.toString(entity);
		        JSONObject json = JSONObject.fromObject(result);
		        boolean state = (Boolean) json.get("state");
		        if(state){
		        	return JSONObject.fromObject(json.get("data"));
		        }else{
		        	JSONObject errorJson = new JSONObject();
		        	String msg = json.getString("msg");
		        	errorJson.put("error_info", msg);
		        	return errorJson;
		        }
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			JSONObject errorJson = new JSONObject();
        	errorJson.put("error_info", "接口服务异常");
        	return errorJson;
		}
        return null;
    }
    
    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @return
     */
    public static JSONObject httpPost(String url,JSONObject params){
    	CloseableHttpClient httpclient = HttpClients.createDefault();
    	HttpPost httpPost = new HttpPost(url);
    	List <NameValuePair> nvps = new ArrayList <NameValuePair>();
    	try {
    		for (Object object : params.keySet()) {
    			String key = object.toString();
    			String value = params.getString(key);
    			nvps.add(new BasicNameValuePair(key, value));
			}
    		UrlEncodedFormEntity urlEncodedFormentity = new UrlEncodedFormEntity(nvps,HTTP.UTF_8);
    		httpPost.setEntity(urlEncodedFormentity);
    		CloseableHttpResponse response = httpclient.execute(httpPost);
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			if(entity!=null&&entity.isStreaming()){	
		        String result = EntityUtils.toString(entity);
		        JSONObject json = JSONObject.fromObject(result);
		        boolean state = (Boolean) json.get("state");
		        if(state){
	        		return JSONObject.fromObject(json.get("data"));
		        }else{
		        	JSONObject errorJson = new JSONObject();
		        	String msg = json.getString("msg");
		        	errorJson.put("error_info", msg);
		        	return errorJson;
		        }
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject errorJson = new JSONObject();
        	errorJson.put("error_info", "接口服务异常");
        	return errorJson;
		}
        return null;
    }
    
    /**
     * GET请求
     * 
     * @param url
     *            URL
     * @param parameterMap
     *            请求参数
     * @return 返回结果
     */
    public static String get(String url, Map<String, Object> parameterMap) {
        Assert.hasText(url);
        String result = null;
        HttpClient httpClient = HttpClients.createDefault();
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null) {
                for (Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    if (StringUtils.isNotBlank(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            HttpGet httpGet = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?")
                    + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }

    /**
     * POST请求
     * 
     * @param url
     *            URL
     * @param parameterMap
     *            请求参数
     * @return 返回结果
     */
    public static String post(String url, Map<String, Object> parameterMap) {
        Assert.hasText(url);
        String result = null;
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null) {
                for (Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    if (StringUtils.isNotBlank(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }
    
    /**
     * GET请求文件下载
     * 
     * @param url
     *            URL
     * @param parameterMap
     *            请求参数
     * @param filePath
     *            文件储存路劲
     */
    public static void downloadForGet(String url, Map<String, Object> parameterMap,String filePath,String fileName) {
    	Assert.hasText(url);
    	
		
    	HttpClient httpClient = HttpClients.createDefault();
    	try {
    		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    		if (parameterMap != null) {
    			for (Entry<String, Object> entry : parameterMap.entrySet()) {
    				String name = entry.getKey();
    				String value = ConvertUtils.convert(entry.getValue());
    				if (StringUtils.isNotBlank(name)) {
    					nameValuePairs.add(new BasicNameValuePair(name, value));
    				}
    			}
    		}
    		HttpGet httpGet = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?")
    				+ EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
    		HttpResponse httpResponse = httpClient.execute(httpGet);
    		HttpEntity httpEntity = httpResponse.getEntity();
    		
    		InputStream in = httpEntity.getContent();
    		FileOutputStream out = new FileOutputStream(filePath+"/"+fileName);  
    		IOUtils.copy(in, out);
    		in.close();  
    		out.close();
    		
    		EntityUtils.consume(httpEntity);
    	} catch (ClientProtocolException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		httpClient.getConnectionManager().shutdown();
    	}
    }
    
    /**
     * GET请求下载文件流
     * @param url
     * @param parameterMap
     * @return 文件流
     */
    public static InputStream downloadStreamForGet(String url, Map<String, Object> parameterMap) {
        Assert.hasText(url);
        HttpClient httpClient = HttpClients.createDefault();
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null) {
                for (Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    if (StringUtils.isNotBlank(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            HttpGet httpGet = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?")
                    + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            
            InputStream in = httpEntity.getContent();
            
            return in;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    public static void main(String[] args) {
		
	}
}