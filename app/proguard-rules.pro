# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Haibin\Downloads\anshu\software\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#增量混淆
#作用就是复用上次的mapping映射，让ProgramMember的visitorInfo恢复到上次混淆的状态。
#由于R8配合Proguard规则进行混淆，需要禁用R8
#-applymapping mapping.txt

