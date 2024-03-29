package com.example.demo.volley1.part;

import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by guozhk on 16-7-21.
 */
public class StringPart extends BasePart {


    private final byte[] valueBytes;

    /**
     * @param name    String - name of parameter (may not be <code>null</code>).
     * @param value   String - value of parameter (may not be <code>null</code>).
     * @param charset String, if null is passed then default "ISO-8859-1" charset is used.
     * @throws IllegalArgumentException if either <code>value</code>
     *                                  or <code>name</code> is <code>null</code>.
     * @throws RuntimeException         if <code>charset</code> is unsupported by OS.
     */
    public StringPart(String name, String value, String charset) {
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");     //$NON-NLS-1$
        }
        if (value == null) {
            throw new IllegalArgumentException("Value may not be null");    //$NON-NLS-1$
        }

        final String partName = name;

        if (charset == null) {
            charset = HTTP.DEFAULT_CONTENT_CHARSET;
        }
        final String partCharset = charset;

        try {
            this.valueBytes = value.getBytes(partCharset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        headersProvider = new IHeadersProvider() {
            public String getContentDisposition() {
                return "Content-Disposition: form-data; name=\"" + partName + '"';                  //$NON-NLS-1$
            }

            public String getContentType() {
                return "Content-Type: " + HTTP.PLAIN_TEXT_TYPE + HTTP.CHARSET_PARAM + partCharset;  //$NON-NLS-1$
            }

            public String getContentTransferEncoding() {
                return "Content-Transfer-Encoding: 8bit";                                           //$NON-NLS-1$
            }
        };
    }


    public StringPart(String name, String value) {
        this(name, value, null);
    }

    public long getContentLength() {
        return getHeader().length + valueBytes.length + CRLF.length;
    }

    public void writeTo(final OutputStream out) throws IOException {
        out.write(getHeader());
        out.write(valueBytes);
        out.write(CRLF);
    }
}
