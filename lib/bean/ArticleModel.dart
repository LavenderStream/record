import 'package:sqflite/sqflite.dart';

final String tableName = 't_article';

final String columnId = '_id';
final String columnTime = 'time';
final String columnTitle = 'title';
final String columnContent = 'content';
final String columnLocation = 'location';

class ArticleModel {
  int id;
  String title = "";
  String location = "";
  String content = "";
  String time = "";

  ArticleModel();

  Map<String, dynamic> toMap() {
    var map = <String, dynamic>{
      columnTime: time,
      columnTitle: title,
      columnContent: content,
      columnLocation: location,
    };
    if (id != null) {
      map[columnId] = id;
    }
    return map;
  }

  ArticleModel.fromMap(Map<String, dynamic> map) {
    id = map[columnId];
    time = map[columnTime];
    title = map[columnTitle];
    content = map[columnContent];
    location = map[columnLocation];
  }
}

class ArticleModelProvider {
  Database db;

  Future open(String path) async {
    db = await openDatabase(path, version: 1,
        onCreate: (Database db, int version) async {
      await db.execute('''create table $tableName ( 
        $columnId integer primary key autoincrement, 
        $columnTime text,
        $columnTitle text,
        $columnContent text,
        $columnLocation text)''');
    });
  }

  Future<ArticleModel> insert(ArticleModel model) async {
    model.id = await db.insert(tableName, model.toMap());
    return model;
  }

  Future<List<ArticleModel>> getArticles() async {
    List<Map> maps = await db.query(tableName, columns: [
      columnId,
      columnTime,
      columnTitle,
      columnContent,
      columnLocation
    ]);
    if (maps.length > 0) {
      return List<ArticleModel>.generate(
          maps.length, (i) => ArticleModel.fromMap(maps[i]));
    }
    return List(0);
  }

  Future<ArticleModel> getArticle(int id) async {
    List<Map> maps = await db.query(tableName,
        columns: [
          columnId,
          columnTime,
          columnTitle,
          columnContent,
          columnLocation
        ],
        where: '$columnId = ?',
        whereArgs: [id]);
    if (maps.length > 0) {
      return ArticleModel.fromMap(maps.first);
    }
    return ArticleModel();
  }

  Future<int> delete(int id) async {
    return await db.delete(tableName, where: '$columnId = ?', whereArgs: [id]);
  }

  Future<int> update(ArticleModel todo) async {
    return await db.update(tableName, todo.toMap(),
        where: '$columnId = ?', whereArgs: [todo.id]);
  }

  Future close() async => db.close();
}
