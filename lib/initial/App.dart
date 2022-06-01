import 'package:flutter/material.dart';
import 'Routes.dart';
import 'package:app_flutter/pages/HomePage.dart';
Widget createApp(){
  return MaterialApp(
    initialRoute: '/',
    routes: pageRouteMap,
    // onGenerateRoute: (RouteSettings settings)=>RouteFactory(settings),
    home: createDesktopPage(),
  );
}
