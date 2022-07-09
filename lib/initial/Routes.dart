
import 'package:app_flutter/pages/user/LoginPage.dart';
import 'package:flutter/material.dart';
import 'package:app_flutter/pages/UserInfoPage.dart';
import 'package:app_flutter/pages/user/MessagePage.dart';

String ROUT_TAG_INFO='/user/info';
String ROUT_TAG_MESSAGE='/user/message';
String ROUT_TAG_LOGIN='/user/login';
Map<String,Widget Function(BuildContext)> pageRouteMap={
  ROUT_TAG_MESSAGE:  (BuildContext bc)=>createMessagePage(bc),
  ROUT_TAG_INFO:  (BuildContext bc)=>createUserPage(bc),
  ROUT_TAG_LOGIN:(BuildContext bc)=>createLoginPage(bc),
  '/user/register':(BuildContext bc)=>Text(""),
  '/user/findpwd':(BuildContext bc)=>Text(""),
};