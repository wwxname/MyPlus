import com.fasterxml.jackson.core.JsonProcessingException;
import com.plus.util.JSONUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author plus me
 * @date 2018/9/10
 */
public class MainTest {
    private static String docker = "http://registry.gs.youyuwo.com";
    private static String dcos = "http://10.0.12.21:8080";

    private static RestTemplate restTemplate = new RestTemplateBuilder().build();

    private static Map param = new HashMap<String, String>();

    public static void main(String args[]) throws JsonProcessingException {
        param.put("name", "platform/ops-ticket");
        //uri = "/v2/apps/{app_id}/tasks";
        Map rep = restTemplate.getForObject(docker + "/v2/{name}/tags/list", Map.class, param);
        JSONUtils.beautyPrint(rep);
    }
}
