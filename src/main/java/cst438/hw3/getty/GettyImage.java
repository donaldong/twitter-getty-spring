package cst438.hw3.getty;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.Arrays;

@RestController
@RequestMapping("/getty")
@CrossOrigin(origins = {"*"})
public class GettyImage implements InitializingBean {
    @Value("${getty_images_key}")
    String getty_images_key;
    HttpEntity<String> entity;

    GettyImage() {
    }

    @RequestMapping("/search")
    public ResponseEntity<String> search(
            @RequestParam(value="phrase") String phrase) throws Exception {
        RestTemplate rest = new RestTemplate();
        return rest.exchange("https://api.gettyimages.com/v3/search/images?" +
                "embed_content_only=true&fields=comp&graphical_styles=photography&minimum_size=xx_large&" +
                "sort_order=most_popular&phrase=" + URLEncoder.encode(phrase, "UTF-8"), HttpMethod.GET, entity, String.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-Key", getty_images_key);
        entity = new HttpEntity<>(headers);
    }
}
