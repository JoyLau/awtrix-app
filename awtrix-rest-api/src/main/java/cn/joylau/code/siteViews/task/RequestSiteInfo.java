package cn.joylau.code.siteViews.task;

import cn.joylau.code.cache.Caches;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Objects;

@Component
public class RequestSiteInfo {

    private final RestTemplate restTemplate;

    private final Caches caches;

    public RequestSiteInfo(RestTemplate restTemplate, Caches caches) {
        this.restTemplate = restTemplate;
        this.caches = caches;
    }

    @Scheduled(fixedRateString = "${task.request-site-info-rate}")
    public void run(){
        String url = "http://busuanzi.ibruce.info/busuanzi?jsonpCallback=callback";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Referer","https://blog.joylau.cn");
        headers.add("Cookie","busuanziId=D58737A150864C68B83F962028616CD6");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,String.class);
        String result = Objects.requireNonNull(responseEntity.getBody()).replace("try{callback(","").replace(");}catch(e){}","");
        HashMap<String,Object> map = caches.getSiteInfo();
        map.put("blog.joylau.cn",new ObjectMapper().convertValue(result,Object.class));
        caches.setSiteInfo(map);
    }
}
