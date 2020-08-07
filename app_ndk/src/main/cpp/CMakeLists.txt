# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

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

target_link_libraries( # Specifies the target library.
        native-lib

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib}
        android
        stdc++
        c
        )