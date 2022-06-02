import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(home:
            Column(
              mainAxisSize:MainAxisSize.max,
              children:[
                Text("title"),
                Expanded(child:Container(child:Text("内容"))),
                Row(children:[Expanded(child:Container(alignment:Alignment.center,child:Text("tab1")))
                              ,Expanded(child:Container(alignment:Alignment.center,child:Text("tab2")))
                              ,Expanded(child:Container(alignment:Alignment.center,child:Text("tab3")))])
              ]
          ));
  }
}
 