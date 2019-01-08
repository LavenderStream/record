import 'dart:async';

import 'package:flutter/material.dart';
import 'package:record/component/DisplayPage.dart';
import 'package:record/component/ScaffoldWidget.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ScaffoldWidget(
      child: SplashPage(),
    );
  }
}

class SplashPage extends StatefulWidget {
  @override
  State<SplashPage> createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage>
    with SingleTickerProviderStateMixin {
  @override
  void initState() {
    super.initState();
    Timer(Duration(seconds: 1), () {
      Navigator.of(context).pushAndRemoveUntil(
          MaterialPageRoute(builder: (context) => DisplayPage()),
          (router) => router == null);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        '小记',
        style: TextStyle(
          decoration: TextDecoration.none,
          fontSize: 21,
        ),
      ),
    );
  }
}
