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

  Widget _addFoodButton() {
    return IconButton(
      icon: Icon(Icons.add_box),
      iconSize: 25,
      color: Colors.white,
      onPressed: () async {
        setState(() {});
        _showFoodToAdd(context);
      },
    );
  }

  _showFoodToAdd(BuildContext context) {
    return showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text(productName),
            content: _showAmountHad(),
            actions: <Widget>[
              FlatButton(
                onPressed: () => Navigator.pop(context), // passing false
                child: Text('Cancel'),
              ),
              FlatButton(
                onPressed: () async {
                  Navigator.pop(context);
                  addFoodTrack.date = _dateTimeValue;
                  await databaseService.addFoodTrackData(addFoodTrack);
                  fetchChartData();
                },
                child: Text('Ok'),
              ),
            ],
          );
        });
  }

  Widget _showAmountHad() {
    return new Scaffold(
      body: Column(children: <Widget>[
        _showAddFoodForm(),
      ]),
    );
  }

  Widget _showAddFoodForm() {
    final dateTimeFormat = DateFormat("yyyy-MM-dd");

    return Form(
      key: _addFoodKey,
      child: Column(children: [
        TextFormField(
          decoration: const InputDecoration(
              labelText: "Calories *",
              hintText: "Please enter a calorie amount"),
          validator: (value) {
            if (value == null || value.isEmpty) {
              return "Please enter a calorie amount";
            }
            return null;
          },
          onChanged: (value) {
            addFoodTrack.calories = int.parse(value);
            print(addFoodTrack.calories);
            // addFood.calories = value;
          },
        ),
        Text("Created On: "),
        DateTimeField(
          format: dateTimeFormat,
          onShowPicker: (context, currentValue) async {
            _dateTimeValue = await showDatePicker(
                    context: context,
                    initialDate: DateTime.now(),
                    firstDate: DateTime(1900),
                    lastDate: DateTime(2100)) ??
                _dateTimeValue;
            addFoodTrack.date = _dateTimeValue;
          },
        )
        // DatePicker().showDatePicker(context, )
      ]),
    );
  }

  void fetchChartData() {
    DatabaseReference _dbRef = FirebaseDatabase.instance.ref();
    addFoodTrack = new FoodTrackEntry(_dateTimeValue, 0);
    List<charts.Series<FoodTrackEntry, DateTime>> resultChartData;
    List<FoodTrackEntry> resultData = [
      new FoodTrackEntry(new DateTime(2022, 03, 11), 50),
      new FoodTrackEntry(new DateTime(2022, 03, 12), 100),
      new FoodTrackEntry(new DateTime(2022, 03, 13), 120),
      new FoodTrackEntry(new DateTime(2022, 03, 14), 150),
    ];

    _dbRef.once().then((DatabaseEvent databaseEvent) {
      final databaseValue = jsonEncode(databaseEvent.snapshot.value);
      Map<String, num> caloriesByDateMap = new Map();
      if (databaseValue != null) {
        Map<String, dynamic> jsonData = jsonDecode(databaseValue);
        var dateFormat = DateFormat("yyyy-MM-dd");

        for (var foodEntry in jsonData["foodTrack"].values) {
          var trackedDateStr =
              DateTime.parse(foodEntry["createdOn"].toString());
          DateTime dateNow = DateTime.now();
          var trackedDate = dateFormat.format(trackedDateStr);
          if (caloriesByDateMap.containsKey(trackedDate)) {
            caloriesByDateMap[trackedDate] =
                caloriesByDateMap[trackedDate]! + foodEntry["calories"];
          } else {
            caloriesByDateMap[trackedDate] = foodEntry["calories"];
          }
        }
        List<FoodTrackEntry> caloriesByDateTimeMap = [];
        for (var foodEntry in caloriesByDateMap.keys) {
          DateTime entryDateTime = DateTime.parse(foodEntry);
          caloriesByDateTimeMap.add(new FoodTrackEntry(entryDateTime,
              int.parse(caloriesByDateMap[foodEntry].toString())));
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
          new charts.Series<FoodTrackEntry, DateTime>(
              id: "Sales",
              colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
              domainFn: (FoodTrackEntry sales, _) => sales.date,
              measureFn: (FoodTrackEntry sales, _) => sales.calories,
              data: caloriesByDateTimeMap)
        ];
      } else {
        resultData = _createDateTimeSeriesData();
        resultChartData = [
          new charts.Series<FoodTrackEntry, DateTime>(
              id: "Sales",
              colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
              domainFn: (FoodTrackEntry sales, _) => sales.date,
              measureFn: (FoodTrackEntry sales, _) => sales.calories,
              data: resultData)
        ];
      }

      setState(() {
        _data = resultData;
        _chartData = resultChartData;
      });
    });
  }

  @override
  void initState() {
    // TODO: implement initState
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
  Widget build(BuildContext context) {
    // TODO: implement build
    if (_chartData != null) {
      return Scaffold(
          appBar: AppBar(
              elevation: 0,
              bottom: PreferredSize(
                preferredSize: const Size.fromHeight(5.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    // _showDatePicker(),
                    new Text("Add Food Entry"),
                    _addFoodButton(),
                  ],
                ),
              )),
          body: new Container(
            child: charts.TimeSeriesChart(_chartData!, animate: true),
          ));
      // StreamProvider<List<FoodTrackTask>>.value(
      //   initialData: [],
      //   value: new DatabaseService(
      //           uid: "calorie-tracker-b7d17", currentDate: DateTime.now())
      //       .foodTracks,
      //   child: new Column(children: <Widget>[
      //     _calorieCounter(),
      //     Expanded(
      //         child: ListView(
      //       children: <Widget>[FoodTrackList(datePicked: _value)],
      //     ))
      //   ]),
      // ));
      // return Container(
      //     child: charts.TimeSeriesChart(_chartData!, animate: true));
    } else {
      return CircularProgressIndicator();
    }
  }
}
