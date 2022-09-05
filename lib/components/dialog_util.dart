import 'package:app_flutter/components/toast_util.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:flutter_smart_dialog/flutter_smart_dialog.dart';



class DialogUtil {
  static dynamic observer =[FlutterSmartDialog.observer];
  static TransitionBuilder builder =FlutterSmartDialog.init(builder: EasyLoading.init());

  void showMessage(BuildContext context){
      // showDialog(context: context, builder: builder);
    SmartDialog.show(
      alignment: Alignment.centerRight,
      usePenetrate: true,
      clickMaskDismiss: false,
      builder: (_) {
        return Container(
          width: 80,
          height: double.infinity,
          color: Colors.amber,
        );
      },
    ); 
  }

  void showChoice(){

  }
  void showPopWindow(BuildContext context){
    SmartDialog.showAttach(
      targetContext: context,
      builder: (_) => Container(width: 100, height: 100, color: Colors.red),
    );
  }

  void showLoading(){
    ToastUtil.show("");
  }

  void showToast(String message){
    if(message==null)
      return;
    ToastUtil.showToast(message);
  }

}