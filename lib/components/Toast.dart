import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';

//支持show dismiss showToast showSuccess show Error showInfo showProgress
class Toast {
  static toastInit() {
    EasyLoading.init();
  }

  static toastConf() {
    //自定义show
    EasyLoading.instance
      ..loadingStyle = EasyLoadingStyle.custom //自定义颜色，底下配置颜色才会生效
      ..maskType = EasyLoadingMaskType.black
      ..indicatorType = EasyLoadingIndicatorType.wave
      ..userInteractions = false //不可交互
      ..dismissOnTap = false
      ..indicatorSize = 45.0
      ..displayDuration = const Duration(milliseconds: 2000)
      ..radius = 10.0
      ..progressColor = Colors.blue
      ..backgroundColor = Colors.blue.withOpacity(0.5)
      ..indicatorColor = Colors.blue.withOpacity(0.5)
      ..textColor = Colors.blue
      ..maskColor = Colors.blue.withOpacity(0.5)
      ;
    // ..customAnimation = CustomAnimation();
  }

  static show(String message) {
    EasyLoading.instance..loadingStyle = EasyLoadingStyle.custom;
    EasyLoading.show(status: 'loading...');
  }

  static showToast(String message) {
    EasyLoading.instance..loadingStyle = EasyLoadingStyle.dark;
    EasyLoading.showToast(message);
  }

  static dismiss() {
    EasyLoading.dismiss();
  }
}
