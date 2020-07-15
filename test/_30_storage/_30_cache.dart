import "package:test/test.dart";
//8种内置数据类型
main(){
//  BuildInType
  //numbers int,double
  //strings；runes (for expressing Unicode characters in a string)
  //booleans bool true,false
  //lists (also known as arrays) []
  //sets {}
  //maps  {'':''}
   //symbols
  test("numbers Test", () {
    var age = 1;
    expect(age.isEven, false);
    expect(age.runtimeType.toString(), "int");
    var big = BigInt.parse('111111111111111111111111111111111111111111111111111111');
    expect(big.runtimeType.toString(), "_BigIntImpl");
    var price=2.3;
    expect(price.isNegative, false);
    expect(price.runtimeType.toString(), "double");
    expect(5e0, 5);
  });

  test("String Test", () {
    var name = "user";
    expect("$name", "${name}");
    final nameFinal = "user";//常量

    expect(identical(name, nameFinal), true);

    const constStr=23;//值必须是编译时常量

    expect(identical(constStr, constStr), true);


    expect(name.runes==name.runes,false);
    expect(name.codeUnits==name.codeUnits,false);

    var addStr=name+' add ';
    print(addStr.runtimeType);
    print("addStr $addStr".runtimeType);

    //上千计算量才能看出差别
    var time=DateTime.now();
    for(var i=0;i<1100;i++){
      addStr+=i.toString();
    }
    print( DateTime.now().difference(time));
    var sb=StringBuffer(addStr);
    time=DateTime.now();
    for(var i=0;i<1100;i++){
      sb.write(i.toString());
    }
    print( DateTime.now().difference(time));
  });

  test("collection test", () {
    var aList = ['s', 'd'];//GrowableList
    expect(aList[1], 'd');
    expect(aList.runtimeType.toString(),"List<String>");
    //https://github.com/dart-lang/sdk/blob/master/sdk/lib/_internal/vm/lib/growable_array.dart
    //扩容(old_capacity * 2) | 3， new _List(capacity | 1)保证奇数。fori拷贝数据。
    //重新设置长度，可能需要_shrink
    print((0 * 2) | 3);

    var aSet={'sd'};//_CompactLinkedHashSet
    expect(aSet.elementAt(0), 'sd');
    expect(aSet.runtimeType.toString(),"_CompactLinkedHashSet<String>");

    final aMap={'sd':1};//_InternalLinkedHashMap
    expect(aMap['sd'], 1);
    expect(aMap.runtimeType.toString(),"_InternalLinkedHashMap<String, int>");


  });

  test("collection iterator test", () {
    var aList = ['s', 'd'];
    expect(aList.iterator!= aList.iterator,true);
    final it=aList.iterator;//每次获取都会重新创建一个队形
    while(it.moveNext()){
      print('${it.current}');
    }

  });

  test("Symbol test", () {
    expect(#s, Symbol("s"));

  });
}