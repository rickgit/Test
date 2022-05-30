import 'package:flutter/material.dart';
import 'Manifest.dart';
import 'package:app_flutter/pages/HomePage.dart';
Widget createApp(){
  return MaterialApp(
    initialRoute: '/',
    routes: pageRouteMap,
    // onGenerateRoute: (RouteSettings settings)=>RouteFactory(settings),
    home: createDesktopPage(),
  );
}
