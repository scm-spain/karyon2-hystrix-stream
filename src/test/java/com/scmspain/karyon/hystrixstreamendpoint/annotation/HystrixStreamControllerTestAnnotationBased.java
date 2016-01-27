package com.scmspain.karyon.hystrixstreamendpoint.annotation;

import org.junit.Assert;
import org.junit.Test;

/**
 */
public class HystrixStreamControllerTestAnnotationBased extends AnnotationBasedAbstractControllerTest {

    @Test
    public void testHystrixStreamEndpoint() throws Exception {
        hystrixCommandService.sendSomeHystrixCommand(serverHost(), serverPort(), "/sayHello");

        final String contents = inputStreamReader.getFirstContentFromStream(hystrixStreamUrl());

        Assert.assertTrue("Not receiving data from endpoint", contents.contains("data:"));
    }
}
