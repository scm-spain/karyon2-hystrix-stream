package com.scmspain.karyon.hystrixstreamendpoint.module.server;

import com.google.inject.Singleton;
import com.netflix.governator.annotations.Modules;
import netflix.karyon.KaryonBootstrap;
import scmspain.karyon.restrouter.KaryonRestRouterModule;

@KaryonBootstrap(name = "AppServer")
@Singleton
@Modules(include = {
    ModuleBasedAppServerForTesting.KaryonRestRouterModuleImpl.class
})
public interface ModuleBasedAppServerForTesting {
    class KaryonRestRouterModuleImpl extends KaryonRestRouterModule {

        public static final int DEFAULT_PORT = 8000;
        public static final int DEFAULT_THREADS = 20;

        @Override
        protected void configureServer() {
            server().port(DEFAULT_PORT).threadPoolSize(DEFAULT_THREADS);
        }

        @Override
        public void configure()
        {
            super.configure();
        }
    }
}
