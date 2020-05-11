package app;

import com.amazonaws.serverless.proxy.model.*;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyResponseEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AppTest {
  @Test
  public void successfulResponse() {
    //ProxyLambdaHandler app = new ProxyLambdaHandler();
    AwsProxyRequest request = new AwsProxyRequest();
    String body = "{\"name\":\"doki4\"}";
    //request.setBody(body);
    request.setPath("/find-account/Alexandre");
    request.setHttpMethod("GET");
    Headers headers = new Headers();
           headers.add("Accept", "text/html,application/xhtml+xml,application/xml");
           headers.add("Accept-Language", "en-US,en;q=0.8");
           headers.add("Cache-Control", "max-age=0");
           headers.add("CloudFront-Forwarded-Proto", "https");
           headers.add("CloudFront-Is-Desktop-Viewer", "true");
           headers.add("CloudFront-Is-Mobile-Viewer", "false");
           headers.add("CloudFront-Is-SmartTV-Viewer", "false");
           headers.add("CloudFront-Is-Tablet-Viewer", "false");
           headers.add("CloudFront-Viewer-Country", "US");
           headers.add("Host", "1234567890.execute-api.{dns_suffix}");
           headers.add("Upgrade-Insecure-Requests", "1");
           headers.add("User-Agent", "Custom User Agent String");
           headers.add("Via", "1.1 08f323deadbeefa7af34d5feb414ce27.cloudfront.net (CloudFront)");
           headers.add("X-Amz-Cf-Id", "cDehVQoZnx43VYQb9j2-nvCh-9z396Uhbp027Y2JvkCPNLmGJHqlaA==");
           headers.add("X-Forwarded-For", "127.0.0.1, 127.0.0.2");
           headers.add("X-Forwarded-Port", "443");
           headers.add("X-Forwarded-Proto", "https");
    request.setMultiValueHeaders(headers);
    request.setResource("/{proxy+}");
    AwsProxyRequestContext context =  new AwsProxyRequestContext();
          context.setAccountId("123456789012");
           context.setResourceId("123456");
           context.setStage("prod");
           context.setRequestId("c6af9ac6-7b61-11e6-9a41-93e8deadbeef");
           ApiGatewayRequestIdentity identity = new ApiGatewayRequestIdentity();
           identity.setCognitoIdentityPoolId(null);
           identity.setAccountId(null);
           identity.setCognitoIdentityId(null);
           identity.setCaller(null);
           identity.setApiKey(null);
           identity.setSourceIp("127.0.0.1");
           identity.setCognitoAuthenticationType(null);
           identity.setCognitoAuthenticationProvider(null);
           identity.setUserArn(null);
           identity.setUserAgent("Custom User Agent String");
           identity.setUser(null);
           context.setIdentity(identity);


    request.setRequestContext(context);

    //AwsProxyResponse result = app.handleRequest(request, null);

  }
}
