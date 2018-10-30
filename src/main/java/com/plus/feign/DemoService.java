package com.plus.feign;

import feign.*;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import lombok.Data;

/**
 * @author plus me
 * @date 2018/10/29
 */
public interface DemoService {
    static DemoService getInstance(String host) {
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(DemoService.class, host);
    }

    /**
     * kkl
     *
     * @param userId
     * @return
     */
    @RequestLine("GET /user/findByUserEmail?email={email}")
    @Headers({"X-Uid: {userId}", "Accept: application/json"})
    Result findByUserEmail(@Param(value = "email") String email, @Param(value = "userId") Long userId);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @RequestLine("POST /users/list")
    User getOwner(User user);
}



class User {

    public static void main(String[] args) {
        DemoService demoService = DemoService.getInstance("http://10.0.33.19:14044/");
        Result o = demoService.findByUserEmail("@", 1L);
        System.err.println(o.toString()+o.getDesc());
    }

}