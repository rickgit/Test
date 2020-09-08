//
// Created by anshu-pc on 2020/9/7.
//


#include <libavformat/avformat.h>

void testDumpInfo();

void testFfmpegMediaInfo() {
    testDumpInfo();
}

void testDumpInfo() {
    AVFormatContext *ps = NULL;
    av_register_all();
    int ret = avformat_open_input(&ps, "/data/data/edu.ptu.java.myapplication/files/out_m.mp4",
                                  NULL, NULL);

    if (ret < 0)
        return;//error
    av_dump_format(ps, 0, "/data/data/edu.ptu.java.myapplication/files/out_m.mp4", 0);
    avformat_close_input(&ps);
    return;
}