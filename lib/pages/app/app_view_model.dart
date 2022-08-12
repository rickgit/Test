import 'package:app_flutter/base/BaseRepo.dart';
import 'package:app_flutter/base/BaseViewModel.dart';
import 'package:app_flutter/pages/app/app_repo.dart';

class AppViewModel extends BaseViewModel{
  @override
  BaseRepo initBaseRepo() {
    return AppRepo();
  }
  
}