@echo off
echo %~dp1
cd %~dp1
echo  %~nx1 ����ȴ�...
hprof-conv   %~nx1   conv.hprof
md conv
move conv.hprof conv/conv.hprof
cd conv
conv.hprof