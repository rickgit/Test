import 'dart:io';

import "package:test/test.dart";

//必须在async函数体内调用awaitt
//async、await、Future
main() async {
  doAsync(num index) async {
    print('异步$index');
    sleep(Duration(seconds: 1));
  }

  test("data test", () async {
    //await async
    for (num i = 0; i < 3; i++) {
      await doAsync(i);
    }
    print('结束');
  });

  doAsyncFuture(num index) async {
    print('异步$index');
    sleep(Duration(seconds: 1));
  }

  test("data test2", () async {
    //await async
    for (num i = 0; i < 5; i++) {
        doAsyncFuture(i).then((value) => null);
    }
    print('结束');
  });
}
