import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false, //去掉调试图标
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
  ThemeData(
      primaryColor: Colors.deepPurpleAccent,
      highlightColor: Color.fromRGBO(255, 255, 255, .5));
  splashColor:
  Colors.white70;
}

Scaffold buildScaffold(BuildContext context) {
  return Scaffold(
    backgroundColor: Colors.grey[100],
    appBar: buildAppBar(),
    body: buildConaiter(),
  );
}

Container buildConaiter() {
  return Container(
    color: Colors.redAccent,
  );
}

AppBar buildAppBar() {
  return AppBar(
      title: buildText(),
      leading: IconButton(
        icon: Icon(Icons.menu),
        tooltip: "菜单",
        onPressed: () => debugPrint("按压"),
      ),
      actions: <Widget>[
        //右上角action
        IconButton(
          icon: Icon(Icons.search),
          tooltip: "搜索",
          onPressed: () => debugPrint("搜索"),
        )
      ],
      centerTitle: true,
      bottom: TabBar(
        unselectedLabelColor: Colors.black38,
        //图标底部指示条
        indicatorColor: Colors.black54,
        indicatorSize: TabBarIndicatorSize.label,
        indicatorWeight: 1.0,
        tabs: [
          Tab(
            icon: Icon(Icons.local_activity),
          ),
          Tab(
            icon: Icon(Icons.change_history),
          ),
          Tab(
            icon: Icon(Icons.directions_bike),
          ),
        ],
      ),
      elevation: 40.0);
}

Text buildText() {
  return Text('hello');
}

Text buildDrawerListTileText() {
  return Text('hello');
}
