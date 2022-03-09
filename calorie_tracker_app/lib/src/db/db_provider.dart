import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart' as p;
import 'package:path_provider/path_provider.dart';
import 'dart:io';

import 'package:calorie_tracker_app/src/model/food_model.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';

class DBProvider {
  static Database? _database;

  DBProvider._();
  static final DBProvider db = DBProvider._();

  var foodList = [
    Food("Oatmeal", color: "white"),
    Food("Sandwich", color: "white"),
    Food("Blueberry", color: "blue"),
    Food("Apple", color: "red"),
    Food("Pasta", color: "yellow")
  ];

  get _dbPath async {
    String documentsDirectory = await _localPath;
    return p.join(documentsDirectory, "FoodList.db");
  }

  Future<String> get _localPath async {
    final directory = await getApplicationDocumentsDirectory();
    return directory.path;
  }

  Future<bool> dbExists() async {
    return File(await _dbPath).exists();
  }

  Future<Database> get database async {
    return _database ?? await initDB();
  }

  // Future<List<FoodTrackTask>> getFoodList() async {
  //   final db = await database;
  //   var result = await db.query("Foodlist");
  //   return result.map((it) => FoodTrackTask.fromJson(it)).toList();
  // }

  insertBulkFoodTrackTask(List<Food> foods) async {
    final db = await database;
    foods.forEach((it) async {
      var res = await db.insert("Food", it.toJson());
      print("Food item: ${it.id} = $res");
    });
  }

  initDB() async {
    String path = await _dbPath;
    return await openDatabase(path, version: 1, onOpen: (db) {},
        onCreate: (Database db, int version) async {
      print("DBProvider:: onCreate()");
      await db.execute("CREATE TABLE Food("
          "id TEXT PRIMARY KEY,"
          "name TEXT,"
          "color TEXT,"
          ")");
      await db.execute("CREATE TABLE FoodTrackTask("
          "id TEXT PRIMARY KEY,"
          "foodId INTEGER,"
          "FOREIGN KEY(foodId) REFERENCES Food(id)");
    });
  }
}
