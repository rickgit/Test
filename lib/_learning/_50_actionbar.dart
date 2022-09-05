import 'package:app_flutter/model/user.dart';
import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,//去掉调试图标
      home: DrawerWrapperWidget(), //需要包裹一层widget，Navigator.pop才会生效
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

ThemeData buildThemeData() {
  return ThemeData(primaryColor: Colors.deepPurpleAccent,
  highlightColor: Color.fromRGBO(255, 255, 255, .5));
  // splashColor:Colors.white70;
}

Scaffold buildScaffold(BuildContext context) {
  return Scaffold(
    backgroundColor: Colors.grey[100],
    appBar: buildAppBar(),
  );
}



AppBar buildAppBar() {
  return AppBar(title: buildText(),
      leading: IconButton(
        icon: Icon(
          Icons.menu
        ),
        tooltip: "菜单",
        onPressed: ()=>debugPrint("按压"),
      ),
      actions: <Widget>[  //右上角action
        IconButton(
          icon: Icon(
              Icons.search
          ),
          tooltip: "搜索",
          onPressed: ()=>debugPrint("搜索"),
        )
      ],
      centerTitle: true,
      elevation: 40.0
  );
}

Text buildText() {
  return Text('hello');
}

Text buildDrawerListTileText() {
  return Text(
    'hello'
  );
}
