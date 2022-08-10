import 'package:flutter/material.dart';

import 'BaseViewModel.dart';
abstract class BaseState<W extends StatefulWidget, VM extends BaseViewModel>
    extends State<W> {

  late VM mViewModel=initViewModel();

  @override
  void initState() {
    super.initState();
    
  }

  VM initViewModel();//TODO 依赖注入

  @override
  void dispose() {
    super.dispose();
    //销毁viewmodel
  }

}
