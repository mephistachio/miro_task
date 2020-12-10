package com.miro.task.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApiFunc {

    private static final String CREATE_BOARD_URL = "https://api.miro.com/v1/boards";
    private static final String CREATE_WIDGET_URL = "https://api.miro.com/v1/boards/%s/widgets";


    public static String createBoard(String authorizationID, String boardName) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        SharingPolicy policy = new SharingPolicy("comment", "comment");
        CreateBoardRequest request = new CreateBoardRequest(boardName, policy);
        String requestBody = objectMapper.writeValueAsString(request);
        Header[] headers = new Header[2];
        headers[0] = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        headers[1] = new BasicHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", authorizationID));
        String response = sendPOST(CREATE_BOARD_URL, requestBody, headers);
        return response;

    }

    public static String createWidget(String authorizationID, String boardID, String type, String title) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        WidgetStyle style = new WidgetStyle("#ff00ff");
        CreatWidgetRequest request = new CreatWidgetRequest(type, title, style);
        String requestBody = objectMapper.writeValueAsString(request);
        Header[] headers = new Header[2];
        headers[0] = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        headers[1] = new BasicHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", authorizationID));
        String response = sendPOST(String.format(CREATE_WIDGET_URL, boardID), requestBody, headers);
        return response;

    }

    private static String sendPOST(String url, String requestBody, Header[] headers) throws IOException {

        HttpPost post = new HttpPost(url);
        post.setHeaders(headers);
        post.setEntity(new StringEntity(requestBody));


        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            return EntityUtils.toString(response.getEntity());
        }

    }

    @Value
    private static class CreateBoardRequest {
        String name;
        SharingPolicy sharingPolicy;
    }

    @Value
    private static class SharingPolicy {
        String access;
        String teamAccess;
    }

    @Value
    private static class CreatWidgetRequest {
        String type;
        String title;
        WidgetStyle style;
    }

    @Value
    private static class WidgetStyle {
        String backgroundColor;
    }

}

