package com.scmspain.karyon.hystrixstreamendpoint;

import com.google.inject.Inject;
import com.netflix.hystrix.contrib.rxnetty.metricsstream.HystrixMetricsStreamHandler;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import rx.Observable;
import scmspain.karyon.restrouter.annotation.Endpoint;
import scmspain.karyon.restrouter.annotation.Path;

@Endpoint
public class HystrixStreamController {

    private final HystrixMetricsStreamHandler<ByteBuf, ByteBuf> hystrixHandler;

    @Inject
    public HystrixStreamController(HystrixMetricsStreamHandler<ByteBuf, ByteBuf> hystrixHandler) {
        this.hystrixHandler = hystrixHandler;
    }

    @Path(value = HystrixMetricsStreamHandler.DEFAULT_HYSTRIX_PREFIX, method = "GET")
    public Observable<Void> hystrix(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        return this.hystrixHandler.handle(request, response);
    }
}
