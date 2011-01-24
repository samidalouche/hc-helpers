/**
 * Copyright 2009 Sami Dalouche
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sirika.hchelpers.spring;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.content.AbstractContentBody;
import org.springframework.core.io.InputStreamSource;

import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;

/**
 * 
 * @author Sami Dalouche <sami.dalouche@gmail.com>
 *
 */

/**
 * A repeatable Source Body that fetches its data from an {@link InputStreamSource}
 * 
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 *
 */
public final class InputStreamSourceBody extends AbstractContentBody {
    private final InputStreamSource inputStreamSource;
    private final String filename;

    public InputStreamSourceBody(final InputStreamSource inputStreamSource,
            final String mimeType, final String filename) {
        super(mimeType);
        if (inputStreamSource == null) {
            throw new IllegalArgumentException(
                    "Input stream source may not be null");
        }
        this.inputStreamSource = inputStreamSource;
        this.filename = filename;
    }

    public InputStreamSourceBody(final InputStreamSource inputStreamSource,
            final String filename) {
        this(inputStreamSource, "application/octet-stream", filename);
    }

    public InputStream getInputStream() throws IOException {
        return this.inputStreamSource.getInputStream();
    }

    public void writeTo(final OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream in = null;
        try {
            in = this.inputStreamSource.getInputStream();
            ByteStreams.copy(in, out);
            out.flush();
        } finally {
            Closeables.closeQuietly(in);
        }
    }

    public String getTransferEncoding() {
        return MIME.ENC_BINARY;
    }

    public String getCharset() {
        return null;
    }

    public long getContentLength() {
        return -1;
    }

    public String getFilename() {
        return this.filename;
    }
}