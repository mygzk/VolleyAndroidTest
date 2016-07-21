package com.example.demo.volley1.part;

import com.example.demo.volley1.VolleyContanse;

import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;

/**
 * Created by guozhk on 16-7-21.
 */
public abstract class BasePart implements Part {
    protected static final byte[] CRLF = VolleyContanse.CRLF_BYTE;

    protected interface IHeadersProvider {
        String getContentDisposition();

        String getContentType();

        String getContentTransferEncoding();
    }

    protected IHeadersProvider headersProvider; // must be initialized in descendant constructor



    private byte[] header;

    protected byte[] getHeader() {
        if (header == null) {
            header = generateHeader(); // lazy init
        }
        return header;
    }

    private byte[] generateHeader() {
        if (headersProvider == null) {
            throw new RuntimeException("Uninitialized headersProvider");    //$NON-NLS-1$
        }
        final ByteArrayBuffer buf = new ByteArrayBuffer(256);
        append(buf, VolleyContanse.starting);
        append(buf, headersProvider.getContentDisposition());
        append(buf, CRLF);
        append(buf, headersProvider.getContentType());
        append(buf, CRLF);
        append(buf, headersProvider.getContentTransferEncoding());
        append(buf, CRLF);
        append(buf, CRLF);
        return buf.toByteArray();
    }

    private static void append(ByteArrayBuffer buf, String data) {
        append(buf, EncodingUtils.getAsciiBytes(data));
    }

    private static void append(ByteArrayBuffer buf, byte[] data) {
        buf.append(data, 0, data.length);
    }
}
