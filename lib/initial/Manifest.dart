


import 'package:flutter/material.dart';
import 'package:app_flutter/pages/UserInfoPage.dart';

Map<String,Widget Function(BuildContext)> pageRouteMap={
  '/user/info':  (BuildContext bc)=>createUserPage(bc),
  '/user/login':(BuildContext bc)=>Text(""),
  '/user/register':(BuildContext bc)=>Text(""),
  '/user/findpwd':(BuildContext bc)=>Text(""),
};