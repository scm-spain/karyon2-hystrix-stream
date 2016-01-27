package com.scmspain.karyon.hystrixstreamendpoint.module;

import com.scmspain.karyon.hystrixstreamendpoint.common.HystrixCommandService;
import com.scmspain.karyon.hystrixstreamendpoint.common.InputStreamReader;
import com.scmspain.karyon.hystrixstreamendpoint.module.server.ModuleBasedAppServerForTesting;
import netflix.karyon.Karyon;
import netflix.karyon.KaryonServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

abstract public class ModuleBasedAbstractControllerTest {
    private static KaryonServer server;
    protected final InputStreamReader inputStreamReader = new InputStreamReader();
    protected final HystrixCommandService hystrixCommandService = new HystrixCommandService();

    @BeforeClass
    public static void setUpBefore() throws Exception {
        server = Karyon.forApplication(ModuleBasedAppServerForTesting.class, HystrixStreamEndPointModule.asBootstrapModule());
        server.start();
    }

    @AfterClass
    public static void tearDownAfter() throws Exception {
        if (server != null) {
            server.shutdown();
        }
    }

    protected String serverHost() {
        return "localhost";
    }

    protected int serverPort() {
        return ModuleBasedAppServerForTesting.KaryonRestRouterModuleImpl.DEFAULT_PORT;
    }

    protected String hystrixStreamUrl() {
        return "http://" + serverHost() + ":" + serverPort() + "/hystrix.stream";
    }
}
