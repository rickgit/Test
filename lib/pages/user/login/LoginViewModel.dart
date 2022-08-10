import 'package:app_flutter/base/BaseRepo.dart';
import 'package:app_flutter/base/BaseViewModel.dart';
import 'package:app_flutter/pages/user/login/LoginRepo.dart';

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
}