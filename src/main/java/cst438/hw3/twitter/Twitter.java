package cst438.hw3.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/twitter")
public class Twitter {
    @Value("${twitter_access_token}")
    private String twitter_access_token;

    @RequestMapping("/user_timeline")
    public String user_timeline(@RequestParam(value="screen_name") String screen_name) {
        return "hello, " + screen_name + " :" + this.twitter_access_token;
    }
}
