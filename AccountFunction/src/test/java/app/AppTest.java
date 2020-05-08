package app;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyResponseEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AppTest {
  @Test
  public void successfulResponse() {
    App app = new App();
    //ProxyLambdaHandler app = new ProxyLambdaHandler();
    APIGatewayV2ProxyResponseEvent result = (APIGatewayV2ProxyResponseEvent) app.handleRequest(null, null);
    assertEquals(result.getStatusCode(), 200);
    assertEquals(result.getHeaders().get("Content-Type"), "application/json");
    String content = result.getBody();
    assertNotNull(content);
    //assertTrue(content.contains("\"message\""));
    //assertTrue(content.contains("\"hello world\""));
    //assertTrue(content.contains("\"location\""));
  }
}
