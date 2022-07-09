import 'package:app_flutter/initial/CustomLocalizations.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'ResString.dart';
import 'Routes.dart';
import 'package:app_flutter/pages/HomePage.dart';

Widget createApp(){
  return MaterialApp(
    theme: ThemeData(splashColor: Colors.transparent, // 点击时的高亮效果设置为透明
      highlightColor: Colors.transparent, // 长按时的扩散效果设置为透明
      ),
    debugShowCheckedModeBanner: false,
    initialRoute: '/',
    routes: pageRouteMap,
    localizationsDelegates:localizationsDelegates(),
    supportedLocales: supportedLocales(),
    // onGenerateRoute: (RouteSettings settings)=>RouteFactory(settings),
    home: createDesktopPage(),
    //   localizationsDelegates:[
    //   GlobalMaterialLocalizations.delegate,
    //   GlobalCupertinoLocalizations.delegate,
    //   GlobalWidgetsLocalizations.delegate,],
    // supportedLocales: [Locale('en'),Locale('zh')],


  );
}
