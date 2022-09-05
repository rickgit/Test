import 'package:app_flutter/base/base_repo.dart';
import 'package:app_flutter/base/base_view_model.dart';
import 'package:app_flutter/pages/app/app_repo.dart';

class AppViewModel extends BaseViewModel{
  @override
  BaseRepo initBaseRepo() {
    return AppRepo();
  }
  
}