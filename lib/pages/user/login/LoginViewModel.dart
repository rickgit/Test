import 'package:app_flutter/base/BaseRepo.dart';
import 'package:app_flutter/base/BaseViewModel.dart';
import 'package:app_flutter/pages/user/login/LoginRepo.dart';
import 'package:flutter/cupertino.dart';

import '../../../components/Toast.dart';

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
    Toast.show("message");
    Future.delayed(Duration(seconds: 2), () {
      print("Duration ...");
      Toast.dismiss();
      Navigator.of(context)
          .pushNamed('/user/message', arguments: {"": "a"});
    });
  }
}