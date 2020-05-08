package app;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyResponseEvent;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import config.AppConfig;
import entities.Arquivo;
import model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import service.ArquivoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayV2ProxyRequestEvent, APIGatewayV2ProxyResponseEvent> {

    private static SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try {
            handler = SpringLambdaContainerHandler.getAwsProxyHandler(AppConfig.class);
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring framework", e);
        }
    }

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
                    Arquivo arquivo = new Arquivo(dataCar.getName(),"Teste");

                    MongoClient mongoClient = MongoClients.create("mongodb+srv://account-credit:teste@cluster0-uhz0z.mongodb.net/test?retryWrites=true&w=majority");
                    MongoDatabase database = mongoClient.getDatabase("account_credit");


                    MongoOperations mongoOps = new MongoTemplate(new SimpleMongoClientDbFactory(mongoClient, "account_credit"));

                    mongoOps.save(arquivo);
                    //arquivoService.salvar(dataCar.getName(),"Teste");

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
