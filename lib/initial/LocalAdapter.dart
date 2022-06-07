import 'package:app_flutter/initial/CustomLocalizations.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';//#intl 步骤4

supportedLocales(){//#intl 步骤5
  return AppLocalizations.supportedLocales;
}
localizationsDelegates(){//#intl 步骤5
  List list=<LocalizationsDelegate<dynamic>>[];
  list.addAll(AppLocalizations.localizationsDelegates);
  list.add(CustomLocalizations.delegate);
  return list;
}

abstract class CommonLocalizations {
  static AppLocalizations? of(BuildContext context) {
    return AppLocalizations.of(context);
  }
}