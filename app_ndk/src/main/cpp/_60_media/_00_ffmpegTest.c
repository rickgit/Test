//
// Created by anshu-pc on 2020/8/27.
//

#include "_60_media/_00_ffmpegTest.h"
#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"

void testRecordAudio();

void testFfmpeg() {
    testRecordAudio();
}

void testRecordAudio() {
//    avcodec_send_frame()/avcodec_receive_packet()
    av_register_all();
    AVFormatContext *pContext = avformat_alloc_context();

}
