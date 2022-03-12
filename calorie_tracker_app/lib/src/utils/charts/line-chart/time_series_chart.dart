import 'package:calorie_tracker_app/src/utils/charts/line-chart/line_chart.dart';
import 'package:charts_flutter/flutter.dart' as charts;
import 'package:firebase_database/firebase_database.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:intl/intl.dart';

class TimeSeriesChart extends StatelessWidget {
  final List<charts.Series> seriesList;
  final bool animate;

  TimeSeriesChart(this.seriesList, {required this.animate});

  DatabaseReference foodTrackRef = FirebaseDatabase.instance.ref();

  @override
  Widget build(BuildContext context) {
    return new charts.TimeSeriesChart(
      _createTimeSeriesSampleData(),
      animate: false,
      dateTimeFactory: const charts.LocalDateTimeFactory(),
      domainAxis: charts.DateTimeAxisSpec(
        tickFormatterSpec: charts.AutoDateTimeTickFormatterSpec(
          day: charts.TimeFormatterSpec(
            format: "dd",
            transitionFormat: "dd MMM",
          ),
        ),
      ),
    );
  }

  factory TimeSeriesChart.withSampleData() {
    return new TimeSeriesChart(
      _createTimeSeriesSampleData(),
      animate: false,
    );
  }

  void fetchData() {}

  static List<charts.Series<TimeSeriesSale, DateTime>>
      _createTimeSeriesSampleData() {
    DatabaseReference _dbRef = FirebaseDatabase.instance.ref();
    _dbRef.once().then((DatabaseEvent databaseEvent) {
      // final Map<dynamic, dynamic> data =
      // databaseEvent.snapshot.key;
      final databaseValue = jsonEncode(databaseEvent.snapshot.value);
      // ? databaseEvent.snapshot.value["foodTrack"].toString()
      // : databaseEvent.snapshot.value.toString();
      // jsonDecode(databaseEvent.snapshot.value["foodTrack"]);
      // final foodTrackEntries = databaseSnapshot["foodTrack"];
      Map<String, int> caloriesByDateMap = new Map();
      if (databaseValue != null) {
        Map<String, dynamic> jsonData = jsonDecode(databaseValue);
        var dateFormat = DateFormat("MM/dd/yyyy");
        for (var foodEntry in jsonData["foodTrack"].values) {
          var trackedDateStr =
              DateTime.parse(foodEntry["createdOn"].toString());
          DateTime dateNow = DateTime.now();
          var trackedDate = dateFormat.format(trackedDateStr);
          if (caloriesByDateMap.containsKey(trackedDate)
              // &&
              // dateNow.difference(trackedDate).inDays != 0
              ) {
            caloriesByDateMap[trackedDate] = caloriesByDateMap[trackedDate]! +
                int.parse(foodEntry["calories"]);
          } else {
            caloriesByDateMap[trackedDate] = int.parse(foodEntry["calories"]);
          }
        }
        print(caloriesByDateMap);
      } else {
        print("databaseSnapshot key is NULL");
      }
    });
    // NEED TO FIND WAY TO FETCH ALL FOODTRACKTASK INSTANCES FROM FIREBASE
    // foodTrackRef.

    final data = [
      new TimeSeriesSale(new DateTime(2022, 03, 11), 50),
      new TimeSeriesSale(new DateTime(2022, 03, 12), 100),
      new TimeSeriesSale(new DateTime(2022, 03, 13), 120),
      new TimeSeriesSale(new DateTime(2022, 03, 14), 150),
    ];

    return [
      new charts.Series<TimeSeriesSale, DateTime>(
          id: "Sales",
          colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
          domainFn: (TimeSeriesSale sales, _) => sales.time,
          measureFn: (TimeSeriesSale sales, _) => sales.sales,
          data: data)
    ];
  }
}

class CaloriesByDate {
  final DateTime time;
  final int totalCalories;

  CaloriesByDate(this.time, this.totalCalories);
}

class TimeSeriesSale {
  final DateTime time;
  final int sales;

  TimeSeriesSale(this.time, this.sales);
}
