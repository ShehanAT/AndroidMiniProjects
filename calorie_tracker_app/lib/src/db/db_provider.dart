import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';
import 'dart:io';

import 'package:calorie_tracker_app/src/model/food_model.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';

class DBProvider {
  static Database? _database;

  DBProvider._();
  static final DBProvider db = DBProvider._();

  var foodList = [
    Food("Oatmeal", color: "white", codePoint: 01),
    Food("Sandwich", color: "white", codePoint: 02),
    Food("Blueberry", color: "blue", codePoint: 03),
    Food("Apple", color: "red", codePoint: 04),
    Food("Pasta", color: "yellow", codePoint: 05)
  ];
}
