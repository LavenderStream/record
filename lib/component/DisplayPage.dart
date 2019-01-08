import 'package:flutter/material.dart';
import 'package:record/bean/ArticleModel.dart';
import 'package:record/component/DisplayItem.dart';
import 'package:record/component/EditPage.dart';
import 'package:record/component/ScaffoldWidget.dart';
import 'package:record/utils/Utils.dart';

class DisplayPage extends StatefulWidget {
  @override
  State<DisplayPage> createState() => _DisplayPageState();
}

class _DisplayPageState extends State<DisplayPage> {
  final TextStyle _textStyle = TextStyle(
    color: Colors.white,
  );

  List<ArticleModel> _mArticles = List();

  @override
  void initState() {
    super.initState();
    _generateArticles();
  }

  void _generateArticles() async {
    ArticleModelProvider provider = ArticleModelProvider();
    String path = await Utils.getDataBasePath();
    await provider.open(path);
    List<ArticleModel> articles = await provider.getArticles();
    await provider.close();
    _mArticles.clear();

    setState(() {
      _mArticles.addAll(articles);
    });
  }

  void _handleClickWriteBtn(BuildContext context) {
    Navigator.push(context, MaterialPageRoute(builder: (ctx) => EditPage(-1)))
        .then((r) => _generateArticles());
  }

  @override
  Widget build(BuildContext context) {
    return ScaffoldWidget(
      child: Stack(children: <Widget>[
        ListView.builder(
          itemCount: _mArticles.length,
          itemBuilder: (context, index) {
            return DisplayItem(model: _mArticles[index]);
          },
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
