import 'package:app_flutter/components/toast_util.dart';
import 'package:app_flutter/initial/custom_localizations.dart';
import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'res_strings.dart';
import 'routes.dart';
import 'package:app_flutter/pages/home_page.dart';

Widget createApp(){
  return MaterialApp(
    theme: ThemeData(
      splashColor: Colors.transparent, // 点击时的高亮效果设置为透明
      highlightColor: Colors.transparent, // 长按时的扩散效果设置为透明
      splashFactory: const NoSplashFactory(), 
      ),
    debugShowCheckedModeBanner: false,
    initialRoute: '/',
    routes: pageRouteMap,
    localizationsDelegates:localizationsDelegates(),
    supportedLocales: supportedLocales(),
    // onGenerateRoute: (RouteSettings settings)=>RouteFactory(settings),
    home: createDesktopPage(),
    //   localizationsDelegates:[
    //   GlobalMaterialLocalizations.delegate,
    //   GlobalCupertinoLocalizations.delegate,
    //   GlobalWidgetsLocalizations.delegate,],
    // supportedLocales: [Locale('en'),Locale('zh')],
    builder: EasyLoading.init(),

  );
} 
class NoSplashFactory extends InteractiveInkFeatureFactory {
  const NoSplashFactory();
  
  @override
  InteractiveInkFeature create({required MaterialInkController controller, required RenderBox referenceBox, required Offset position, required Color color, required TextDirection textDirection, bool containedInkWell = false, 
   rectCallback, BorderRadius? borderRadius, ShapeBorder? customBorder, double? radius, VoidCallback? onRemoved}) {
   return new NoSplash(
      controller: controller,
      referenceBox: referenceBox,
      color: color, 
    );
  }
 
}
class NoSplash extends InteractiveInkFeature {
  NoSplash({required MaterialInkController controller, required RenderBox referenceBox, required Color color}) : super(controller: controller, referenceBox: referenceBox, color: color){
     controller.addInkFeature(this);
  }

  @override
  void paintFeature(Canvas canvas, Matrix4 transform) {
    
  }
  
}
 