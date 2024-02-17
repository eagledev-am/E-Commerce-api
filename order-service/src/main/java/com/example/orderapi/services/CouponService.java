package com.example.orderapi.services;

import com.example.orderapi.dtos.CouponResponseDto;
import com.example.orderapi.exceptions.exceptiontypes.ConsumeCouponException;
import com.example.orderapi.exceptions.exceptiontypes.CouponNotFoundException;
import com.example.orderapi.exceptions.exceptiontypes.ProductOutOfStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CouponService {
    @Autowired
    private WebClient webClient;
    String url = "http://localhost:8086/coupon";

    public CouponResponseDto validateCoupon(String code){
        return webClient.get()
                .uri(url + "/validatecode", uriBuilder -> uriBuilder.queryParam("code", code).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.error(new CouponNotFoundException(response.toString()));
                })
                .bodyToMono(CouponResponseDto.class)
                .block();
    }


    public boolean consumeCoupon(String code, int orderId){
        return Boolean.TRUE.equals(webClient.put()
                .uri(url + "/consume", uriBuilder -> uriBuilder
                        .queryParam("code", code)
                        .queryParam("orderId", orderId)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new CouponNotFoundException(response.toString()));
                    }else{
                        return Mono.error(new ConsumeCouponException(response.toString()));
                    }
                })
                .bodyToMono(Boolean.class)
                .block());
    }
}
