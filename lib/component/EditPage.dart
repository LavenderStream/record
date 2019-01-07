import 'package:flutter/material.dart';
import 'package:record/component/ScaffoldWidget.dart';
import 'package:record/utils/Utils.dart';

class EditPage extends StatelessWidget {
  final double id;

  EditPage(this.id);

  @override
  Widget build(BuildContext context) {
    return ScaffoldWidget(
      child: Stack(
        children: <Widget>[
          _EditComponent(),
          _BtnComponent(),
        ],
      ),
    );
  }
}

// 编辑框
class _EditComponent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        children: <Widget>[
          _generateDateView(context),
          _Div(),
          _generateEditView(context),
          _Div(),
          _generateLocationView(context),
        ],
      ),
    );
  }

  Widget _generateDateView(BuildContext context) {
    return Container(
      padding: const EdgeInsets.fromLTRB(16, 0, 16, 0),
      alignment: Alignment.centerLeft,
      width: double.infinity,
      height: 60,
      child: Text(
        Utils.coverDate2Chinese(),
        style: Theme.of(context).textTheme.title.copyWith(
              fontSize: 16,
            ),
      ),
    );
  }

  Widget _generateLocationView(BuildContext context) {
    return Row(
      children: <Widget>[
        Container(
          child: Text(
            "于",
            style: Theme.of(context).textTheme.title.copyWith(
                  fontSize: 16,
                ),
          ),
        ),
        Expanded(
          child: Container(
            padding: const EdgeInsets.fromLTRB(6, 0, 16, 0),
            alignment: Alignment.center,
            width: double.infinity,
            height: 60,
            child: TextField(
              decoration: InputDecoration(border: InputBorder.none),
              cursorColor: Colors.black,
              style: Theme.of(context).textTheme.title.copyWith(
                    fontSize: 16,
                  ),
            ),
          ),
        )
      ],
    );
  }

  Widget _generateEditView(BuildContext context) {
    return Expanded(
      child: Container(
        padding: const EdgeInsets.fromLTRB(16, 0, 16, 32),
        child: TextField(
          maxLines: 100,
          decoration: InputDecoration(border: InputBorder.none),
          cursorColor: Colors.black,
          style: Theme.of(context).textTheme.title.copyWith(
                fontSize: 20.0,
              ),
        ),
      ),
    );
  }
}

// 侧边按钮
class _BtnComponent extends StatelessWidget {
  final TextStyle _textStyle = TextStyle(
    color: Colors.white,
  );

  Widget _generateCatBtn() {
    return RaisedButton(
      color: Colors.red,
      onPressed: () => null,
      child: Text(
        '看',
        style: _textStyle,
      ),
      shape: CircleBorder(),
    );
  }

  Widget _generateSaveBtn() {
    return RaisedButton(
      color: Colors.red,
      onPressed: () => null,
      child: Text(
        '存',
        style: _textStyle,
      ),
      shape: CircleBorder(),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.end,
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        _generateSaveBtn(),
        Container(
          height: 16,
        ),
        _generateCatBtn(),
      ],
    );
  }
}

class _Div extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.grey,
      width: double.infinity,
      height: 0.1,
    );
  }
}
