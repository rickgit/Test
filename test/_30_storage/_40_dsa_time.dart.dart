import "package:test/test.dart";

main() {
  test("data test", (){
    var date=DateTime.now();
    expect(date.timeZoneName, "中国标准时间");
    final customDate=DateTime.parse("2020-07-14 11:27:28.458409");
    expect(customDate.year, 2020);
    expect(date.timeZoneName, "中国标准时间");


    var dur= Duration(days: 1);
    expect(dur.inHours,24);
  });
  test("hash test", (){
    expect(true.hashCode, 1231);
    expect(false.hashCode, 1237);
    expect(0.hashCode, 0);
    expect(BigInt.from(0).hashCode, 6707);


    expect(1.5.hashCode, 4609434219686920192);//java  1073217536

//    expect( 111111111111111111.hashCode,  (111111111111111111 ^ (111111111111111111 >> 32)));
  });

}
