package battle.ykostrub.cn.ua;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 *
 *
 * @author Yuriy Kostrub(http://astelit.ukr).
 */
public class DoBattle {

  private static String url = "http://robobattleship.com/";

  public static void main(String[] args) {
    
    System.out.println("Start========================");
    
    GetMethod method = null; 
    HttpClient client = null;
    try {

    
    // Create an instance of HttpClient.
     client = new HttpClient();

    // Create a method instance.
     method = new GetMethod(url);
    
    // Provide custom retry handler is necessary
    method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
        new DefaultHttpMethodRetryHandler(3, false));

      // Execute the method.
      int statusCode = client.executeMethod(method);
      
      System.err.println("statusCode: " + statusCode);

      if (statusCode != HttpStatus.SC_OK) {
        System.err.println("Method failed: " + method.getStatusLine());
      }

      // Read the response body.
      byte[] responseBody = method.getResponseBody();

      // Deal with the response.
      // Use caution: ensure correct character encoding and is not binary data
      System.out.println(new String(responseBody));

    } catch (HttpException e) {
      System.err.println("Fatal protocol violation: " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("Fatal transport error: " + e.getMessage());
      e.printStackTrace();
    }catch (Exception e) {
      e.printStackTrace();
    } 
    finally {
      // Release the connection.
      if (method != null) {
        method.releaseConnection();
      }
      
    }  
  }

}
