import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import '../../components/TitleWidget.dart';
import '../../initial/ResSize.dart';
import '../../initial/ResColors.dart';

Widget createMessagePage(BuildContext buildContext){
  return Scaffold(
    body: createPage(buildContext),
  );
}

Widget createPage(BuildContext buildContext) {
  return MsgPage();
}
class MsgPage extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return _MsgPageState();
  }

}

class _MsgPageState extends State<MsgPage> with SingleTickerProviderStateMixin {
  PageController mPageController=PageController(initialPage: 0);
  late TabController mTabController;
  bool isPageCanChanged = true;
  onPageChange(int index, {PageController? p, TabController? t}) async {
    if (p != null) {//判断是哪一个切换
      isPageCanChanged = false;
      await mPageController.animateToPage(index, duration: Duration(milliseconds: 500), curve: Curves.ease);//等待pageview切换完毕,再释放pageivew监听
      isPageCanChanged = true;
    } else {
      mTabController.animateTo(index);//切换Tabbar
    }
  }
  @override
  void initState() {
    mTabController=TabController(vsync: this,length: 2);
    mTabController.addListener(() {
      if(mTabController.indexIsChanging){
        onPageChange(mTabController.index, p: mPageController);
      }

    });
    super.initState();
  }
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        createCommonTitleBar(context,"消息"),
        Container(height: FONT_TITLE_HEIGHT,color:Color(COLOR_TAB_BACKGROUND),child: TabBar(tabs: [Tab(text: "消息1"),Tab(text: "消息2")] ,controller:mTabController ,)),
        Expanded(
            child: PageView(
          children: [createListPage(0), createListPage(1)],
          controller: mPageController,
              onPageChanged: (index){
                if (isPageCanChanged) {//由于pageview切换是会回调这个方法,又会触发切换tabbar的操作,所以定义一个flag,控制pageview的回调
                  onPageChange(index);
                }
              },
        ))
      ],
    );
  }
  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    mPageController.dispose();
  }
}


Container createListPage(int flag) {
  return Container(color: Colors.white,child: ListView.builder(itemBuilder: (BuildContext context, int index) {
    return Column(children: [
      Row(children: [Text("Tab$flag 第$index个消息",style: TextStyle(fontSize: FONT_DEFAULT,color: Color(COLOR_TITLE))), Container(width: 30,)
        ,Text("${DateFormat('yyyy-MM-dd HH:mm').format(DateTime.now())}",style: TextStyle(fontSize: FONT_DEFAULT,color: Color(COLOR_TITLE)))],
      ),
      Padding(padding: EdgeInsets.only(top: 10),child:Text("系统暂停",style: TextStyle(fontSize: FONT_DEFAULT))),
      Padding(padding: EdgeInsets.only(top: 10),child:Text("系统由于一些原因停止运行",style: TextStyle(fontSize: FONT_DEFAULT))),
      Container(height: 20,)
    ],
    mainAxisAlignment: MainAxisAlignment.start,
    crossAxisAlignment: CrossAxisAlignment.start,
    );
  },
  itemCount: 100,
  controller: ScrollController(),
  ));
}

