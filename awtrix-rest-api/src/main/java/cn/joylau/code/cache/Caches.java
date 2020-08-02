package cn.joylau.code.cache;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
public class Caches {

    private HashMap<String,Object> siteInfo = new HashMap<>();

}
