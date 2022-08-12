import 'dart:math';

import 'package:app_flutter/components/toast_util.dart';
import 'package:app_flutter/initial/CustomLocalizations.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import '../../initial/res_strings.dart';

Widget createLoginPage(BuildContext context) {
  return Scaffold(
      resizeToAvoidBottomInset: false,
      body: Container(
        color: Color.fromARGB(1, 5, 36, 78),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Padding( padding: EdgeInsets.only(top: 171),child:  Icon(Icons.account_circle)),
            Padding(padding: EdgeInsets.only(left: 30,right: 30,top: 51),child:
              createAccountEditWidget(),
            ),
            Padding(padding: EdgeInsets.only(left: 30,right: 30,top: 10),
              child: createPasswdWidget(),),
            Container(child: ElevatedButton(onPressed: () {
              //登录
              ToastUtil.show("message");
              Future.delayed(Duration(seconds: 2),(){
                print("Duration ...");
                ToastUtil.dismiss();
                Navigator.of(context).pushNamed('/user/message',arguments: {"":"a"});
              });

            }, 
            child: Text("${ResString.of(context)?.sign_in}") ,
            
            style: ButtonStyle(
              elevation: MaterialStateProperty.all<double>(0),//按压阴影
               //backgroundColor: MaterialStateProperty.all<Color>(Colors.green), 背景颜色
               overlayColor: MaterialStateProperty.all<Color>(Colors.transparent), //去掉水波纹
               shape: MaterialStateProperty.all(
                RoundedRectangleBorder(
                    borderRadius:
                    BorderRadius.circular(
                        30))) ),),
              width: double.infinity,height: 60,
              padding: EdgeInsets.only(left: 30,right: 30,top: 15),),
            Padding(padding: EdgeInsets.only(left: 30,right: 30,top: 10),
              child:
              Row(mainAxisAlignment: MainAxisAlignment.end,children: [
                TextButton(onPressed: (){print("regist");},
                 style: ButtonStyle( overlayColor: MaterialStateProperty.all(Colors.transparent),),
                child: Text("${ResString.of(context)?.sign_up}")),
                Container(width: 10, child: TextButton(onPressed: (){}, child: Text("|")),),
                TextButton(onPressed: (){print("foget_password");},
                style: ButtonStyle( overlayColor: MaterialStateProperty.all(Colors.transparent),),
                 child: Text("${ResString.of(context)?.foget_password}")),
              ],),
            ),
            Spacer(),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                CheckStatefulWidget(),
                createAgreeStatefullWidget(),
              ],
            )
          ],
        ),
      )
  );
}

createAgreeStatefullWidget() {
  return AgressTextStatefullWidget();
}

class AgressTextStatefullWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return AgressTextState();
  }
}
class AgressTextState extends State<AgressTextStatefullWidget> {
  TapGestureRecognizer tapGestureRecognizer=TapGestureRecognizer();

  @override
  void dispose() {
    tapGestureRecognizer.dispose();
    super.dispose();

  }

  @override
  Widget build(BuildContext context) {
    return RichText(text: TextSpan(
        style: TextStyle(color: Colors.black45),
        text: "${ResString.of(context)?.receive_the}"
        ,children:[
      TextSpan(text: "${ResString.of(context)?.user_agreement}",
          style: TextStyle(color: Colors.blue)
          ,recognizer: tapGestureRecognizer
            ..onTap = (){
              setState(() {
                print("跳转协议${Random().nextInt(10)} ${ResString.of(context)!.helloWorld}");
                print("跳转协议${Random().nextInt(10)} ${findString(context, "title")}");
                ToastUtil.showToast("点击 ${ResString.of(context)?.user_agreement}");
              });
            }
      )]
    ),);
  }
}
createAccountEditWidget() {
  return AccountEditWidget ();
}

class AccountEditWidget extends StatefulWidget{
  @override
  State<StatefulWidget> createState()=>AccountState();
}

class AccountState extends State<AccountEditWidget> {
  @override
  Widget build(BuildContext context) {
    
    return TextField(
        keyboardType:TextInputType.emailAddress,
        decoration: InputDecoration(
          border: OutlineInputBorder(borderRadius: BorderRadius.all(Radius.circular(30)) ),
          hintText:"${ResString.of(context)?.account}",
          focusedBorder: OutlineInputBorder(
                    borderSide: new BorderSide(color: Colors.blue),
                    borderRadius: new BorderRadius.circular(30),
                  ),
          enabledBorder: OutlineInputBorder(
                    borderSide: new BorderSide(color: Colors.blue),
                    borderRadius: new BorderRadius.circular(30),
                  ),
          suffixIcon: IconButton(icon:Icon(Icons.keyboard_arrow_down),
          color:   Colors.blue, onPressed: (){

          },),
        ));
  }
}

Widget createPasswdWidget() {
  return PwdStatefulWidget();
}
class PwdStatefulWidget extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => PwdState();
}

class PwdState  extends State<PwdStatefulWidget>{
  bool stateShowSecure=false;

  @override
  Widget build(BuildContext context) {
    return TextField(
        obscureText: stateShowSecure,
        decoration: InputDecoration(
          border: OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(30)),
          ),
          hintText: "${ResString.of(context)?.password}",
                    focusedBorder: OutlineInputBorder(
                    borderSide: new BorderSide(color: Colors.blue),
                    borderRadius: new BorderRadius.circular(30),
                  ),
          enabledBorder: OutlineInputBorder(
                    borderSide: new BorderSide(color: Colors.blue),
                    borderRadius: new BorderRadius.circular(30),
                  ),
          suffixIcon: IconButton(icon: Icon(stateShowSecure?Icons.remove_red_eye:Icons.remove_red_eye_outlined),
           color:   Colors.blue,onPressed: (){
            setState(() {
              stateShowSecure=!stateShowSecure;
            });
          },),
        ));
  }
}

class CheckStatefulWidget extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => CheckState();
}
class CheckState extends State<CheckStatefulWidget>{
  bool stateCheck=false;

  @override
  Widget build(BuildContext context) {
    return  Checkbox(
      checkColor: Colors.white,
      fillColor: MaterialStateProperty.resolveWith(getColor),
      value: stateCheck,
      onChanged: (bool? value) {
        setState(() {
          stateCheck=!stateCheck;
        });
      },
    );
  }
}

Color getColor(Set<MaterialState> states) {
  const Set<MaterialState> interactiveStates = <MaterialState>{
    MaterialState.pressed,
    MaterialState.hovered,
    MaterialState.focused,
  };
  if (states.any(interactiveStates.contains)) {
    return Colors.blue;
  }
  return Colors.black45;
}
class LoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
      children: [Icon(Icons.account_circle)],
    );
  }
}
