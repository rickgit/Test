//可选参数可以是命名参数或者位置参数，但一个参数只能选择其中一种方式修饰。

import 'package:args/args.dart';
import 'package:test/test.dart';

void addPosMethod(num a, num b, [num c, num d]) {}

void addNameMethod(num a, num b, {num c, num d}) {}

main(List<String> arg) {
  test("",(){
    addPosMethod(3, 3, 3);
    addNameMethod(3, 3, d: 3);
  });
  var parser = ArgParser();//args: ^1.6.0 解析参数


}
