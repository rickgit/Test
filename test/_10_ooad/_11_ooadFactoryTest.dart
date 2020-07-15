import "package:test/test.dart";

import "package:test/test.dart";

main() {
  test("factory Test", () {
     var m1= Metarial("an");
     var m2= Metarial("an");
      expect(identical(m1,m2),false);
  });


}

class Metarial{
  int age;
  String name;

  factory Metarial(String name){
    return Metarial.internal(name);
  }
  Metarial.internal(String name){
    this.name=name;
  }
}

