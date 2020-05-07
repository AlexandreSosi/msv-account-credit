package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyResponseEvent;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import model.Account;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayV2ProxyRequestEvent, APIGatewayV2ProxyResponseEvent> {

    public APIGatewayV2ProxyResponseEvent handleRequest(final APIGatewayV2ProxyRequestEvent input, final Context context) {
        LambdaLogger logger = context != null ? context.getLogger(): null;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        try {
            APIGatewayV2ProxyResponseEvent response = new APIGatewayV2ProxyResponseEvent();
            Account dataCar = new Account();
            Gson g = new Gson();
            if (input != null ) {
                String body = input.getBody();
                System.out.println(body);
                if (input.getBody() != null) {
                    dataCar = g.fromJson(input.getBody(), Account.class);
                }
            }
            final String pageContents = this.getPageContents("https://checkip.amazonaws.com");
            //String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents);

            response.setBody(g.toJson(dataCar));
            response.setHeaders(headers);
            response.setStatusCode(200);
            return response;
        } catch (JsonParseException e) {
            logger.log("Erro parsing Json da mensagem");
            APIGatewayV2ProxyResponseEvent response = getResponseError(headers);
            return response;
        } catch (JsonMappingException e) {
            logger.log("Erro Mapeamento Json da mensagem");
            APIGatewayV2ProxyResponseEvent response = getResponseError(headers);
            return response;
        } catch (IOException e) {
            APIGatewayV2ProxyResponseEvent response = getResponseError(headers);
            return response;
        }
    }

    private APIGatewayV2ProxyResponseEvent getResponseError(Map<String, String> headers) {
        APIGatewayV2ProxyResponseEvent response = new APIGatewayV2ProxyResponseEvent();
        response.setStatusCode(500);
        response.setBody("{}");
        response.setHeaders(headers);
        return response;
    }


    private String getPageContents(String address) throws IOException{
        URL url = new URL(address);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
