package cst438.hw3.twitter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

@RestController
@RequestMapping("/twitter")
public class Twitter {
    private TwitterAuth auth;
    private HttpEntity<String> entity;

    Twitter(TwitterAuth auth) {
        this.auth = auth;
        entity = buildEntity(auth.getAccessToken());
    }

    private HttpEntity<String> buildEntity(String access_token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + access_token);
        headers.add("Content-Type", "application/json");
        return new HttpEntity<>(headers);
    }

    @RequestMapping("/user_timeline")
    public ResponseEntity<String> user_timeline(
            @RequestParam(value="screen_name") String screen_name) throws Exception {
        RestTemplate rest = new RestTemplate();
        return rest.exchange("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=" +
                        URLEncoder.encode(screen_name, "UTF-8"), HttpMethod.GET, entity, String.class);
    }
}
