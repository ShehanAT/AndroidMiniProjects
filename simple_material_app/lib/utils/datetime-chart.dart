import 'package:charts_flutter/flutter.dart' as charts;
import 'package:firebase_database/firebase_database.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:intl/intl.dart';

class DateTimeChart extends StatefulWidget {
  @override
  _DateTimeChart createState() => _DateTimeChart();

  static List<charts.Series<TimeSeriesSale, DateTime>>
      createDateTimeSeriesData() => _DateTimeChart._createDateTimeSeriesData();
}

class _DateTimeChart extends State<DateTimeChart> {
  late List<TimeSeriesSale>? _data = null;
  static List<charts.Series<TimeSeriesSale, DateTime>>? _chartData = null;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    DatabaseReference _dbRef = FirebaseDatabase.instance.ref();
    List<charts.Series<TimeSeriesSale, DateTime>> resultChartData;
    List<TimeSeriesSale> resultData = [
      new TimeSeriesSale(new DateTime(2022, 03, 11), 50),
      new TimeSeriesSale(new DateTime(2022, 03, 12), 100),
      new TimeSeriesSale(new DateTime(2022, 03, 13), 120),
      new TimeSeriesSale(new DateTime(2022, 03, 14), 150),
    ];
    _dbRef.once().then((DatabaseEvent databaseEvent) {
      final databaseValue = jsonEncode(databaseEvent.snapshot.value);
      Map<String, int> caloriesByDateMap = new Map();
      if (databaseValue != null) {
        Map<String, dynamic> jsonData = jsonDecode(databaseValue);
        var dateFormat = DateFormat("yyyy-MM-dd");
        for (var foodEntry in jsonData["foodTrack"].values) {
          var trackedDateStr =
              DateTime.parse(foodEntry["createdOn"].toString());
          DateTime dateNow = DateTime.now();
          var trackedDate = dateFormat.format(trackedDateStr);
          if (caloriesByDateMap.containsKey(trackedDate)) {
            caloriesByDateMap[trackedDate] = caloriesByDateMap[trackedDate]! +
                int.parse(foodEntry["calories"]);
          } else {
            caloriesByDateMap[trackedDate] = int.parse(foodEntry["calories"]);
          }
        }
        List<TimeSeriesSale> caloriesByDateTimeMap = [];
        for (var foodEntry in caloriesByDateMap.keys) {
          DateTime entryDateTime = DateTime.parse(foodEntry);
          caloriesByDateTimeMap.add(
              new TimeSeriesSale(entryDateTime, caloriesByDateMap[foodEntry]!));
        }

        caloriesByDateTimeMap.sort((a, b) {
          int aDate = a.date.microsecondsSinceEpoch;
          int bDate = b.date.microsecondsSinceEpoch;

          return aDate.compareTo(bDate);
        });

        resultData = caloriesByDateTimeMap;
        return caloriesByDateTimeMap;
      } else {
        print("databaseSnapshot key is NULL");
        return null;
      }
    }).then((caloriesByDateTimeMap) {
      print(caloriesByDateTimeMap);
      if (caloriesByDateTimeMap != null) {
        resultChartData = [
          new charts.Series<TimeSeriesSale, DateTime>(
              id: "Sales",
              colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
              domainFn: (TimeSeriesSale sales, _) => sales.date,
              measureFn: (TimeSeriesSale sales, _) => sales.sales,
              data: caloriesByDateTimeMap)
        ];
      } else {
        resultChartData = [
          new charts.Series<TimeSeriesSale, DateTime>(
              id: "Sales",
              colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
              domainFn: (TimeSeriesSale sales, _) => sales.date,
              measureFn: (TimeSeriesSale sales, _) => sales.sales,
              data: resultData)
        ];
      }

      setState(() {
        _data = resultData;
        _chartData = resultChartData;
      });
    });
  }

  static List<charts.Series<TimeSeriesSale, DateTime>>
      _createDateTimeSeriesData() {
    List<TimeSeriesSale> resultData = [
      new TimeSeriesSale(new DateTime(2022, 03, 11), 50),
      new TimeSeriesSale(new DateTime(2022, 03, 12), 100),
      new TimeSeriesSale(new DateTime(2022, 03, 13), 120),
      new TimeSeriesSale(new DateTime(2022, 03, 14), 150),
    ];

    return _chartData!;
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    if (_chartData != null) {
      return Container(
          child: charts.TimeSeriesChart(_chartData!, animate: true));
    } else {
      return CircularProgressIndicator();
    }
  }
}

class TimeSeriesSale {
  final DateTime date;
  final int sales;

  TimeSeriesSale(this.date, this.sales);
}
