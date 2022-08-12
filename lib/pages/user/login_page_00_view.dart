import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

Widget createLoginPage() {
  return Scaffold(
      resizeToAvoidBottomInset: false,
      body: Container(
        color: Color.fromARGB(1, 46, 66, 100),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Padding( padding: EdgeInsets.only(top: 171),child:  Icon(Icons.account_circle)),
            Padding(padding: EdgeInsets.only(left: 30,right: 30,top: 51),child:
            TextField(
                keyboardType:TextInputType.emailAddress,
                decoration: InputDecoration(
                  border: OutlineInputBorder(borderRadius: BorderRadius.all(Radius.circular(30)) ),
                  hintText: 'username@gmail.com',
                  suffixIcon: Icon(Icons.keyboard_arrow_down),
                )),
            ),
            Padding(padding: EdgeInsets.only(left: 30,right: 30,top: 10),
              child: TextField(
                  obscureText: true,
                  decoration: InputDecoration(
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(30)),
                    ),
                    hintText: '1234',
                    suffixIcon: Icon(Icons.remove_red_eye_outlined),
                  )),),
            Container(child: ElevatedButton(onPressed: () {}, child: Text("Sign in") ,style: ButtonStyle( shape: MaterialStateProperty.all(
                RoundedRectangleBorder(
                    borderRadius:
                    BorderRadius.circular(
                        30))) ),),
              width: double.infinity,height: 60,
              padding: EdgeInsets.only(left: 30,right: 30,top: 15),),
            Padding(padding: EdgeInsets.only(left: 30,right: 30,top: 10),
              child:
              Row(mainAxisAlignment: MainAxisAlignment.end,children: [
                TextButton(onPressed: (){}, child: Text("Sign up")),
                SizedBox(width:10,child: TextButton(onPressed: (){}, child: Text("|")),),
                TextButton(onPressed: (){}, child: Text("Forgot passwod")),
              ],),
            ),
            Spacer(),
            Row(
              children: [
                Checkbox(
                  checkColor: Colors.white,
                  fillColor: MaterialStateProperty.resolveWith(getColor),
                  value: false,
                  onChanged: (bool? value) {

                  },
                ),
                Text("I have read and agreed to the User Agreement"),
              ],
            )
          ],
        ),
      )
  );
}

Color getColor(Set<MaterialState> states) {
  const Set<MaterialState> interactiveStates = <MaterialState>{
    MaterialState.pressed,
    MaterialState.hovered,
    MaterialState.focused,
  };
  if (states.any(interactiveStates.contains)) {
    return Colors.blue;
  }
  return Colors.black45;
}
class LoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
      children: [Icon(Icons.account_circle)],
    );
  }
}
