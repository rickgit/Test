import 'dart:html';

import "package:test/test.dart";

import "package:test/test.dart";

main() {
  test("factory Test", () {
     var m1= Metarial("an");
     var m2= Metarial("an");
      expect(identical(m1,m2),true);
  });


}

class Metarial{
  int? age;
  String? name;
  static final Map<String, Metarial> _cache = <String, Metarial>{};
  factory Metarial(String name){
    if(_cache[name]!=null){
      return _cache[name]!;
    }
    var newM=Metarial._internal(name);
    return _cache[name]=newM;
  }
  Metarial._internal(String name){
    this.name=name;
  }
}

