package com.scmspain.karyon.hystrixstreamendpoint.common;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class InputStreamReader {

    public String getFirstContentFromStream(String url) {
        return Observable.defer(() -> {
            try {
                final String contents = readStream(url);

                return Observable.just(contents);
            } catch (IOException e) {
                return Observable.error(e);
            }
        })
            .subscribeOn(Schedulers.newThread())
            .timeout(1, TimeUnit.SECONDS, Observable.just(""))
            .toBlocking().first()
            ;
    }

    private String readStream(String url) throws IOException {
        URL hystrixUrl = new URL(url);
        InputStream in = hystrixUrl.openStream();
        byte[] buffer = new byte[2048];
        final int bytesRead = in.read(buffer);
        final String contents = bytesRead>0 ? new String(buffer) : "";
        in.close();
        return contents;
    }
}