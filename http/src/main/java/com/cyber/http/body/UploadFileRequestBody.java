package com.cyber.http.body;


import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class UploadFileRequestBody extends RequestBody {
    private RequestBody mRequestBody;

    public UploadFileRequestBody(File file) {
        this.mRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
    }


    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(@Nullable BufferedSink sink) throws IOException {
        CountingSink countingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(countingSink);
        // 写入
        mRequestBody.writeTo(bufferedSink);
        // 刷新
        // 必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();

    }

    /**
     * CountingSink.
     */
    protected final class CountingSink extends ForwardingSink {
        //        private long bytesWritten = 0;
        CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(@Nullable Buffer source, long byteCount) throws IOException {
            super.write(Objects.requireNonNull(source), byteCount);
//            bytesWritten += byteCount;
//            if (fileUploadObserver != null) {
//                fileUploadObserver.onProgressChange(bytesWritten, contentLength());
//            }
        }
    }
}