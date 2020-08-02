package cn.joylau.code.siteViews.rest;

import cn.joylau.code.cache.Caches;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Rest {

    private final Caches caches;

    public Rest(Caches caches) {
        this.caches = caches;
    }

    @GetMapping("/getSiteInfo/{host}")
    public Object getSiteInfo(@PathVariable String host){
        return caches.getSiteInfo().get(host);
    }
}
