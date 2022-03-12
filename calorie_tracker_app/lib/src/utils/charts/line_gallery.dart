import 'gallery_scaffold.dart';
import 'package:flutter/material.dart';
import 'line-chart/line_chart.dart';
import 'package:calorie_tracker_app/src/utils/charts/line-chart/time_series_chart.dart';

List<GalleryScaffold> buildGallery() {
  return [
    new GalleryScaffold(
      listTileIcon: new Icon(Icons.show_chart),
      title: 'Calorie History Graph',
      subtitle: 'With a single series and default line point highlighter',
      childBuilder: () => new TimeSeriesChart.withSampleData(),
      // new SimpleLineChart.withSampleData(),
    ),
  ];
}
