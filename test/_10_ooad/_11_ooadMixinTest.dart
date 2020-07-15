import "package:test/test.dart";

import "package:test/test.dart";

main() {
  test("Mixin Test", () {

       Metarial()..doMixin();
  });


}
mixin MixinDoing {
  doMixin(){
    print("ProtocolDoing");
  }
}
class Metarial with MixinDoing{

  doing(){
    print("Metarial Doing");
  }
}

