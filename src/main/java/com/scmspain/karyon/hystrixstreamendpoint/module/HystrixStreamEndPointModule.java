package com.scmspain.karyon.hystrixstreamendpoint.module;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.netflix.governator.guice.BootstrapModule;
import com.netflix.hystrix.contrib.rxnetty.metricsstream.HystrixMetricsStreamHandler;
import com.scmspain.karyon.hystrixstreamendpoint.HystrixStreamController;
import io.netty.buffer.ByteBuf;
import netflix.karyon.Karyon;
import netflix.karyon.transport.http.SimpleUriRouter;

/**
 */
public class HystrixStreamEndPointModule extends AbstractModule {
    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure() {
        bind(HystrixStreamController.class).asEagerSingleton();
        // HystrixMetricsStreamHandler must be bound as "Router", getting RestBasedHandler as delegated,
        // but KaryonRestRouter does not provides a way to override the router class nor chain it
        // Find a better way to bind HystrixMetricsStreamHandler.
        bind(new TypeLiteral<HystrixMetricsStreamHandler<ByteBuf, ByteBuf>>() {})
            .toInstance(new HystrixMetricsStreamHandler<>(new SimpleUriRouter<>()));
    }

    public static BootstrapModule asBootstrapModule() {
        return Karyon.toBootstrapModule(HystrixStreamEndPointModule.class);
    }
}
