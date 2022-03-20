import 'package:charts_flutter/flutter.dart' as charts;
import 'package:firebase_database/firebase_database.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:simple_material_app/models/food-track-entry.dart';

class DatabaseService {
  DatabaseService();

  DatabaseReference _foodTrackRef =
      FirebaseDatabase.instance.ref().child("foodTrack");
  final DateTime today =
      DateTime(DateTime.now().year, DateTime.now().month, DateTime.now().day);

  Future addFoodTrackData(FoodTrackEntry foodTrackEntry) async {
    return await _foodTrackRef.push().set({
      'calories': foodTrackEntry.calories,
      'createdOn': foodTrackEntry.date.toString()
    });
  }
}
