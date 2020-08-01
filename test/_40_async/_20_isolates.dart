import 'dart:io';
import 'dart:isolate';

import "package:test/test.dart";
//_50_multithread.dart
void newIsolate(String mainMessage){
  print("isolate 等待");
  sleep(Duration(seconds: 3));
  print(mainMessage);
}
main() {
  //Synchronous 生成器： 返回一个 Iterable 对象。
  //Asynchronous 生成器： 返回一个 Stream 对象。

  Isolate.spawn(newIsolate, 'Hello, Im from new isolate!');//newIsolate需要顶层函数
  print('主线程等待');
  sleep(Duration(seconds: 10)); //主线程阻塞等待

  //单元测试，会被阻塞
  test("_50_multithread test", (){
    Isolate.spawn(newIsolate, 'Hello, Im from new isolate!');//newIsolate需要顶层函数
    print('主线程等待');
    sleep(Duration(seconds: 10)); //主线程阻塞等待
  });

}
