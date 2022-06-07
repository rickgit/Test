import 'package:app_flutter/initial/CustomLocalizations.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'LocalAdapter.dart';
import 'Routes.dart';
import 'package:app_flutter/pages/HomePage.dart';

Widget createApp(){
  return MaterialApp(
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
