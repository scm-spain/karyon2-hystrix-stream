package com.scmspain.karyon.hystrixstreamendpoint.common;

import com.google.inject.Singleton;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import rx.Observable;
import scmspain.karyon.restrouter.annotation.Endpoint;
import scmspain.karyon.restrouter.annotation.Path;

@Singleton
@Endpoint
public class ExampleController {
    @Path(value = "/sayHello", method = "GET")
    public Observable<Void> sayHelloExampleEndpoint(HttpServerResponse<ByteBuf> response) {
        return response.writeStringAndFlush("Hello");
    }
}