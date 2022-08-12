import 'package:app_flutter/model/User.dart';
import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: DrawerWrapperWidget(),//需要包裹一层widget，而且需要顶层是MaterialApp，Navigator.pop才会生效
      theme: buildThemeData(),
    );
  }
}

class DrawerWrapperWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return buildDefaultTabController(context);
  }
}

DefaultTabController buildDefaultTabController(BuildContext context) {
  return DefaultTabController(
    length: 3,
    child: buildScaffold(context),
  );
}

ThemeData buildThemeData() => ThemeData(primaryColor: Colors.blueAccent);

Scaffold buildScaffold(BuildContext context) {
  return Scaffold(
    backgroundColor: Colors.grey[100],
    appBar: buildAppBar(),
//    body: buildTabBarView(),
    drawer: buildDrawer(context),
  );
}

Drawer buildDrawer(BuildContext context) {
  return Drawer(
    child: ListView(
      padding: EdgeInsets.zero,
      children: <Widget>[
        DrawerHeader(
          child: Text('header'.toUpperCase()),
          decoration: BoxDecoration(),
        ),
        buildDrawerListTile(context),
        buildDrawerListTile(context),
        buildDrawerListTile(context),
        buildDrawerListTile(context),
      ],
    ),
  );
}

ListTile buildDrawerListTile(BuildContext context) {
  return ListTile(
    title: buildDrawerListTileText(),
    trailing: Icon(
      Icons.memory,
      color: Colors.black12,
      size: 22,
    ),
    onTap: () => Navigator.pop(context),
  );
}

AppBar buildAppBar() {
  return AppBar(title: buildText(), elevation: 40.0);
}

Text buildText() {
  return Text('hello',
      textDirection: TextDirection.ltr,
      style: TextStyle(
          fontSize: 40,
          fontWeight: FontWeight.bold,
          color: Colors.lightBlueAccent));
}

Text buildDrawerListTileText() {
  return Text(
    'hello',
    textAlign: TextAlign.right,
  );
}
