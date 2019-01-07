import 'package:flutter/material.dart';
import 'package:record/bean/DisplayItemModel.dart';

class DisplayItem extends StatelessWidget {
  final DisplayItemModel model;

  DisplayItem({Key k, @required this.model}) : super(key: k);

  @override
  Widget build(BuildContext context) {
    TextStyle _titleStyle = TextStyle(
      decoration: TextDecoration.none,
      fontSize: 16,
      color: Colors.black,
    );

    TextStyle _subtitleStyle = TextStyle(
      decoration: TextDecoration.none,
      fontSize: 12,
      color: Colors.black,
    );

    Widget _generateListView() {
      return Padding(
        padding: const EdgeInsets.fromLTRB(32, 16, 16, 16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text(
              model.time,
              style: _titleStyle,
            ),
            Container(
              height: 12,
            ),
            Text(
              model.content,
              style: _subtitleStyle,
            ),
          ],
        ),
      );
    }

    void _dispatchItemClick() {
      print(model.id);
    }

    Widget _assemblePressEvent(Widget widget) {
      return GestureDetector(
        onTap: () => _dispatchItemClick(),
        child: widget,
      );
    }

    return _assemblePressEvent(Container(
      color: Colors.white,
      width: double.infinity,
      child: _generateListView(),
    ));
  }
}
