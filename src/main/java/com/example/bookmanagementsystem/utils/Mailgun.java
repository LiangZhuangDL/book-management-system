package com.example.bookmanagementsystem.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @program: book-management-system
 * @description: Mailgun设置类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public class Mailgun {

    private static final String YOUR_DOMAIN_NAME = "sandboxad81d84d11954e63ad5ef4b945e3606d.mailgun.org";
    private static final String API_KEY = "key-cd26064a831d806fc17abebb1fd191ef";

    public static JsonNode sendInlineImage() throws UnirestException {

        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + YOUR_DOMAIN_NAME + "/messages")
                .basicAuth("api", API_KEY)
                .queryString("from", "liang_zhuang_dl@outlook.com")
                .queryString("to", "zhuangliang.programmer@gmail.com")
//                .queryString("to", "bob@example.com")
//                .queryString("cc", "joe@example.com")
                .queryString("subject", "Hello")
                .queryString("text", "Testing out some Mailgun awesomeness!")
                .field("html", "<html>Inline image here</html>")
                .asJson();

        return request.getBody();
    }
}
