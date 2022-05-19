import 'package:app_flutter/model/User.dart';
import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: buildScaffold(),
      theme: buildThemeData(),
    );
  }
}

ThemeData buildThemeData() => ThemeData(primaryColor: Colors.blueAccent);

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
      return buildListItem(ctxt,index);
    },
  );
}
//需要返回具体类型
Container buildListItem(BuildContext ctxt,int index) {
  return new Container(
      color: Colors.white,
      margin: EdgeInsets.all(8.0),
      child: Column(
        children: <Widget>[
          Image.network(userDatas[index].img!),
          SizedBox(height: 16.0,),
          buildListItemTitle(ctxt,index),
          buildListItemSubHead(ctxt,index),
        ],

      ),
    );
}
Text buildListItemSubHead(BuildContext context,int index) {
  return Text(
      userDatas[index].desc!,
      style: Theme.of(context).textTheme.subtitle1);
}

Text buildListItemTitle(BuildContext context,int index) {
  return Text(
      userDatas[index].name!,
      style: Theme.of(context).textTheme.headline6);
}


AppBar buildAppBar() => AppBar(title: buildText(), elevation: 40.0);

Text buildText() {
  return Text('hello',
      textDirection: TextDirection.ltr,
      style: TextStyle(
          fontSize: 40, fontWeight: FontWeight.bold, color: Colors.lightBlueAccent));
}
