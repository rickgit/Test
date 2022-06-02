import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,//去掉右上角debug标识
      home: buildScaffold(),
      theme: buildThemeData(),
    );
  }
}

ThemeData buildThemeData() => ThemeData(primaryColor: Colors.white);

Scaffold buildScaffold() {
  return Scaffold(
    appBar: buildAppBar(),
  );
}

AppBar buildAppBar() {
  return AppBar(
      title: buildText(),
      actions: <Widget>[  //右上角action
        Icon(Icons.home)
      ],
      elevation: 40.0);
}

Text buildText() {
  return Text('hello',
      textDirection: TextDirection.ltr,
      style: TextStyle(
          fontSize: 40, fontWeight: FontWeight.bold, color: Colors.deepOrange));
}
