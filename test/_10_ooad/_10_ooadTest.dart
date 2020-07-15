import "package:test/test.dart";

import "package:test/test.dart";

main() {
  test("func overring disable Test", () {
    doSomething(12,"我");//命名可选参数
    doSomething2(12,home: "家");//位置可选参数

    expect(doSomethingDefaultValue(12),"default home");
  });

  test("func first level Test", () {

    expect(doFunc((){//匿名函数
      return true;
    }),true);
    expect(doFuncDefault((){
      return true;
    }),true);
    expect(doFuncWithTypeDef((){
      return true;
    }),true);

    expect(doFuncReturnFunc()(),true);
  });

  test("test Assign ",(){
    var name="old";
    name??="new value";
    expect(name, "old");
    expect(name??"new Value", "old");
  });

  test("级联操作符号",(){
    var m=Metarial()..age=14..name="mine";
    expect(m.age,14);

  });
}

class Metarial{
  int age;
  String name;//报名
  String _privalue;//私有

}


doSomething(int age,[String name,String home]){
  print("$age  $name");
}

doSomething2(int age,{String name,String home}){
  print("$age  $name");
}

doSomethingDefaultValue(int age,{String name,String home="default home"}){
  return home;
}
doFuncDefault(Function f ){
  return f.call();
}
doFunc(bool f()){
  return f.call();
}
typedef IsTrust=bool Function();
doFuncWithTypeDef(IsTrust f){
  return f.call();
}

IsTrust doFuncReturnFunc(){
  return (){return true;};
}