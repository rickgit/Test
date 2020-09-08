//
// Created by anshu-pc on 2020/9/7.
//


#include <libavformat/avformat.h>



void adts_header(char *adtsHeader,int datalen){
    int audio_obj_type=2;
    int sampling_freq_index=7;
    int channel_conf=2;
    int adtsLen=datalen+7;;
    adtsHeader[0]=0xff;//syncword0xfff
    adtsHeader[1]=0xf0;//syncword
    adtsHeader[1]|=0<<3;//mpeg version
    adtsHeader[1]|=0<<1;//layer
    adtsHeader[1]|=1;//protection absent 1bit

    adtsHeader[2]=(audio_obj_type-1)<<6;//profile 2bit
    adtsHeader[2]|=(sampling_freq_index&0x0f)<<2;//  4bit
    adtsHeader[2]|=(0)<<1;//  1bit
    adtsHeader[2]|=(channel_conf&0x04)>>2;//  1bit

    adtsHeader[3]=(channel_conf&0x03)<<6;//channel conf
    adtsHeader[3]|=0<<5;//original 0
    adtsHeader[3]|=0<<4;//home
    adtsHeader[3]|=0<<3;//copyright id bit 1bit
    adtsHeader[3]|=0<<2;//1bit
    adtsHeader[3]|=(adtsLen&0x1800)>>11;//高2bit
    adtsHeader[4]=(uint8_t)((adtsLen&0x7f8)>>3);//8bit
    adtsHeader[5]=(uint8_t)((adtsLen&0x7)<<5);//低3bit
    adtsHeader[5]|=0x1f;//高5bit
    adtsHeader[6]=0xfc;//

}
void testAudioStream() {
    AVFormatContext *ps = NULL;
    av_register_all();
    int ret = avformat_open_input(&ps, "/data/data/edu.ptu.java.myapplication/files/out_m.mp4",
                                  NULL, NULL);

    if (ret < 0)
        return;//error
    ret = av_find_best_stream(ps, AVMEDIA_TYPE_AUDIO, -1, -1, NULL, 0);
    if (ret < 0)
        return;//error
        int audio_index=ret;
    AVPacket pkt;
    av_init_packet(&pkt);
    FILE* dst_fd=fopen("/data/data/edu.ptu.java.myapplication/files/audio.aac","wb");
    char adts_header_buf[7];
    while (av_read_frame(ps,&pkt)>=0){
        if (pkt.stream_index==audio_index){
            uint8_t *datas = pkt.data;
            adts_header(adts_header_buf,pkt.size);
            fwrite(adts_header_buf,1,7,dst_fd);
            size_t len = fwrite(pkt.data, 1, pkt.size, dst_fd);
            if (len!=pkt.size){
                //error
            }
        }
        av_packet_unref(&pkt);//adts头，采样数，声道数
    }

    av_dump_format(ps, 0, "/data/data/edu.ptu.java.myapplication/files/out_m.mp4", 0);

    if (dst_fd){
        fclose(dst_fd);
    }

    avformat_close_input(&ps);
    return;
}