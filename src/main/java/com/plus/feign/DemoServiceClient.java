package com.plus.feign;

import feign.*;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.apache.commons.lang.time.DateUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author plus me
 * @date 2018/10/29
 */
public interface DemoServiceClient {
    static DemoServiceClient getInstance(String host) {
        return Feign.builder()
                .errorDecoder(new DemoErrorDecoder())
                .encoder(new GsonEncoder())
                .decoder(new DemoGsonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 2))
                .target(DemoServiceClient.class, host);
    }

    /**
     * kkl
     *
     * @param userId
     * @return
     */
    @RequestLine("GET /user/findByUserEmail00?email={email}")
    @Headers({"X-Uid: {userId}", "Accept: application/json"})
    Result findByUserEmail(@Param(value = "email") String email, @Param(value = "userId") Long userId);

//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @RequestLine("POST /users/list")
//    User getOwner(User user);

}

class DemoGsonDecoder extends GsonDecoder {
    @Override
    public Object decode(Response response, Type type) throws IOException {
        Result result = (Result) super.decode(response, type);
        System.err.println("------------------------");
        if (!result.getCode().equals(Integer.valueOf(1))) {
            throw DemoErrorDecoder.decode(result.getCode());
        }
        return result;
    }
}

class DemoErrorDecoder extends ErrorDecoder.Default implements ErrorDecoder {

    /**
     * Implement this method in order to decode an HTTP {@link Response} when {@link
     * Response#status()} is not in the 2xx range. Please raise  application-specific exceptions where
     * possible. If your exception is retryable, wrap or subclass {@link RetryableException}
     *
     * @param methodKey {@link Feign#configKey} of the java method that invoked the request.
     *                  ex. {@code IAM#getUser()}
     * @param response  HTTP response where {@link Response#status() status} is greater than or equal
     *                  to {@code 300}.
     * @return Exception IOException, if there was a network error reading the response or an
     * application-specific exception decoded by the implementation. If the throwable is retryable, it
     * should be wrapped, or a subtype of {@link RetryableException}
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        return super.decode(methodKey, response);
    }

    public static RuntimeException decode(Integer code) {
        //Date date = DateUtils.addSeconds(new Date(),2);
        return new RetryableException("laji", null);

    }
}

class User {

    public static void main(String[] args) {
        DemoServiceClient demoService = DemoServiceClient.getInstance("http://10.0.33.19:14044/");
        Result o = demoService.findByUserEmail("@", 1L);
        //System.err.println(o.toString()+o.getDesc());
    }

}