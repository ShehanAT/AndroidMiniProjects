import 'package:charts_flutter/flutter.dart' as charts;
import 'package:firebase_database/firebase_database.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:intl/intl.dart';
import 'package:calorie_tracker_app/src/services/database.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';
import 'package:calorie_tracker_app/src/model/food-track-entry.dart';

class DateTimeChart extends StatefulWidget {
  @override
  _DateTimeChart createState() => _DateTimeChart();

  // static List<charts.Series<TimeSeriesSale, DateTime>>;
  // createDateTimeSeriesData() => _DateTimeChart._createDateTimeSeriesData();
}

class _DateTimeChart extends State<DateTimeChart> {
  // late List<TimeSeriesSale>? _data = null;
  List<charts.Series<FoodTrackEntry, DateTime>>? resultChartData = null;
  DatabaseService databaseService = new DatabaseService(
      uid: "calorie-tracker-b7d17", currentDate: DateTime.now());

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    getAllFoodTrackData();

    // DatabaseReference _dbRef = FirebaseDatabase.instance.ref();

    // List<charts.Series<TimeSeriesSale, DateTime>> resultChartData;
    // List<TimeSeriesSale> resultData = [
    //   new TimeSeriesSale(new DateTime(2022, 03, 11), 50),
    //   new TimeSeriesSale(new DateTime(2022, 03, 12), 100),
    //   new TimeSeriesSale(new DateTime(2022, 03, 13), 120),
    //   new TimeSeriesSale(new DateTime(2022, 03, 14), 150),
    // ];
    // _dbRef.once().then((DatabaseEvent databaseEvent) {
    // final Map<dynamic, dynamic> data =
    // databaseEvent.snapshot.key;
    // final databaseValue = jsonEncode(databaseEvent.snapshot.value);
    // ? databaseEvent.snapshot.value["foodTrack"].toString()
    // : databaseEvent.snapshot.value.toString();
    // jsonDecode(databaseEvent.snapshot.value["foodTrack"]);
    // final foodTrackEntries = databaseSnapshot["foodTrack"];
    // Map<String, int> caloriesByDateMap = new Map();
    // if (databaseValue != null) {
    //   Map<String, dynamic> jsonData = jsonDecode(databaseValue);
    //   var dateFormat = DateFormat("yyyy-MM-dd");
    //   for (var foodEntry in jsonData["foodTrack"].values) {
    //     var trackedDateStr =
    //         DateTime.parse(foodEntry["createdOn"].toString());
    //     DateTime dateNow = DateTime.now();
    //     var trackedDate = dateFormat.format(trackedDateStr);
    //     if (caloriesByDateMap.containsKey(trackedDate)
    //         // &&
    //         // dateNow.difference(trackedDate).inDays != 0
    //         ) {
    //       caloriesByDateMap[trackedDate] = caloriesByDateMap[trackedDate]! +
    //           int.parse(foodEntry["calories"]);
    //     } else {
    //       caloriesByDateMap[trackedDate] = int.parse(foodEntry["calories"]);
    //     }
    //   }
    //   // print(caloriesByDateMap);
    //   List<TimeSeriesSale> caloriesByDateTimeMap = [];
    //   for (var foodEntry in caloriesByDateMap.keys) {
    //     // print(foodEntry + " 00:00:00");
    //     DateTime entryDateTime = DateTime.parse(foodEntry);
    //     // print(foodEntry);
    //     caloriesByDateTimeMap.add(
    //         new TimeSeriesSale(entryDateTime, caloriesByDateMap[foodEntry]!));
    //   }

    //   caloriesByDateTimeMap.sort((a, b) {
    //     int aDate = a.date.microsecondsSinceEpoch;
    //     int bDate = b.date.microsecondsSinceEpoch;

    //     return aDate.compareTo(bDate);
    //   });

    //     resultData = caloriesByDateTimeMap;
    //     // print(caloriesByDateTimeMap);
    //     return caloriesByDateTimeMap;
    //   } else {
    //     print("databaseSnapshot key is NULL");
    //     return null;
    //   }
    // }).then((caloriesByDateTimeMap) {
    //   // print("passing");
    //   print(caloriesByDateTimeMap);
    //   if (caloriesByDateTimeMap != null) {
    //     resultChartData = [
    //       new charts.Series<TimeSeriesSale, DateTime>(
    //           id: "Sales",
    //           colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
    //           domainFn: (TimeSeriesSale sales, _) => sales.date,
    //           measureFn: (TimeSeriesSale sales, _) => sales.sales,
    //           data: caloriesByDateTimeMap)
    //     ];
    //   } else {
    //     resultChartData = [
    //       new charts.Series<TimeSeriesSale, DateTime>(
    //           id: "Sales",
    //           colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
    //           domainFn: (TimeSeriesSale sales, _) => sales.date,
    //           measureFn: (TimeSeriesSale sales, _) => sales.sales,
    //           data: resultData)
    //     ];
    //     // return result;
    //   }

    //   setState(() {
    //     _data = resultData;
    //   });
    // });
  }

  void getAllFoodTrackData() async {
    Stream<List<FoodTrackTask>> result = databaseService.foodTracks;
    List<dynamic> foodTrackResults =
        await databaseService.getAllFoodTrackData();
    // print("pasing getAllFoodTrackData()");
    List<FoodTrackEntry> foodTrackEntries = [];

    for (var foodTrack in foodTrackResults) {
      if (foodTrack["createdOn"] != null) {
        foodTrackEntries.add(FoodTrackEntry(
            foodTrack["createdOn"].toDate(), foodTrack["calories"]));
      }
    }
    populateChartWithEntries(foodTrackEntries);
    // return foodTrackEntries;
    // String foodTrackStr = json.decode(foodTrackResults);
    // print(foodTrackStr);
    // String jsonResult = json.encode(foodTrackResults);
    // print(jsonResult);
    // for (var i = 0; i < foodTrackResults.length; i++) {
    //   print(foodTrackResults[i].toString());
    // }
  }

  void populateChartWithEntries(List<FoodTrackEntry> foodTrackEntries) async {
    // resultChartData =
    Map<String, int> caloriesByDateMap = new Map();
    if (foodTrackEntries != null) {
      var dateFormat = DateFormat("yyyy-MM-dd");
      for (var foodEntry in foodTrackEntries) {
        var trackedDateStr = foodEntry.date;
        DateTime dateNow = DateTime.now();
        var trackedDate = dateFormat.format(trackedDateStr);
        if (caloriesByDateMap.containsKey(trackedDate)
            // &&
            // dateNow.difference(trackedDate).inDays != 0
            ) {
          caloriesByDateMap[trackedDate] =
              caloriesByDateMap[trackedDate]! + foodEntry.calories;
        } else {
          caloriesByDateMap[trackedDate] = foodEntry.calories;
        }
      }
      // print(caloriesByDateMap);
      List<FoodTrackEntry> caloriesByDateTimeMap = [];
      for (var foodEntry in caloriesByDateMap.keys) {
        // print(foodEntry + " 00:00:00");
        DateTime entryDateTime = DateTime.parse(foodEntry);
        // print(foodEntry);
        caloriesByDateTimeMap.add(
            new FoodTrackEntry(entryDateTime, caloriesByDateMap[foodEntry]!));
      }

      caloriesByDateTimeMap.sort((a, b) {
        int aDate = a.date.microsecondsSinceEpoch;
        int bDate = b.date.microsecondsSinceEpoch;

        return aDate.compareTo(bDate);
      });
      setState(() {
        resultChartData = [
          new charts.Series<FoodTrackEntry, DateTime>(
              id: "Food Track Entries",
              colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
              domainFn: (FoodTrackEntry foodTrackEntry, _) =>
                  foodTrackEntry.date,
              measureFn: (FoodTrackEntry foodTrackEntry, _) =>
                  foodTrackEntry.calories,
              data: caloriesByDateTimeMap)
        ];
      });
    }
  }

  // static List<charts.Series<TimeSeriesSale, DateTime>>
  //     _createDateTimeSeriesData() {
  //   List<TimeSeriesSale> resultData = [
  //     new TimeSeriesSale(new DateTime(2022, 03, 11), 50),
  //     new TimeSeriesSale(new DateTime(2022, 03, 12), 100),
  //     new TimeSeriesSale(new DateTime(2022, 03, 13), 120),
  //     new TimeSeriesSale(new DateTime(2022, 03, 14), 150),
  //   ];

  //   return _chartData!;
  //   // }else{
  //   //   return [
  //   //   new charts.Series<TimeSeriesSale, DateTime>(
  //   //       id: "Sales",
  //   //       colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
  //   //       domainFn: (TimeSeriesSale sales, _) => sales.time,
  //   //       measureFn: (TimeSeriesSale sales, _) => sales.sales,
  //   //       data: resultData)
  //   // ];
  // }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    if (resultChartData != null) {
      return Container(
          child: charts.TimeSeriesChart(resultChartData!, animate: true));
    } else {
      return CircularProgressIndicator();
    }
  }
  // @override
  // Widget build(BuildContext context) {
  //   return Container();
  // }

}
