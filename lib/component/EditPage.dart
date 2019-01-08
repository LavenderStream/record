import 'package:flutter/material.dart';
import 'package:record/bean/ArticleModel.dart';
import 'package:record/component/ScaffoldWidget.dart';
import 'package:record/utils/Utils.dart';

class EditPage extends StatefulWidget {
  final int id;

  EditPage(this.id);

  @override
  State<EditPage> createState() => _EditPageState(id);
}

class _EditPageState extends State<EditPage> {
  int id;

  _EditPageState(this.id);

  String _title = '';
  String _location = '';
  String _content = '';

  @override
  void initState() {
    super.initState();
    if (id != -1) {
      _generateArticles();
    } else {
      _title = Utils.coverDate2Chinese();
    }
  }

  void _generateArticles() async {
    ArticleModelProvider provider = ArticleModelProvider();
    String path = await Utils.getDataBasePath();
    await provider.open(path);
    ArticleModel article = await provider.getArticle(id);
    await provider.close();

    setState(() {
      _title = article.title;
      _location = article.location;
      _content = article.location;
    });
  }

  @override
  Widget build(BuildContext context) {
    return ScaffoldWidget(
      child: Stack(
        children: <Widget>[
          _EditComponent(_title, _location, _content),
          _BtnComponent(),
        ],
      ),
    );
  }
}

// 编辑框
class _EditComponent extends StatelessWidget {
  String _title = '';
  String _location = '';
  String _content = '';

  _EditComponent(this._title, this._location, this._content);

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

  // 标题
  Widget _generateDateView(BuildContext context) {
    return Container(
      padding: const EdgeInsets.fromLTRB(16, 0, 16, 0),
      alignment: Alignment.centerLeft,
      width: double.infinity,
      height: 60,
      child: Text(
        _title == null ? "" : _title,
        style: Theme.of(context).textTheme.title.copyWith(
              fontSize: 16,
            ),
      ),
    );
  }

  // 地理位置信息
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
              controller: TextEditingController.fromValue(
                TextEditingValue(
                  text: _location == null ? '' : _location,
                  selection: TextSelection.fromPosition(
                    TextPosition(
                      affinity: TextAffinity.downstream,
                      offset: _content.length,
                    ),
                  ),
                ),
              ),
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

  // 正文内容
  Widget _generateEditView(BuildContext context) {
    return Expanded(
      child: Container(
        padding: const EdgeInsets.fromLTRB(16, 0, 16, 32),
        child: TextField(
          controller: TextEditingController.fromValue(
            TextEditingValue(
              text: _content == null ? '' : _content,
              selection: TextSelection.fromPosition(
                TextPosition(
                  affinity: TextAffinity.downstream,
                  offset: _content.length,
                ),
              ),
            ),
          ),
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
