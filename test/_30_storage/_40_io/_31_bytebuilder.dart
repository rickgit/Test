import 'dart:io';

import 'package:test/test.dart';

main() {
  test("bytebuild grow", () {
    var bb = BytesBuilder();
    print(bb.length.toString());//0
    bb.addByte(0);
    print(bb.length.toString());//0


    //默认1024
    expect(_pow2roundup(1025),2048);
    expect(_pow2roundup(2049),2048*2);
//    _CopyingBytesBuilder 初始化1024，扩容是2指数倍增加

  });
}

int _pow2roundup(int x) {
  assert(x > 0);
  --x;
  x |= x >> 1;
  x |= x >> 2;
  x |= x >> 4;
  x |= x >> 8;
  x |= x >> 16;
  return x + 1;
}
