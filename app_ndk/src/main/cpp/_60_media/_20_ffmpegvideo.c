//
// Created by anshu-pc on 2020/9/7.
//


#include <libavformat/avformat.h>
#include <assert.h>
#include "libavformat/avformat.h"


int h264_toannexb(AVFormatContext *pContext, AVPacket *in_pkt, FILE *pFile);

int
alloc_and_copy(AVPacket *out, const uint8_t *sps_pps, uint32_t sps_pps_size, const uint8_t *in,
               uint32_t in_size);

int h264_extradata_to_annext(const uint8_t *codec_extradata, const int codec_extra_size,
                             AVPacket *package_extradata, int padding);

void testVideoStream() {
    AVFormatContext *ps = NULL;
    av_register_all();
    //文件需要含有I帧
    int ret = avformat_open_input(&ps, "/data/data/edu.ptu.java.myapplication/files/gui.mp4",
                                  NULL, NULL);

    if (ret < 0)
        return;//error
    ret = av_find_best_stream(ps, AVMEDIA_TYPE_VIDEO, -1, -1, NULL, 0);
    if (ret < 0)
        return;//error
    int video_index = ret;
    AVPacket pkt;
    av_init_packet(&pkt);
    FILE *dst_fd = fopen("/data/data/edu.ptu.java.myapplication/files/video.h264", "wb");
    while (av_read_frame(ps, &pkt) >= 0) {
        if (pkt.stream_index == video_index) {
            h264_toannexb(ps, &pkt, dst_fd);//adts头
        }
        av_packet_unref(&pkt);
    }

    av_dump_format(ps, 0, "/data/data/edu.ptu.java.myapplication/files/gui.mp4", 0);

    if (dst_fd) {
        fclose(dst_fd);
    }

    avformat_close_input(&ps);
    return;
}

int h264_toannexb(AVFormatContext *pContext, AVPacket *in_pkt, FILE *pFile) {
    AVPacket *out_pkt = NULL;
    AVPacket spspps_pkt;
    int len;
    uint8_t unit_type;
    int32_t nal_size;
    uint32_t cumul_size;
    const uint8_t *buf;
    const uint8_t *buf_end;
    int buf_size;
    int ret = 0, i;

    out_pkt = av_packet_alloc();
    buf = in_pkt ->data;
    buf_size = in_pkt->size;
    buf_end = in_pkt->data + in_pkt->size;

    do {
        ret = AVERROR(EINVAL);
        if (buf + 4 > buf_end) {
            goto fail;
        }
        for (nal_size = 0, i = 0; i < 4; ++i) {
            nal_size = (nal_size << 8) | buf[i];
        }
        buf += 4;
        unit_type = *buf & 0x1f;
        if (nal_size > buf_end - buf || nal_size < 0)
            goto fail;
        //https://blog.csdn.net/shaoyizhe2006/article/details/8542063
        //7 序列参数集/seq_parameter_set_rbsp；
        // 8 图像参数集/pic_parameter_set_rbsp；
        // 5 IDR图像的编码条带/slice_layer_without_partitioning_rbsp
        // 1 一个非IDR图像的编码条带/slice_layer_without_partitioning_rbsp
        if (unit_type == 5) {
            //关键帧
            h264_extradata_to_annext(pContext->streams[in_pkt->stream_index]->codec->extradata,
                                     pContext->streams[in_pkt->stream_index]->codec->extradata_size,
                                     &spspps_pkt,
                                     AV_INPUT_BUFFER_PADDING_SIZE);
            if ((ret = alloc_and_copy(out_pkt, spspps_pkt.data, spspps_pkt.size, buf, nal_size)) < 0)
                goto fail;
        } else {
            if ((ret = alloc_and_copy(out_pkt, NULL, 0, buf, nal_size)) < 0)
                goto fail;
        }
        len = fwrite(out_pkt->data, 1, out_pkt->size, pFile);
        if (len != out_pkt->size) {
            //log
        }
        fflush(pFile);
        next_nal:
        buf += nal_size;
        cumul_size += nal_size + 4;
    } while (cumul_size < buf_size);
    fail:
    av_packet_free(&out_pkt);
    return ret;
}
#ifndef AV_WB32
# define AV_WB32(p,val) do{          \
        uint32_t d=(val);           \
        ((uint8_t*)(p))[3]=(d);     \
        ((uint8_t*)(p))[2]=(d)>>8;  \
        ((uint8_t*)(p))[1]=(d)>>16; \
        ((uint8_t*)(p))[0]=(d)>>24; \
        }while(0)
#endif
#ifndef AV_RB16
# define AV_RB16(x)                       \
        ((((const uint8_t*)(x))[0]<<8)|  \
            ((const uint8_t*)(x))[1]     \
        )
#endif
int h264_extradata_to_annext(const uint8_t *codec_extradata, const int codec_extra_size,
                             AVPacket *package_extradata, int padding) {
    uint16_t unit_size;
    uint64_t total_size = 0;
    int length_size=0;
    uint8_t *out = NULL, unit_nb, sps_done = 0,
            sps_seen = 0, pps_seen = 0, sps_offset = 0, pps_offset = 0;
    const uint8_t *extradata = codec_extradata + 4;
    static const uint8_t nalu_header[4] = {0, 0, 0, 1};
    length_size = (*extradata++ & 0x3) + 1;//编码数据长度所需字节数
    sps_offset = pps_offset = -1;
    unit_nb = *extradata++ & 0x1f;//sps unit

    if (!unit_nb) {
        goto pps;
    } else {
        sps_offset = 0;
        sps_seen = 1;
    }

    while (unit_nb--) {
        int err;
        unit_size = AV_RB16(extradata);
        total_size += unit_size + 4;
        if (total_size > INT_MAX - padding) {
            //error
            av_free(out);
            return AVERROR(EINVAL);
        }
        if (extradata + 2 + unit_size > codec_extradata + codec_extra_size) {
            //error
            av_free(out);
            return AVERROR(EINVAL);
        }
        //使用av_realloc()会出现异常，修改为av_reallocp()函数用于内存扩展
        if ((err = av_reallocp(&out, total_size + padding)) < 0)
            return err;
        memcpy(out + total_size - unit_size - 4, nalu_header, 4);
        memcpy(out + total_size - unit_size, extradata + 2, unit_size);
        extradata += 2 + unit_size;
        pps:
        if (!unit_nb && !sps_done++) {
            unit_nb = *extradata++;//number of pps
            if (unit_nb) {
                pps_offset = total_size;
                pps_seen = 1;
            }
        }
    }
    if (out)
        memset(out + total_size, 0, padding);
    if (!sps_seen)
        assert("fail ");
    if (!pps_seen)
        assert("fail ");
    package_extradata->data = out;
    package_extradata->size = total_size;
    return length_size;
}


int
alloc_and_copy(AVPacket *out, const uint8_t *sps_pps, uint32_t sps_pps_size, const uint8_t *in,
               uint32_t in_size) {
    uint32_t offset = out->size;
    uint8_t nal_header_size = offset ? 3 : 4;
    int err;
    err = av_grow_packet(out, sps_pps_size + in_size + nal_header_size);
    if (err < 0)
        return err;
    if (sps_pps)
        memcpy(out->data + offset, sps_pps, sps_pps_size);
    memcpy(out->data + sps_pps_size + nal_header_size + offset, in, in_size);
    if (!offset) {
        AV_WB32(out->data + sps_pps_size, 1);

    } else {
        (out->data + offset + sps_pps_size)[0] =
        (out->data + offset + sps_pps_size)[1] = 0;
        (out->data + offset + sps_pps_size)[2] = 1;
    }



    return 0;
}


