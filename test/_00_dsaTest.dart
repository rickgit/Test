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

}
