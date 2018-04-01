package cst438.hw3.twitter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

class ResponseToken {
    String token_type, access_token;

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}

@Controller
public class TwitterAuth implements InitializingBean {
    private HttpEntity<String> entity;

    @Value("${twitter_access_token}")
    private String twitter_access_token;

    @Value("${twitter_api_key}")
    private String twitter_api_key;

    @Value("${twitter_api_secret}")
    private String twitter_api_secret;

    public String getAccessToken() {
        RestTemplate rest = new RestTemplate();
        ResponseEntity<ResponseToken> response = rest.postForEntity("https://api.twitter.com/oauth2/token",
                entity, ResponseToken.class);
        return response.getBody().access_token;
    }

    private HttpEntity<String> buildEntity(String bearerB64) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Basic " + bearerB64);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        return new HttpEntity<>("grant_type=client_credentials", headers);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String token = this.twitter_api_key + ":" + this.twitter_api_secret;
        String bearerB64 = Base64.getEncoder().encodeToString(token.getBytes("utf-8"));
        entity = buildEntity(bearerB64);
    }
}
