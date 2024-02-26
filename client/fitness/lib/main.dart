import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

void main(){
  runApp(MyApp());
}

class MyApp extends StatelessWidget{
  const MyApp({super.key});
  @override
  Widget build(BuildContext context){
    return MaterialApp(
      home:LoginPage(),
    );
  }
}

class LoginPage extends StatefulWidget{
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage>{
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  void _login() async{
    final response= await http.post(
      Uri.parse('url'),
      headers: <String, String>{
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body:{
        'username': _usernameController.text,
        'password': _passwordController.text,
      },
    );

    if(response.statusCode == 200){
      print('Login Successful');
    }else{
      print('Login Failed');
    }
  }

  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title:Text('Login Page')
      ),
      body: Column(
        children: <Widget>[
          TextField(
            controller: _usernameController,
            decoration: InputDecoration(labelText: 'Username'),
          ),
          TextField(
            controller: _passwordController,
            decoration: InputDecoration(labelText: 'Password'),
            obscureText: true,
          ),
          ElevatedButton(
            onPressed:  _login,
            child: Text('Login'),
          ),
        ],
      ),
    );
  }
}