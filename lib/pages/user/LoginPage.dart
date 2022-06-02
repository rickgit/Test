import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

Widget createLoginPage() {
  return Scaffold(
      body: Container(
        color: Color.fromARGB(1, 46, 66, 100),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Padding( padding: EdgeInsets.only(top: 171),child:  Icon(Icons.account_circle)),
            Padding(padding: EdgeInsets.only(left: 30,right: 30,top: 51),child: TextField(
                obscureText: true,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'username@gmail.com',
                )),),
            Padding(padding: EdgeInsets.only(left: 30,right: 30,top: 10),
              child: TextField(
                obscureText: true,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: '1234',
                )),),
            Padding(padding: EdgeInsets.only(left: 30,right: 30,top: 15),
              child: ElevatedButton(onPressed: () {}, child: Text("Sign in")),)
          ],
        ),
      )
  );
}

class LoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
      children: [Icon(Icons.account_circle)],
    );
  }
}
