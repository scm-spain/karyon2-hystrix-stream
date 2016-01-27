package com.scmspain.karyon.hystrixstreamendpoint.annotation.server;

import com.google.inject.Singleton;
import com.netflix.governator.annotations.Modules;
import com.scmspain.karyon.hystrixstreamendpoint.common.ExampleController;
import com.scmspain.karyon.hystrixstreamendpoint.module.HystrixStreamEndPointModule;
import netflix.karyon.KaryonBootstrap;
import netflix.karyon.health.AlwaysHealthyHealthCheck;
import scmspain.karyon.restrouter.KaryonRestRouterModule;

@KaryonBootstrap(name = "AppServer")
@Singleton
@Modules(include = {
    AnnotationBasedAppServerForTesting.KaryonRestRouterModuleImpl.class,
    HystrixStreamEndPointModule.class
})
public interface AnnotationBasedAppServerForTesting {
    class KaryonRestRouterModuleImpl extends KaryonRestRouterModule {

        public static final int DEFAULT_PORT = 8000;
        public static final int DEFAULT_THREADS = 20;

        @Override
        protected void configureServer() {
            server().port(DEFAULT_PORT).threadPoolSize(DEFAULT_THREADS);
        }

        @Override
        public void configure() {
            bind(ExampleController.class).asEagerSingleton();
            super.configure();
        }
    }
}
