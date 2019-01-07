import 'package:flutter/material.dart';
import 'package:record/bean/DisplayItemModel.dart';
import 'package:record/component/DisplayItem.dart';
import 'package:record/component/EditPage.dart';
import 'package:record/component/ScaffoldWidget.dart';

class DisplayPage extends StatelessWidget {
  final TextStyle _textStyle = TextStyle(
    color: Colors.white,
  );

  List<Widget> generateListItem() {
    return List.generate(
      30,
      (i) => DisplayItem(model: new DisplayItemModel("标题$i", "副标题$i")),
    );
  }

  void _handleClickWriteBtn(BuildContext context) {
    Navigator.push(context,
        MaterialPageRoute(builder: (ctx) => EditPage(0.0)));
  }

  @override
  Widget build(BuildContext context) {
    return ScaffoldWidget(
      child: Stack(children: <Widget>[
        ListView(
          children: generateListItem(),
        ),
        Container(
          alignment: Alignment.centerRight,
          child: RaisedButton(
            color: Colors.red,
            onPressed: () => _handleClickWriteBtn(context),
            child: Text(
              '写',
              style: _textStyle,
            ),
            shape: CircleBorder(),
          ),
        ),
      ]),
    );
  }
}
