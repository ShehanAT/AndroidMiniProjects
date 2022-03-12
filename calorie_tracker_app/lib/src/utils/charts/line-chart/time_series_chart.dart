import 'package:calorie_tracker_app/src/utils/charts/line-chart/line_chart.dart';
import 'package:charts_flutter/flutter.dart' as charts;
import 'package:firebase_database/firebase_database.dart';
import 'package:flutter/material.dart';

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

  static List<charts.Series<TimeSeriesSale, DateTime>>
      _createTimeSeriesSampleData() {
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

class TimeSeriesSale {
  final DateTime time;
  final int sales;

  TimeSeriesSale(this.time, this.sales);
}
