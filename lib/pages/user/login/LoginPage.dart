import 'dart:math';

import 'package:app_flutter/base/BaseState.dart';
import 'package:app_flutter/components/Toast.dart';
import 'package:app_flutter/initial/CustomLocalizations.dart';
import 'package:app_flutter/pages/user/login/LoginViewModel.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:provider/provider.dart';
import '../../../initial/ResString.dart';

Widget createProviderLoginPage(BuildContext context) {
  // return ProviderWidget();
  return LoginPage();
}
class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends BaseState<LoginPage, LoginViewModel> {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<LoginViewModel>(
        create: ((context) {
          return mViewModel;
        }), child: LoginWidget() );
  }

  @override
  LoginViewModel initViewModel() {
    return LoginViewModel();
  }
}
class ProviderWidget extends StatefulWidget {
  const ProviderWidget({Key? key}) : super(key: key);

  @override
  State<ProviderWidget> createState() => _ProviderWidgetState();
}

class _ProviderWidgetState extends BaseState<ProviderWidget,LoginViewModel> {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<LoginViewModel>(create: ((context) {
     return mViewModel;
    }),child:LoginWidget());//ChangeNotifierProvider child必须是一个widget
  }
  
  @override
  LoginViewModel initViewModel() {
     return LoginViewModel();
  }
}
class LoginWidget extends StatelessWidget {
  const LoginWidget({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    context.read<LoginViewModel>();
    return Scaffold(
            resizeToAvoidBottomInset: false,
            body: Container(
              color: Color.fromARGB(1, 5, 36, 78),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Padding(
                      padding: EdgeInsets.only(top: 171),
                      child: Icon(Icons.account_circle)),
                  Padding(
                    padding: EdgeInsets.only(left: 30, right: 30, top: 51),
                    child: createAccountEditWidget(),
                  ),
                  Padding(
                    padding: EdgeInsets.only(left: 30, right: 30, top: 10),
                    child: pwdWidget(),
                  ),
                  Container(
                    child: ElevatedButton(
                      onPressed: () {
                        //登录
                        context.read<LoginViewModel>().login(context);
                      },
                      child: Text("${ResString.of(context)?.sign_in}"),
                      style: ButtonStyle(
                          elevation:
                              MaterialStateProperty.all<double>(0), //按压阴影
                          //backgroundColor: MaterialStateProperty.all<Color>(Colors.green), 背景颜色
                          overlayColor: MaterialStateProperty.all<Color>(
                              Colors.transparent), //去掉水波纹
                          shape: MaterialStateProperty.all(
                              RoundedRectangleBorder(
                                  borderRadius: BorderRadius.circular(30)))),
                    ),
                    width: double.infinity,
                    height: 60,
                    padding: EdgeInsets.only(left: 30, right: 30, top: 15),
                  ),
                  Padding(
                    padding: EdgeInsets.only(left: 30, right: 30, top: 10),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.end,
                      children: [
                        TextButton(
                            onPressed: () {
                              print("regist");
                            },
                            style: ButtonStyle(
                              overlayColor:
                                  MaterialStateProperty.all(Colors.transparent),
                            ),
                            child: Text("${ResString.of(context)?.sign_up}")),
                        Container(
                          width: 10,
                          child: TextButton(onPressed: () {}, child: Text("|")),
                        ),
                        TextButton(
                            onPressed: () {
                              print("foget_password");
                            },
                            style: ButtonStyle(
                              overlayColor:
                                  MaterialStateProperty.all(Colors.transparent),
                            ),
                            child: Text(
                                "${ResString.of(context)?.foget_password}")),
                      ],
                    ),
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
            ));
  }
  Widget pwdWidget1() {
    return Consumer(builder: (context, value, child) => TextField(
                        obscureText: context.select<LoginViewModel,bool>(((value) {
                          return value.stateShowSecure;
                        })),
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
                          suffixIcon: IconButton(
                            icon: Icon(context.read<LoginViewModel>().stateShowSecure
                                ? Icons.remove_red_eye
                                : Icons.remove_red_eye_outlined),
                            color: Colors.blue,
                            onPressed: () {
                               context.read<LoginViewModel>().changeShowSecure();
                            },
                          ),
                        )));
  }
  Builder pwdWidget() {
    return Builder (builder: (context) =>   TextField(
                        obscureText: context.select<LoginViewModel,bool>(((value) {
                          return value.stateShowSecure;
                        })),
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
                          suffixIcon: IconButton(
                            icon: Icon(context.read<LoginViewModel>().stateShowSecure
                                ? Icons.remove_red_eye
                                : Icons.remove_red_eye_outlined),
                            color: Colors.blue,
                            onPressed: () {
                               context.read<LoginViewModel>().changeShowSecure();
                            },
                          ),
                        )),
                  );
  }
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
  TapGestureRecognizer tapGestureRecognizer = TapGestureRecognizer();

  @override
  void dispose() {
    tapGestureRecognizer.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return RichText(
      text: TextSpan(
          style: TextStyle(color: Colors.black45),
          text: "${ResString.of(context)?.receive_the}",
          children: [
            TextSpan(
                text: "${ResString.of(context)?.user_agreement}",
                style: TextStyle(color: Colors.blue),
                recognizer: tapGestureRecognizer
                  ..onTap = () {
                    setState(() {
                      print(
                          "跳转协议${Random().nextInt(10)} ${ResString.of(context)!.helloWorld}");
                      print(
                          "跳转协议${Random().nextInt(10)} ${findString(context, "title")}");
                      Toast.showToast(
                          "点击 ${ResString.of(context)?.user_agreement}");
                    });
                  })
          ]),
    );
  }
}

createAccountEditWidget() {
  return AccountEditWidget();
}

class AccountEditWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => AccountState();
}

class AccountState extends State<AccountEditWidget> {
  @override
  Widget build(BuildContext context) {
    return TextField(
        keyboardType: TextInputType.emailAddress,
        decoration: InputDecoration(
          border: OutlineInputBorder(
              borderRadius: BorderRadius.all(Radius.circular(30))),
          hintText: "${ResString.of(context)?.account}",
          focusedBorder: OutlineInputBorder(
            borderSide: new BorderSide(color: Colors.blue),
            borderRadius: new BorderRadius.circular(30),
          ),
          enabledBorder: OutlineInputBorder(
            borderSide: new BorderSide(color: Colors.blue),
            borderRadius: new BorderRadius.circular(30),
          ),
          suffixIcon: IconButton(
            icon: Icon(Icons.keyboard_arrow_down),
            color: Colors.blue,
            onPressed: () {},
          ),
        ));
  }
}

 
class CheckStatefulWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => CheckState();
}

class CheckState extends State<CheckStatefulWidget> {
  bool stateCheck = false;

  @override
  Widget build(BuildContext context) {
    return Checkbox(
      checkColor: Colors.white,
      fillColor: MaterialStateProperty.resolveWith(getColor),
      value: stateCheck,
      onChanged: (bool? value) {
        setState(() {
          stateCheck = !stateCheck;
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
