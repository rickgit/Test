import 'package:test/test.dart';
import 'dart:io';


main() {
  test("file test", () {
    var f = File("");
    print("absolute " + f.path);
    print("absolute " + f.absolute.toString()); //flutter_app 项目的文件夹
    print("absolute " + f.uri.toString());



    f = File('D:\\workspace\\ws-flutter\\flutter_app\\test\\_30_storage\\_40_io\\_31_file.dart');
    print("absolute " + f.absolute.toString()); //flutter_app 项目的文件夹
    print("absolute " + f.existsSync().toString()); //flutter_app 项目的文件夹
    print(f.readAsStringSync());//_RandomAccessFile : RandomAccessFile
  });
  test("Platform test", () {
    var uri = Platform.script;
    var path = uri.toFilePath();
    print('路径 $path ');//flutter_app\main.dart 路径
  });
}
