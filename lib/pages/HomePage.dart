import 'package:app_flutter/initial/CustomLocalizations.dart';
import 'package:flutter/material.dart';

class Body extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(body:Container(color: Colors.deepOrange,
      child: TextButton(child:Text("標題"),onPressed: (){
        print("object");
        Navigator.of(context).pushNamed('/user/login',arguments: {"":"a"});
      },),));
  }

}
Widget createDesktopPage(){
  return Body() ;
}