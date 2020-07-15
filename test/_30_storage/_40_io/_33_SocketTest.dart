import 'package:test/test.dart';
import 'dart:io';
import 'dart:convert'; //utf8

//http://www.bejson.com/knownjson/webInterface/  jsonapi提供
//http://tools.jb51.net/code/json2javabean JavaBean转化
main() {
  test("socket ",() async {
      //连接服务器的8081端口
      Socket socket = await Socket.connect('www.baidu.com', 80);
//    socket.write('Hello!');
    socket.cast<List<int>>().transform(utf8.decoder).listen(print);
} );

  test("http ", () async{
  var hc = HttpClient();

  HttpClientRequest request =await hc .getUrl(Uri.parse( "http://www.kuaidi100.com/query?type=yuantong&postid=11111111111")) ;
  HttpClientResponse response = await request.close();
  print(response.toString());
  String responseBody = await response.transform(utf8.decoder).join();
  print(responseBody);

  });
  test("WebSocket ", () {

  });
}
