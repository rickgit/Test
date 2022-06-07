import 'package:flutter/widgets.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';//#intl 步骤4

supportedLocales(){//#intl 步骤5
  return AppLocalizations.supportedLocales;
}
localizationsDelegates(){//#intl 步骤5
  return AppLocalizations.localizationsDelegates;
}

abstract class CommonLocalizations {
  static AppLocalizations? of(BuildContext context) {
    return AppLocalizations.of(context);
  }
}