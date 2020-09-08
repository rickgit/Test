//
// Created by anshu-pc on 2020/9/7.
//


#include <libavformat/avformat.h>


void toannexb(AVFormatContext *pContext, AVPacket *pPacket, FILE *pFile);

int
alloc_and_copy(AVPacket *out, const uint8_t *sps_pps, uint32_t sps_pps_size, const uint8_t * in,
               uint32_t in_size);

void testVideoStream() {
    AVFormatContext *ps = NULL;
    av_register_all();
    int ret = avformat_open_input(&ps, "/data/data/edu.ptu.java.myapplication/files/out_m.mp4",
                                  NULL, NULL);

    if (ret < 0)
        return;//error
    ret = av_find_best_stream(ps, AVMEDIA_TYPE_AUDIO, -1, -1, NULL, 0);
    if (ret < 0)
        return;//error
    int video_index=ret;
    AVPacket pkt;
    av_init_packet(&pkt);
    FILE* dst_fd=fopen("/data/data/edu.ptu.java.myapplication/files/video.mp4","wb");
//    char adts_header_buf[7];
    while (av_read_frame(ps,&pkt)>=0){
        if (pkt.stream_index==video_index){
//            uint8_t *datas = pkt.data;
//            fwrite(adts_header_buf,1,7,dst_fd);
//            size_t len = fwrite(pkt.data, 1, pkt.size, dst_fd);
//            if (len!=pkt.size){
//                //error
//            }
            toannexb(ps,&pkt,dst_fd);//adts头
        }
        av_packet_unref(&pkt);
    }

    av_dump_format(ps, 0, "/data/data/edu.ptu.java.myapplication/files/out_m.mp4", 0);

    if (dst_fd){
        fclose(dst_fd);
    }

    avformat_close_input(&ps);
    return;
}

int toannexb(AVFormatContext *pContext, AVPacket *pPacket, FILE *pFile) {
    AVPacket*out=NULL;
    AVPacket spspps_pkt;
    int len;
    uint8_t  unit_type;
    int32_t  nal_size;
    uint32_t  cumul_size=0;
    const uint8_t  *buf;
    const uint8_t  *buf_end;
    int buf_size;
    int ret=0,i;

    out=av_packet_alloc();
    buf=pPacket->data;
    buf_size=pPacket->size;
    buf_end=pPacket->data+pPacket->size;

    do{
        ret=AVERROR(EINVAL);
        if(buf+4>buf_end){
            goto fail;
        }
        for (  nal_size = 0,i=0; i < 4; ++i) {
            nal_size=(nal_size<<8)|buf[i];
        }
        buf+=4;
        unit_type=*buf&0x1f;
        if (nal_size>buf_end-buf||nal_size<0)
            goto fail;
        if (unit_type==5){
            //关键帧
            h264_extradata_to_annext(pContext->streams[pPacket->stream_index]->codec->extradata,
                    pContext->streams[pPacket->stream_index]->codec->extradata_size,
                    &spspps_pkt,
                    AV_INPUT_BUFFER_PADDING_SIZE);
            if ((ret=alloc_and_copy(out,spspps_pkt.data,spspps_pkt.size,buf,nal_size))<0)
                goto fail;
        } else{
            if ((ret=alloc_and_copy(out,NULL,0,buf,nal_size))<0)
                goto fail;
        }
        len=fwrite(out->data,1,out->size,pFile);
        if (len!=out->size){
            //log
        }
        fflush(pFile);
        next_nal:
        buf+=nal_size;
        cumul_size+=nal_size+4;
    }while (cumul_size<buf_size);
    fail:
    av_packet_free(&out);
    return ret;
}

int
alloc_and_copy(AVPacket *out, const uint8_t *sps_pps, uint32_t sps_pps_size, const uint8_t * in,
               uint32_t in_size) {
    uint32_t  offset=out->size;
    uint8_t  nal_header_size=offset?3:4;
    int err;
    err=av_grow_packet(out,sps_pps_size+in_size+nal_header_size);
    if (err<0)
    return err;
    if (sps_pps)
        memcpy(out->data+offset,sps_pps,sps_pps_size);
    memcpy(out->data+sps_pps_size+nal_header_size+offset,in,in_size);

//    ...
    return 0;
}


