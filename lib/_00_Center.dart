import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  final bool a=false;
  @override
  Widget build(BuildContext context) {
    return buildCenter();
  }
}

Center buildCenter() {
  return Center(
    child:
    buildText(),
  );
}

Text buildText() {
  return Text('hello',
      textDirection: TextDirection.ltr,
      style: TextStyle(fontSize: 40,
          fontWeight: FontWeight.bold,
          color: Colors.deepOrange));
}