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
  return LoginPage();
}

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends BaseState<LoginPage, LoginViewModel> {
  initViewModel() {
    return LoginViewModel();
  }

  @override
  Widget build(BuildContext buildContext) {
    return providerwidget(buildContext);
  }

  ChangeNotifierProvider<LoginViewModel> providerwidget(BuildContext context) {
    return ChangeNotifierProvider<LoginViewModel>(
        create: ((context) => mViewModel),
        child: MaterialApp(
            home: Scaffold(
                resizeToAvoidBottomInset: false,
                body: Text(context
                    .read<LoginViewModel>()
                    .stateShowSecure
                    .toString()))));
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

// class LoginPage extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     return Row(
//       children: [Icon(Icons.account_circle)],
//     );
//   }
// }
