import 'package:flutter/material.dart';
import 'package:calorie_tracker_app/src/model/scoped/food_list_model.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';
import 'package:calorie_tracker_app/src/model/food_model.dart';
import 'package:calorie_tracker_app/component/colorpicker/color_picker_builder.dart';
import 'package:calorie_tracker_app/component/iconpicker/icon_picker_builder.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:calorie_tracker_app/src/utils/charts/line-chart/line_chart.dart';
import 'package:calorie_tracker_app/src/utils/charts/line_gallery.dart';
import 'package:calorie_tracker_app/src/utils/charts/gallery_scaffold.dart';
import 'package:calorie_tracker_app/src/utils/charts/line-chart/datetime_series_chart.dart';

class HistoryScreen extends StatefulWidget {
  HistoryScreen();

  @override
  State<StatefulWidget> createState() {
    return _HistoryScreenState();
  }
}

class _HistoryScreenState extends State<HistoryScreen> {
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  bool _isBack = true;
  final List<GalleryScaffold> lineGallery = buildGallery();
  @override
  void initState() {
    super.initState();
  }

  void onClickBackButton() {
    print("Back Button");
    Navigator.of(context).pop();
  }

  @override
  Widget build(BuildContext context) {
    var galleries = <Widget>[];

    galleries.addAll(
        lineGallery.map((gallery) => gallery.buildGalleryListTile(context)));

    return Scaffold(
        appBar: AppBar(
          title: Text(
            "Calorie Tracker App",
            style: TextStyle(
                color: Colors.black, fontSize: 20, fontWeight: FontWeight.bold),
          ),
        ),
        body: Container(
          child: DateTimeChart(),
          // DateTimeChart(),
          // DateTimeChart()
          // galleries
          //   new ListTile(
          //       leading: const Icon(Icons.food_bank),
          //       title: new Text(
          //           "Oatmeal, calories: 300cal, Carbs: 30g, Fat: 10g, Protein: 5g")),
          //   new ListTile(
          //       leading: const Icon(Icons.food_bank),
          //       title: new Text(
          //           "Burrito, calories: 400cal, Carbs: 30g, Fat: 10g, Protein: 5g")),
          //   new ListTile(
          //       leading: const Icon(Icons.food_bank),
          //       title: new Text(
          //           "Pasta, calories: 350cal, Carbs: 40g, Fat: 10g, Protein: 5g")),
          //   new ListTile(
          //       leading: const Icon(Icons.food_bank),
          //       title: new Text(
          //           "Hummus, calories: 300cal, Carbs: 30g, Fat: 10g, Protein: 5g")),
          //   new ListTile(
          //       leading: const Icon(Icons.food_bank),
          //       title: new Text(
          //           "Mashed Potates, calories: 300cal, Carbs: 30g, Fat: 10g, Protein: 5g")),
          //   // SimpleLineChart(SimpleLineChart._createSampleData(),
          //   //     animate: false),
          //   SimpleLineChart.withSampleData()
          // ],
        ));
  }
}
