
import 'package:flutter/foundation.dart';
import 'package:flutter/widgets.dart';
import 'package:get/get.dart';

class CustomLocalizations{
  late final String? mLang;
  CustomLocalizations(locale) {
    this.mLang = locale.languageCode.toLowerCase();
    // _textDirection = _rtlLanguages.contains(language) ? TextDirection.rtl : TextDirection.ltr;
  }
  static  CustomLocalizations of<CustomLocalizations>(BuildContext context){
    return Localizations.of(context, CustomLocalizations);
  }
  static const LocalizationsDelegate<CustomLocalizations> delegate = CustomLocalizationsDelegate();
  static Map<String, Map<String, String>> _localValues = {
    'en': {"title": "title in english"},
    "zh": {"title": "标题 中文"}
  };

  String? findString(String key){
    return _localValues[mLang]![key];
  }
}

class CustomLocalizationsDelegate extends LocalizationsDelegate<CustomLocalizations>{
  const CustomLocalizationsDelegate();
  @override
  bool isSupported(Locale locale) {
    return ['en','zh'].contains(locale.languageCode);
  }

  @override
  Future<CustomLocalizations> load(Locale locale) {
    return SynchronousFuture<CustomLocalizations>(CustomLocalizations(locale));
  }

  @override
  bool shouldReload(covariant LocalizationsDelegate<CustomLocalizations> old) {
    return false;
  }

}


findString(BuildContext context,String key){
  return CustomLocalizations.of<CustomLocalizations>(context).findString(key);
}
