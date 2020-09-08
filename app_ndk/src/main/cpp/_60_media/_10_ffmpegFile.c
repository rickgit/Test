//
// Created by anshu-pc on 2020/9/7.
//

#include "libavformat/avio.h"

void testFfmpegFile() {
    AVIODirContext *s = NULL;
    AVIODirEntry *next=NULL;
    int ret = avio_open_dir(&s, "/data/data/edu.ptu.java.myapplication/lib/", NULL);///data/data/edu.ptu.java.myapplication/cache
    if (ret < 0)
        return;//error
    while (1) {
        ret = avio_read_dir(s, &next);
        if (ret<0||next==NULL)
            break;//error
        char *string = next->name;
        avio_free_directory_entry(&next);
    }
    avio_close_dir(&s);
    return;
}