import 'package:flutter/material.dart';

import '../initial/ResColors.dart';
import '../initial/ResSize.dart';

Widget createCommonTitleBar(BuildContext buildContext, String title) {
  return Container(
    color: Color(COLOR_BACKGROUND),
    padding: EdgeInsets.only(top: 12),
    child: Container(
      height: FONT_TITLE_HEIGHT,
      color: Color(COLOR_BACKGROUND),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Spacer(
            flex: 1,
          ),
          Text(
            title,
            style: TextStyle(color: Color(COLOR_WHITE)),
          ),
          Spacer(flex: 1)
        ],
      ),
    ),
  );
}
