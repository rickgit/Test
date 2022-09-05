import 'dart:io';

import 'package:json_annotation/json_annotation.dart';
import 'package:package_info/package_info.dart';
import 'package:retrofit/retrofit.dart';
import 'package:dio/dio.dart';

part 'http.g.dart';


testRestClient(){
  // final logger = Logger();
  var options = new BaseOptions(
    connectTimeout: 5000,
    receiveTimeout: 3000,
  );
  final dio = Dio(options); // Provide a dio instance
  dio.interceptors.add(AuthInterceptor()); // 添加 token
  dio.interceptors.add(LogInterceptor(responseBody: true, requestBody: true)); //添加日志
  final client = RestClient(dio);
  client.getTasks().then((it) => print(it)); //logger.i(it));
}

class AuthInterceptor extends Interceptor {
  String PLATFORM = "android"; //可根据代码进行判断
  @override
  onRequest(RequestOptions options,RequestInterceptorHandler handler) async {
    //获取app版本
    PackageInfo packageInfo = await PackageInfo.fromPlatform();

    String version = packageInfo.version;
    if (Platform.isIOS) {
      PLATFORM = "ios";
    } else if (Platform.isAndroid) {
      PLATFORM = "android";
    } else if (Platform.isWindows) {
      PLATFORM = "Windows";
    } else if (Platform.isMacOS) {
      PLATFORM = "macos";
    } else if (Platform.isLinux) {
      PLATFORM = "Linux";
    }

    Map<String, String> headers = new Map();
    headers["Accept-Charset"] = "utf-8";
    headers["Connection"] = "keep-alive";
    headers["Accept"] = "*/*";
    headers["x-version"] = version; //自己更改配置
    headers["x-platform"] = PLATFORM;
    headers["lang"] = "ZH_CN";
    //获取存储数据 保存header token
    String token = (await   ()=>"token") as String;

    if (null!=token&&token.isNotEmpty) {
      headers["token"] = token; //添加自己项目中的请求头 进行保存
    }

    options.headers = headers;
    Map<String,String> querys=new Map();
    querys["lang"]="en";
    options.queryParameters = querys;
    return super.onRequest(options,handler,);
  }
}
/// 生成 g.dart 文件
/// flutter pub run build_runner build
@RestApi(baseUrl: "https://60f6ed5fa9c8e3e7c09b44ec.mockapi.com")
abstract class RestClient {

  factory RestClient(Dio dio, {String baseUrl}) =_RestClient;

  @GET("/store/inventory")
  Future<List<Task>> getTasks();

}

@JsonSerializable()
class Task {
  String? id;
  String? name;
  String? avatar;
  String? createdAt;

  Task({this.id, this.name, this.avatar, this.createdAt});

  factory Task.fromJson(Map<String, dynamic> json) => _$TaskFromJson(json);
  Map<String, dynamic> toJson() => _$TaskToJson(this);
}

