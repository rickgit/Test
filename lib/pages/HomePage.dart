import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class Body extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(body:Container(color: Colors.deepOrange,
      child: TextButton(child:Text("文本"),onPressed: (){
        print("object");
        Navigator.of(context).pushNamed("/user/info",arguments: {"":"a"});
      },),));
  }

}
Widget createDesktopPage(){
  return Body() ;
}