package com.amazonaws.kinesisvideo.internal.mediasource;

import com.amazonaws.kinesisvideo.common.exception.KinesisVideoException;
import com.amazonaws.kinesisvideo.producer.KinesisVideoFrame;

import java.nio.ByteBuffer;

public interface OnStreamDataAvailable {
    default void onFrameDataAvailable(final ByteBuffer frame) throws KinesisVideoException {
        // no-op
    }
    default void onFrameDataAvailable(final KinesisVideoFrame frame) throws KinesisVideoException {
        // no-op
    }
    default void onFragmentMetadataAvailable(final String metadataName, final String metadataValue,
                                             final boolean persistent) throws KinesisVideoException {
        // no-op
    }
}
