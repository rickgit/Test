
// import 'package:app_flutter/pages/user/LoginPage.dart';
// import 'package:app_flutter/pages/user/login/LoginPage.dart';
import 'package:app_flutter/initial/res_strings.dart';
import 'package:app_flutter/pages/user/login_page.dart';
import 'package:app_flutter/pages/user/login/login_page.dart';
import 'package:flutter/material.dart';
// import 'package:app_flutter/pages/UserInfoPage.dart';
// import 'package:app_flutter/pages/user/MessagePage.dart';
import 'package:app_flutter/pages/user_info_page.dart';
import 'package:app_flutter/pages/user/message_page.dart';
import 'package:flutter_smart_dialog/flutter_smart_dialog.dart';
import 'package:get/get.dart';
import 'package:get_it/get_it.dart';


String ROUT_TAG_INFO='/user/info';
String ROUT_TAG_MESSAGE='/user/message';
String ROUT_TAG_LOGIN='/user/login';
String ROUT_TAG_DIALOG='/dialog';
String ROUT_TAG_BOTTOM_SHEET='/ROUT_TAG_BOTTOM_SHEET';
String ROUT_TAG_General_Dialog_Page='/GeneralDialogPage';

Map<String,Widget Function(BuildContext)> pageRouteMap={
  ROUT_TAG_MESSAGE:  (BuildContext bc)=>createMessagePage(bc),
  // ROUT_TAG_INFO:  (BuildContext bc)=>createUserPage(bc),
  // ROUT_TAG_LOGIN:(BuildContext bc)=>createLoginPage(bc),
  ROUT_TAG_LOGIN:(BuildContext bc)=>createProviderLoginPage(bc),
  '/user/register':(BuildContext bc)=>Text(""),
  '/user/findpwd':(BuildContext bc)=>Text(""),
  // '/':(BuildContext bc)=> Scaffold(
  //   body:Padding(padding: EdgeInsets.only(top: 100),child: Text("命名路由"))),
  
};

Route<dynamic>? customRouteFactory(RouteSettings settings){
  if(settings.name==ROUT_TAG_DIALOG){
    return MaterialPageRoute(builder: (context){
        return DialogPage();
    });
  }else if(settings.name==ROUT_TAG_General_Dialog_Page){
    return MaterialPageRoute(builder: (context){
        return GeneralDialogPage();
    });
  }else if(settings.name==ROUT_TAG_BOTTOM_SHEET){
    return MaterialPageRoute(builder: (context){
        return BottomSheetPage();
    });
  }
  return MaterialPageRoute(builder: (context){
    return Padding(padding: EdgeInsets.only(top: 100),child: Text("命名路由"));
  });
}


class DialogPage extends StatelessWidget {
  const DialogPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(padding: EdgeInsets.only(top: 100),child: Column(children: [
      TextButton(onPressed: (){
        // showCustomDialog(context);
        // _createSmartDialog();
        Get.defaultDialog();
        Future.delayed(Duration(seconds: 5),()=>Get.showSnackbar(GetSnackBar(title:"message",message: "title")));
      }, child: Text("对话框"))
    ],),);
  }

  Future<dynamic> _createSmartDialog() {
    return SmartDialog.show(builder: (context){
          return ElevatedButton(onPressed: () {
            _createSmartDialog();
          },child: Text("对话框"),);
      });
  }

  Future<dynamic> showCustomDialog(BuildContext context) {
    return showDialog(context: context, builder: (context){
          return SimpleDialog(
            children: [ Text("对话框SimpleDialog"), Text("对话框SimpleDialog")],
            title:  ElevatedButton(onPressed: () {
              showCustomDialog(context);
             
            },
            child: Text("对话框"),),
            
          );
      });
  }
}

class GeneralDialogPage extends StatelessWidget {
  const GeneralDialogPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(padding: EdgeInsets.only(top: 100),child: Column(children: [
      TextButton(onPressed: (){
        showGeneralDialog(context: context, pageBuilder: (BuildContext context, Animation<double> animation, Animation<double> secondaryAnimation){
            return AlertDialog(
              content: Text("对话框 content"),
              actions: [MaterialButton(child: Text("对话框 actions"), onPressed: () {  },),MaterialButton(child: Text("对话框 actions"), onPressed: () {  },),],
              title:  ElevatedButton(onPressed: () {
                 Navigator.pop(context);
                Navigator.of(context).pushNamed(ROUT_TAG_BOTTOM_SHEET);
               
              },
              child: Text("对话框"),),
              
            );
        });},child: Text("对话框GeneralDialogPage"),),
       
    ],),);
  }
}

class BottomSheetPage extends StatelessWidget {
  const BottomSheetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(padding: EdgeInsets.only(top: 100),child: Column(children: [
        TextButton(onPressed: (){
          showModalBottomSheet( context: context,builder: ((context) {
            return Text("对话框 showBottomSheet");
          }));
          },child: Text("BottomSheetPage"),),
         
      ],),),
    );
  }
}