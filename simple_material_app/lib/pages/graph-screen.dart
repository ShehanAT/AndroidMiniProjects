import 'package:flutter/material.dart';
import 'package:simple_material_app/utils/datetime-chart.dart';
import 'package:simple_material_app/utils/gallery-scaffold.dart';

class GraphScreen extends StatefulWidget {
  GraphScreen();

  @override
  State<StatefulWidget> createState() {
    return _GraphScreenState();
  }
}

class _GraphScreenState extends State<GraphScreen> {
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  bool _isBack = true;
  // final List<GalleryScaffold> lineGallery = buildGallery();
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
    // var galleries = <Widget>[];

    // galleries.addAll(
    //     lineGallery.map((gallery) => gallery.buildGalleryListTile(context)));

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
