package com.example.demo.volley1;

import com.example.demo.volley1.part.Part;

import org.apache.http.entity.AbstractHttpEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guozhk on 16-7-21.
 */
public class MultipartEntity extends AbstractHttpEntity implements Cloneable {

    private List<Part> parts = new ArrayList<Part>();

    @Override
    public boolean isRepeatable() {
        return true;
    }

    public void addPart(Part part) {
        parts.add(part);
    }

    @Override
    public long getContentLength() {
        long result = 0;
        for (Part part : parts) {
            result += part.getContentLength();
        }
        result += VolleyContanse.CLOSING_BYTE.length;
        return result;
    }

    @Override
    public InputStream getContent() throws IOException, IllegalStateException {
        return null;
    }

    @Override
    public void writeTo(OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Output stream may not be null");    //$NON-NLS-1$
        }
        for (Part part : parts) {
            part.writeTo(out);
        }
        out.write(VolleyContanse.CLOSING_BYTE);
        out.flush();

    }

    @Override
    public boolean isStreaming() {
        return false;
    }
}
