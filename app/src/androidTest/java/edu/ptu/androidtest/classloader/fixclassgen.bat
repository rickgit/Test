mkdir edu
cd edu
mkdir ptu
cd ptu
mkdir test
cd test
echo package edu.ptu.test;public class FixBean{boolean fix=true;} > FixBean.java
cd ../../..
set PATH=D:\Program\Android\android-studio-3.4.2\jre\bin;D:\Program\Android\sdk-2\build-tools\28.0.3;%PATH%

mkdir out
javac -source 1.8 -target 1.8 -d out edu/ptu/test/FixBean.java

::rem jar cvf fix.jar -C out/ .

::rem 含有classes.dex的fix_dex.jar
call dx --dex --output=fix_dex.jar out

rmdir /s /q edu
rmdir /s /q out