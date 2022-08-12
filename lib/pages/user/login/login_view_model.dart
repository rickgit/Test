import 'package:app_flutter/base/BaseRepo.dart';
import 'package:app_flutter/base/BaseViewModel.dart';
import 'package:app_flutter/pages/user/login/login_repo.dart';
import 'package:flutter/cupertino.dart';

import '../../../components/toast_util.dart';

class LoginViewModel extends BaseViewModel<LoginRepo>{
  String name="";
  String pwd="";
  bool stateShowSecure=false;
  changeShowSecure(){
    stateShowSecure=!stateShowSecure;
    notifyListeners();
  }
  @override
  LoginRepo initBaseRepo() {
     return LoginRepo();
  }

  void login(BuildContext context){
    ToastUtil.show("message");
    Future.delayed(Duration(seconds: 2), () {
      print("Duration ...");
      ToastUtil.dismiss();
      Navigator.of(context)
          .pushNamed('/user/message', arguments: {"": "a"});
    });
  }
}