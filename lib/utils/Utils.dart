import 'dart:async';
import 'dart:io';

import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';

class Utils {
  static final numFormat = [
    '零',
    '一',
    '二',
    '三',
    '四',
    '五',
    '六',
    '七',
    '八',
    '九',
    '十'
  ];

  static String coverDate2Chinese() {
    var thisInstant = new DateTime.now();

    int d = thisInstant.weekday;
    String week = "星期" + "日一二三四五六".substring(d, (d + 1));

    String year = "";
    int i = 0;
    while (++i <= thisInstant.year.toString().length) {
      String index = thisInstant.year.toString().substring(i - 1, i);
      year += numFormat[int.parse(index)];
    }

    String month = "";
    i = 0;
    while (++i <= thisInstant.month.toString().length) {
      String index = thisInstant.month.toString().substring(i - 1, i);
      month += numFormat[int.parse(index)];
    }

    String day = "";
    i = 0;
    while (++i <= thisInstant.day.toString().length) {
      String index = thisInstant.day.toString().substring(i - 1, i);
      day += numFormat[int.parse(index)];
    }

    return year + "年" + month + "月" + day + "日 " + week;
  }

  static Future<String> getDataBasePath() async {
    Directory directory = await getApplicationDocumentsDirectory();
    return join(directory.path, "record.db");
  }
}
