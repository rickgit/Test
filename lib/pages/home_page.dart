// import 'package:app_flutter/initial/CustomLocalizations.dart';
import 'package:app_flutter/initial/custom_localizations.dart';
import 'package:flutter/material.dart';

// import '../initial/ResSize.dart';
import 'package:app_flutter/initial/res_size.dart';
import '../initial/Routes.dart';

class Body extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(body:Column(
      children : [
        Container(child: Text(""),height: FONT_TITLE_HEIGHT,),
        TextButton(child:Text("登录"),onPressed: (){
          print("object");
          Navigator.of(context).pushNamed(ROUT_TAG_LOGIN,arguments: {"":"a"});
        },),
        TextButton(child:Text("消息"),onPressed: (){
          print("object");
          Navigator.of(context).pushNamed(ROUT_TAG_MESSAGE,arguments: {"":"a"});
        },),
      ]));
  }

}
Widget createDesktopPage(){
  return Body() ;
}