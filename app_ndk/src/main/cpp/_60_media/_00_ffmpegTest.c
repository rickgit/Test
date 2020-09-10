//
// Created by anshu-pc on 2020/8/27.
//

#include <assert.h>
#include "_60_media/_00_ffmpegTest.h"

#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
void testLog();
void testRecordAudio();

void testFfmpeg() {
    testLog();
    testFfmpegFile();
    testFfmpegMediaInfo();
    testRecordAudio();
    testAudioStream();
    testVideoStream();
}
void testLog(){
    av_log_set_level(AV_LOG_INFO);
    av_log(NULL,AV_LOG_INFO,"running ffmpeg");//不支持
    assert(1);
}
void testRecordAudio() {
//    avcodec_send_frame()/avcodec_receive_packet()
    av_register_all();
    AVFormatContext *pContext = avformat_alloc_context();

}