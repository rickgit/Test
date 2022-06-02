import 'package:app_flutter/model/User.dart';
import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: buildListView(),
      theme: buildThemeData(),
    );
  }
}

ThemeData buildThemeData() => ThemeData(primaryColor: Colors.blue);

Scaffold buildScaffold() {
  return Scaffold(
    appBar: buildAppBar(),
    body: buildListView(),
  );
}

buildListView() {
  return ListView.builder(
    itemCount: userDatas.length,
    itemBuilder: (BuildContext ctxt, int index) {
      return buildListItem(index);
    },
  );
}
//需要返回具体类型
Text buildListItem(int index) {
  return Text(
        userDatas[index].desc!,
        style: TextStyle(color: Colors.brown));
}


AppBar buildAppBar() => AppBar(title: buildText(), elevation: 40.0);

Text buildText() {
  return Text('hello',
      textDirection: TextDirection.ltr,
      style: TextStyle(
          fontSize: 40, fontWeight: FontWeight.bold, color: Colors.green));
}
