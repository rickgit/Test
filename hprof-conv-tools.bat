@echo off
echo %~dp1
cd %~dp1
echo  %~nx1 £¬ÇëµÈ´ý...
hprof-conv   %~nx1   conv.hprof
md conv
move conv.hprof conv/conv.hprof
cd conv
conv.hprof