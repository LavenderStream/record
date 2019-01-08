import 'package:flutter/material.dart';
import 'package:record/bean/ArticleModel.dart';
import 'package:record/component/ScaffoldWidget.dart';

class PreviewPage extends StatefulWidget {
  final ArticleModel _model;

  PreviewPage(this._model);

  @override
  State<PreviewPage> createState() => _PreviewPageState(_model);
}

class _PreviewPageState extends State<PreviewPage> {
  final ArticleModel _model;
  List<_TextType> _item = List();

  _PreviewPageState(this._model);

  @override
  void initState() {
    super.initState();
    _processModel();
  }

  Widget _generateItem(int index) {
    _TextType it = _item[index];

    if (it.type == _TextType.TITLE) {
      return _generateTitleView(it.text);
    } else if (it.type == _TextType.CONTENT) {
      return _generateContentView(it.text);
    } else if (it.type == _TextType.LOCATION) {
      return _generateLocationView(it.text);
    }
  }

  // 添加标题布局
  Widget _generateTitleView(String text) {
    List<Widget> widgets = [];
    for (int i = 0; i < text.length; i++) {
      widgets.add(
        Text(
          text[i],
          style: Theme.of(context).textTheme.title.copyWith(
                fontSize: 32,
              ),
        ),
      );
    }

    widgets.add(
      Container(
        width: 38,
      ),
    );

    return Column(
      children: widgets,
    );
  }

  // 添加正文布局
  Widget _generateContentView(String text) {
    List<Widget> rowWidgets = [];

    // 按照结尾分组
    List<String> sorts = [];
    String flag = "";
    for (int i = 0; i < text.length; i++) {
      if (text[i] == ',' ||
          text[i] == '.' ||
          text[i] == '，' ||
          text[i] == '。' ||
          text[i] == '\n' ||
          text[i] == '\r') {
        debugPrint('text chat flag ' + flag);
        sorts.add(flag);
        flag = "";
      } else {
        flag += text[i];
      }
    }

    // 创建竖直组件
    for (var sort in sorts.reversed) {

      List<Widget> columnWidgets = [];
      columnWidgets.add(Container(
        height: 12,
      ));

      for (int i = 0; i < sort.length; i++) {
        columnWidgets.add(
          Text(
            sort[i],
            style: Theme.of(context).textTheme.title.copyWith(
                  fontSize: 22,
                ),
          ),
        );
      }
      // 添加到竖直的view中
      rowWidgets.add(Column(
        children: columnWidgets,
      ));
      rowWidgets.add(Container(
        width: 4,
      ));
    }

    rowWidgets.add(
      Container(
        width: 48,
      ),
    );

    return Row(
      children: rowWidgets,
    );
  }

  // 增加位置信息
  Widget _generateLocationView(String text) {
    List<Widget> widgets = [];

    widgets.add(
      Container(
        width: 96,
      ),
    );

    for (int i = 0; i < text.length; i++) {
      widgets.add(
        Text(
          text[i],
          style: Theme.of(context).textTheme.title.copyWith(
                fontSize: 18,
              ),
        ),
      );
    }

    return Container(
      alignment: Alignment.bottomCenter,
      child: Column(
        children: widgets,
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return ScaffoldWidget(
      child: ListView.builder(
          padding: const EdgeInsets.all(16),
          reverse: true,
          scrollDirection: Axis.horizontal,
          itemCount: _item == null ? 0 : _item.length,
          itemBuilder: (context, index) => _generateItem(index)),
    );
  }

  // 拆分文章信息
  void _processModel() {
    _item.add(_TextType(_TextType.TITLE, _model.title));
    _item.add(_TextType(_TextType.CONTENT, _model.content));
    _item.add(_TextType(_TextType.LOCATION, _model.location));
  }
}

class _TextType {
  static final int TITLE = 1;
  static final int LOCATION = 2;
  static final int CONTENT = 3;
  int type = 0;
  String text = '';

  _TextType(this.type, this.text);
}
