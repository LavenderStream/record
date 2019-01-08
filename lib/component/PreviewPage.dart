import 'package:flutter/material.dart';
import 'package:record/component/ScaffoldWidget.dart';

class PreviewPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ScaffoldWidget(
      child: ScaffoldWidget(child: Center()),
    );
  }
}
