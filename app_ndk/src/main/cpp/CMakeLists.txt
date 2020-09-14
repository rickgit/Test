# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")# sdl需要支持nullptr ，添加c++11

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        native-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        native-lib.cpp

        jnitest/jnitest.cpp
        jnitest/jnitest.h

        pip_test.cpp
        pip_test.h

        base_type.cpp
        base_type.h

        _10_ctest/hello.c
        _10_ctest/hello.h
        _10_ctest/_00_ctypetest.c
        _10_ctest/_00_ctypetest.h
        _10_ctest/_01_asm.c
        _10_ctest/_01_asm.h
        _10_ctest/_02_storage.h
        _10_ctest/_02_storage.c
        _10_ctest/_50_thread_test.c
        _10_ctest/_50_thread_test.h
        _10_ctest/_30_file_test.c
        _10_ctest/_30_file_test.h
        _10_ctest/_31_SysStatTest.c
        _10_ctest/_32_accessTest.c


        _05_microsystest/microsystest.c
        _05_microsystest/microsystest.h

        cpptest/testCppSuit.h
        cpptest/testCppSuit.cpp
#                cpptest/_30_ooad/_00_class.h
#                cpptest/_30_ooad/_00_class.cpp

                cpptest/_10_storage/_10_DataTypeTest.h
                cpptest/_10_storage/_10_DataTypeTest.cpp
                cpptest/_10_storage/_11_SpecialTypeTest.cpp
                cpptest/_10_storage/_11_SpecialTypeTest.h

        _60_media/_00_ffmpegTest.c
        _60_media/_10_ffmpegFile.c
        _60_media/_20_ffmpeg_metadata.c
        _60_media/_20_ffmpegaudio.c
        _60_media/_20_ffmpegvideo.c
        _60_media/_50_sdl.c
        #
        #        cpptest/_30_ooad/_00_class.cpp
        #        cpptest/_30_ooad/_00_class.h
        #        cpptest/_30_ooad/_10_extendsTest.h
        #        cpptest/_30_ooad/_10_extendsTest.cpp
        #        cpptest/_30_ooad/_20_dynamic.cpp
        #        cpptest/_30_ooad/_20_dynamic.h
        #        giflib/dgif_lib.c
        #        giflib/gif_err.c
        #        giflib/gif_hash.h
        #        giflib/gif_lib.h
        #        giflib/gif_lib_private.h
        #        giflib/gifalloc.c
        )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)
#SET(CMAKE_SYSROOT D:\\Program\\Android\\android-ndk-r16b\\sysroot\\usr)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
#include_directories(
#        ${CMAKE_SOURCE_DIR}/src/main/cpp/include #h文件目录
##        ${CMAKE_SOURCE_DIR}/libs/include
#)
include_directories(
        ${CMAKE_SOURCE_DIR}/include

)
add_subdirectory(SDL2)
#$ANDROID_SDK/cmake/<cmake-version>/share/cmake-<version>/Modules/AndroidNdkModules.cmake
#D:\Program\Android\sdk-ndk\ndk\21.3.6528147\sources\android\cpufeatures
#${ANDROID_NDK}/sources/android/cpufeatures


add_library( avcodec
        SHARED
        IMPORTED)
set_target_properties( avcodec
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libavcodec.so)
add_library( avdevice
        SHARED
        IMPORTED)
set_target_properties( avdevice
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libavdevice.so)
add_library( avfilter
        SHARED
        IMPORTED)
set_target_properties( avfilter
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libavfilter.so)
add_library( avformat
        SHARED
        IMPORTED)
set_target_properties( avformat
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libavformat.so)
add_library( avutil
        SHARED
        IMPORTED)
set_target_properties( avutil
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libavutil.so)
add_library( swresample
        SHARED
        IMPORTED)
set_target_properties( swresample
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libswresample.so)
add_library( swscale
        SHARED
        IMPORTED)
set_target_properties( swscale
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libswscale.so)

target_link_libraries( # Specifies the target library.
        native-lib
        ##ffmpeg
        avcodec
        avdevice
        avfilter
        avformat
        avutil
        swresample
        swscale
        ## sdl
        SDL2
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib}
        android
        stdc++
        c     #D:\Program\Android\sdk-ndk\ndk\21.3.6528147\sysroot\usr\lib\x86_64-linux-android
        )