import 'package:app_flutter/model/User.dart';
import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
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
    drawer: buildDrawer(context),
    bottomNavigationBar: buildBottomNavigationBar(),
  );
}

BottomNavigationBar buildBottomNavigationBar() {
  return BottomNavigationBar(
      type: BottomNavigationBarType.fixed,//超过3个不会显示底部，需要设置这个参数
      fixedColor: Colors.black,
      currentIndex:bnbcurrentIndex,//当前选中状态
      onTap: _onTabBottomNb,
      items: [
    BottomNavigationBarItem(icon: Icon(Icons.explore)/*, title: Text('Export')*/),
    BottomNavigationBarItem(icon: Icon(Icons.history)/*, title: Text('history')*/),
    BottomNavigationBarItem(icon: Icon(Icons.unarchive)/*, title: Text('unarchive')*/),
    BottomNavigationBarItem(icon: Icon(Icons.home)/*, title: Text('home')*/)
  ]);
}

String imgs =
    "https://www.canva.cn/learn/wp-content/uploads/sites/17/2019/09/Snipaste_2019-09-24_15-21-59.png";

Drawer buildDrawer(BuildContext context) {
  return Drawer(
    child: ListView(
      padding: EdgeInsets.zero,
      children: <Widget>[
        UserAccountsDrawerHeader(
          accountName: Text(
            'header'.toUpperCase(),
            style: TextStyle(fontWeight: FontWeight.bold),
          ),
          accountEmail: Text('q@g.com'.toUpperCase()),
          currentAccountPicture:
              CircleAvatar(backgroundImage: NetworkImage(imgs)),
          decoration: BoxDecoration(
              color: Colors.blueGrey[400],
              image: DecorationImage(
                  image: NetworkImage(
                      "http://pic.lvmama.com/uploads/pc/place2/2017-07-19/7e318645-7b36-465e-8e79-9420fe057619.jpg"),
                  fit: BoxFit.cover, //填充
                  colorFilter: ColorFilter.mode(
                      //滤镜
                      Colors.blueGrey[400]!.withOpacity(0.6),
                      BlendMode.srcOver))),
        ),
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

int bnbcurrentIndex=0;
void _onTabBottomNb(int value) {
  bnbcurrentIndex=value;
}