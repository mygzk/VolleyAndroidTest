package com.example.demo.volley1;

import org.apache.http.util.EncodingUtils;

/**
 * Created by guozhk on 16-7-21.
 */
public class VolleyContanse {

    public static final String PROTOCOL_CHARSET = "utf-8";
    public static final int TIMEOUT_MS = 60 * 1000;

    public final static String boundary =
            "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CRLF = "\r\n";
    public static final String starting = "--" + boundary + CRLF;         //$NON-NLS-1$
    public static final String closing = "--" + boundary + "--" + CRLF;

    public static final byte[] CRLF_BYTE = EncodingUtils.getAsciiBytes(CRLF);
    public static final byte[] CLOSING_BYTE = EncodingUtils.getAsciiBytes(closing);
    public static final byte[] STRARTING_BYTE = EncodingUtils.getAsciiBytes(starting);


}
