// import 'package:calorie_tracker_app/src/utils/charts/line-chart/line_chart.dart';
import 'package:charts_flutter/flutter.dart' as charts;
import 'package:firebase_database/firebase_database.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
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
        var dateFormat = DateFormat("yyyy-MM-dd");
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
        // print(caloriesByDateMap);
        List<TimeSeriesSale> caloriesByDateTimeMap = [];
        for (var foodEntry in caloriesByDateMap.keys) {
          // print(foodEntry + " 00:00:00");
          DateTime entryDateTime = DateTime.parse(foodEntry);
          // print(foodEntry);
          caloriesByDateTimeMap.add(
              new TimeSeriesSale(entryDateTime, caloriesByDateMap[foodEntry]!));
        }

        caloriesByDateTimeMap.sort((a, b) {
          int aDate = a.date.microsecondsSinceEpoch;
          int bDate = b.date.microsecondsSinceEpoch;

          return aDate.compareTo(bDate);
        });

        resultData = caloriesByDateTimeMap;
        // print(caloriesByDateTimeMap);
        return caloriesByDateTimeMap;
      } else {
        print("databaseSnapshot key is NULL");
        return null;
      }
    }).then((caloriesByDateTimeMap) {
      // print("passing");
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
        // return result;
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
    // }else{
    //   return [
    //   new charts.Series<TimeSeriesSale, DateTime>(
    //       id: "Sales",
    //       colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
    //       domainFn: (TimeSeriesSale sales, _) => sales.time,
    //       measureFn: (TimeSeriesSale sales, _) => sales.sales,
    //       data: resultData)
    // ];
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
  // @override
  // Widget build(BuildContext context) {
  //   return Container();
  // }

}

class TimeSeriesSale {
  final DateTime date;
  final int sales;

  TimeSeriesSale(this.date, this.sales);
}
