import 'BaseRepo.dart';
import 'package:flutter/material.dart';
//R 代表可以访问数据 Repo

abstract class BaseViewModel<R extends BaseRepo> extends ChangeNotifier {
  late R mRepo=initBaseRepo();

  R initBaseRepo();

}
