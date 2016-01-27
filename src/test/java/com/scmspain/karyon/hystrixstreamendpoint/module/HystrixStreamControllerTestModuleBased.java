package com.scmspain.karyon.hystrixstreamendpoint.module;

import org.junit.Assert;
import org.junit.Test;

/**
 */
public class HystrixStreamControllerTestModuleBased extends ModuleBasedAbstractControllerTest {

    @Test
    public void testHystrixStreamEndpoint() throws Exception {
        hystrixCommandService.sendSomeHystrixCommand(serverHost(), serverPort(), "/sayHello");

        final String contents = inputStreamReader.getFirstContentFromStream(hystrixStreamUrl());

        Assert.assertTrue("Not receiving data from endpoint", contents.contains("data:"));
    }
}
