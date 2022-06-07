import 'dart:math';

import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

Widget createLoginPage() {
  return Scaffold(
      resizeToAvoidBottomInset: false,
      body: Container(
        color: Color.fromARGB(1, 46, 66, 100),
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
              //获取输入框控件
            }, child: Text("Sign in") ,style: ButtonStyle( shape: MaterialStateProperty.all(
                RoundedRectangleBorder(
                    borderRadius:
                    BorderRadius.circular(
                        30))) ),),
              width: double.infinity,height: 60,
              padding: EdgeInsets.only(left: 30,right: 30,top: 15),),
            Padding(padding: EdgeInsets.only(left: 30,right: 30,top: 10),
              child:
              Row(mainAxisAlignment: MainAxisAlignment.end,children: [
                TextButton(onPressed: (){}, child: Text("Sign up")),
                Container(width: 10, child: TextButton(onPressed: (){}, child: Text("|")),),
                TextButton(onPressed: (){}, child: Text("Forgot passwod")),
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
  Widget build(BuildContext context) {
    return RichText(text: TextSpan(
        style: TextStyle(color: Colors.black45),
        text: "I have read and agreed to the "
        ,children:[
      TextSpan(text: "User Agreement",
          style: TextStyle(color: Colors.blue)
          ,recognizer: tapGestureRecognizer
            ..onTap = (){
              setState(() {
                print("跳转协议${Random().nextInt(10)}");
              });
            }
      )]
    ),);
  }
  @override
  void dispose() {
    tapGestureRecognizer.dispose();
    super.dispose();

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
          hintText: 'username@gmail.com',
          suffixIcon: IconButton(icon:Icon(Icons.keyboard_arrow_down),onPressed: (){

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
          hintText: '1234',
          suffixIcon: IconButton(icon: Icon(stateShowSecure?Icons.remove_red_eye:Icons.remove_red_eye_outlined),onPressed: (){
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
