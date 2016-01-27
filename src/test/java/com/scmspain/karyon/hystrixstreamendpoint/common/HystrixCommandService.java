package com.scmspain.karyon.hystrixstreamendpoint.common;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;
import rx.Observable;

public class HystrixCommandService {
    public HystrixCommandService() {
    }

    public void sendSomeHystrixCommand(final String serverHost, final int serverPort, final String uri) {
        new HystrixObservableCommand<HttpClientResponse<ByteBuf>>(HystrixCommandGroupKey.Factory.asKey("request")) {
            @Override
            protected Observable<HttpClientResponse<ByteBuf>> construct() {
                return RxNetty.createHttpClient(
                    serverHost,
                    serverPort
                )
                    .submit(HttpClientRequest.createGet(uri));
            }
        }
            .construct().subscribe()
        ;
    }
}