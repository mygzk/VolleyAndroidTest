package com.example.demo.volley1.part;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by guozhk on 16-7-21.
 */
public interface Part {
    public long getContentLength();
    public void writeTo(final OutputStream out) throws IOException;
}
