import 'gallery-scaffold.dart';
import 'package:flutter/material.dart';
import 'datetime-series-chart.dart';
// import 'line-chart.dart';
// import 'package:calorie_tracker_app/src/utils/charts/line-chart/time_series_chart.dart';
// import '../../android/lib/utils/line-chart/datetime_series_chart.dart';

List<GalleryScaffold> buildGallery() {
  return [
    new GalleryScaffold(
      listTileIcon: new Icon(Icons.show_chart),
      title: 'Calorie History Graph',
      subtitle: 'With a single series and default line point highlighter',
      childBuilder: () =>
          // new DateTimeChart(DateTimeChart.createDateTimeSeriesData()),
      // new TimeSeriesChart.withSampleData(),
      // new SimpleLineChart.withSampleData(),
    ),
  ];
}
