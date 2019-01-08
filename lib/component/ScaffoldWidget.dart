import 'package:flutter/material.dart';

class ScaffoldWidget extends StatelessWidget {
  final Widget child;

  ScaffoldWidget({Key k, @required this.child}) : super(key: k);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Record",
      theme: ThemeData(
          primaryColorDark: Colors.white,
          primaryColorLight: Colors.white,
          accentColor: Colors.grey,
          fontFamily: 'TrueType'),
      home: Builder(
        builder: (context) => Scaffold(
              backgroundColor: Colors.white,
              body: SafeArea(child: child),
            ),
      ),
    );
  }
}
