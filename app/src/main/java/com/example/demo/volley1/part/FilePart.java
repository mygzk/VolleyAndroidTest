package com.example.demo.volley1.part;

import org.apache.http.protocol.HTTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by guozhk on 16-7-21.
 */
public class FilePart extends BasePart {
    private File file;
  //  protected static final byte[] CRLF = EncodingUtils.getAsciiBytes(VolleyContanse.CRLF);


    public FilePart(String name, File file, String filename, String contentType) {
        if (file == null) {
            throw new IllegalArgumentException("File may not be null");     //$NON-NLS-1$
        }
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");     //$NON-NLS-1$
        }

        this.file = file;
        final String partName = name;
        final String partFilename = (filename == null) ? file.getName() : filename;
        final String partContentType = (contentType == null) ? HTTP.DEFAULT_CONTENT_TYPE : contentType;

        headersProvider = new IHeadersProvider() {
            public String getContentDisposition() {
                return "Content-Disposition: form-data; name=\"" + partName //$NON-NLS-1$
                        + "\"; filename=\"" + partFilename + '"';           //$NON-NLS-1$
            }

            public String getContentType() {
                return "Content-Type: " + partContentType;                  //$NON-NLS-1$
            }

            public String getContentTransferEncoding() {
                return "Content-Transfer-Encoding: binary";                 //$NON-NLS-1$
            }
        };
    }

    public long getContentLength() {
        return getHeader().length + file.length() + CRLF.length;
    }

    @Override
    public void writeTo(OutputStream out) throws IOException {
        out.write(getHeader());
        InputStream in = new FileInputStream(file);
        try {
            byte[] tmp = new byte[4096];
            int l;
            while ((l = in.read(tmp)) != -1) {
                out.write(tmp, 0, l);
            }
        } finally {
            in.close();
        }
        out.write(CRLF);

    }
}
