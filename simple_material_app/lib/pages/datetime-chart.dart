import 'package:charts_flutter/flutter.dart' as charts;
import 'package:firebase_database/firebase_database.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:intl/intl.dart';
import 'package:simple_material_app/models/food-track-entry.dart';
import 'package:simple_material_app/utils/database-service.dart';
import 'package:datetime_picker_formfield/datetime_picker_formfield.dart';

class DateTimeChart extends StatefulWidget {
  @override
  _DateTimeChart createState() => _DateTimeChart();
}

class _DateTimeChart extends State<DateTimeChart> {
  late List<FoodTrackEntry>? _data = null;
  static List<charts.Series<FoodTrackEntry, DateTime>>? _chartData = null;
  String productName = 'Add Food';
  late FoodTrackEntry addFoodTrack;
  DateTime _dateTimeValue = DateTime.now();
  final _addFoodKey = GlobalKey<FormState>();
  DatabaseService databaseService = new DatabaseService();

  Widget _addFoodButton() {}

  _showFoodToAdd(BuildContext context) {}

  Widget _showAmountHad() {}

  Widget _showAddFoodForm() {}

  void fetchChartData() {}

  @override
  void initState() {
    super.initState();

    fetchChartData();
  }

  static List<FoodTrackEntry> _createDateTimeSeriesData() {
    List<FoodTrackEntry> resultData = [
      new FoodTrackEntry(new DateTime(2022, 03, 11), 50),
      new FoodTrackEntry(new DateTime(2022, 03, 12), 100),
      new FoodTrackEntry(new DateTime(2022, 03, 13), 120),
      new FoodTrackEntry(new DateTime(2022, 03, 14), 150),
    ];

    return resultData;
  }

  @override
  Widget build(BuildContext context) {}
}
