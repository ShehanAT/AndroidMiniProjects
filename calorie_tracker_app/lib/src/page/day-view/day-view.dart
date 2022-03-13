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
import 'calorie-stats.dart';
import 'package:provider/provider.dart';


class DayViewScreen extends StatefulWidget {
  DayViewScreen();

  @override
  State<StatefulWidget> createState() {
    return _DayViewState();
  }
}

class _DayViewState extends State<DayViewScreen> {
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  bool _isBack = true;
  final List<GalleryScaffold> lineGallery = buildGallery();

  @override
  void initState() {
    super.initState();
  }

  void onClickBackButton() {}

    Widget _calorieCounter() {
      return Padding(
        padding: EdgeInsets.fromLTRB(0.0, 0.0, 0.0, 0.0),
        child: new Container(
          decoration: BoxDecoration(
            color: Colors.white,
            border: Border(
              bottom: BorderSide(
                color: Colors.grey.withOpacity(0.5),
                width: 1.5,
              )
            )
          ),
          height: 220,
          child: Row(
            children: <Widget>[
              CalorieStats(datePicked: DateTime.now()),
            ],
          ),
        ),
      );
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
        body: StreamProvider<List<Scan>>.value(
          initialData: [],
          value: null,
          child: new Column(children: <Widget>[
            _calorieCounter(),
            Expanded(
              child: ListView(
                children: <Widget>[
                  ScanList(datePicked: DateTime.now())
                ],
              ))

          ]),
        
          // child: CalorieStats(datePicked: DateTime.now()),
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



class ScanList extends StatelessWidget {

  final DateTime datePicked;
  ScanList({required this.datePicked});

  @override
  Widget build(BuildContext context) {
    print("RENDERING SCANLIST!!!");
    final DateTime curDate = new DateTime(datePicked.year, datePicked.month, datePicked.day);

    final scans = Provider.of<List<Scan>>(context);

    List findCurScans(List scansFeed) {
      List curScans = [];
      scansFeed.forEach((scan) {
        DateTime scanDate = DateTime(scan.dateTime.toDate().year, scan.dateTime.toDate().month, scan.dateTime.toDate().day);
        if (scanDate.compareTo(curDate) == 0) {
          curScans.add(scan);
        }
      });
      return curScans;
    }

    List curScans = findCurScans(scans);

    return ListView.builder(
      scrollDirection: Axis.vertical,
      physics: ClampingScrollPhysics(),
      shrinkWrap: true,
      itemCount: curScans.length + 1,
      itemBuilder: (context, index) {
        if (index < curScans.length) {
          return ScanTile(scan: curScans[index]);
        } else {
          return SizedBox(height: 5);
        }
        },
    );
  }
}


class ScanTile extends StatelessWidget {

  final Scan scan;
  ScanTile({required this.scan});

  List macros = CalorieStats.macroData;

  @override
  Widget build(BuildContext context) {
        return ExpansionTile(
          leading: CircleAvatar(
            radius: 25.0,
            backgroundColor: Color(0xff5FA55A),
            child: _itemCalories(),
          ),
          title: Text(scan.productName, style: TextStyle(
            fontSize: 16.0,
            fontFamily: 'Open Sans',
            fontWeight: FontWeight.w500,
          )),
          subtitle: _macroData(),
          children: <Widget>[
            _expandedView(context),
          ],
        );
  }

  Widget _itemCalories() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Text(scan.productCalories.toStringAsFixed(0),
            style: TextStyle(
              fontSize: 16.0,
              color: Colors.white,
              fontFamily: 'Open Sans',
              fontWeight: FontWeight.w500,
            )
        ),
        Text('kcal',
            style: TextStyle(
              fontSize: 10.0,
              color: Colors.white,
              fontFamily: 'Open Sans',
              fontWeight: FontWeight.w500,
            )
        ),
      ],
    );
  }

  Widget _macroData() {
    return Row(
      children: <Widget>[
        Container(
          width: 200,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Row(
                children: <Widget>[
                  Container(
                    height: 8,
                    width: 8,
                    decoration: BoxDecoration(
                      color: Color(0xffFA5457),
                      shape: BoxShape.circle,
                    ),
                  ),
                  Text(' ' + scan.productCarbs.toStringAsFixed(1) + 'g    ',
                      style: TextStyle(
                        fontSize: 12.0,
                        color: Colors.black,
                        fontFamily: 'Open Sans',
                        fontWeight: FontWeight.w400,
                      )
                  ),
                  Container(
                    height: 8,
                    width: 8,
                    decoration: BoxDecoration(
                      color: Color(0xffFA8925),
                      shape: BoxShape.circle,
                    ),
                  ),
                  Text(' ' + scan.productProtein.toStringAsFixed(1) + 'g    ',
                      style: TextStyle(
                        fontSize: 12.0,
                        color: Colors.black,
                        fontFamily: 'Open Sans',
                        fontWeight: FontWeight.w400,
                      )
                  ),
                  Container(
                    height: 8,
                    width: 8,
                    decoration: BoxDecoration(
                      color: Color(0xff01B4BC),
                      shape: BoxShape.circle,
                    ),
                  ),
                  Text(' ' + scan.productFat.toStringAsFixed(1) + 'g',
                      style: TextStyle(
                        fontSize: 12.0,
                        color: Colors.black,
                        fontFamily: 'Open Sans',
                        fontWeight: FontWeight.w400,
                      )
                  ),
                ],
              ),
              // Text(scan.grams.toString() + 'g',
              //     style: TextStyle(
              //       fontSize: 12.0,
              //       color: Colors.black,
              //       fontFamily: 'Open Sans',
              //       fontWeight: FontWeight.w300,
              //     )
              // ),
            ],
          ),
        )
      ],
    );
  }

  Widget _expandedView(BuildContext context) {
    return Padding(
      padding: EdgeInsets.fromLTRB(20.0, 0.0, 15.0, 0.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          expandedHeader(context),
          _expandedCalories(),
          _expandedCarbs(),
          _expandedProtein(),
          _expandedFat(),
        ],
      ),
    );
  }

  Widget expandedHeader(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: <Widget>[
        Text('% of total',
            style: TextStyle(
              fontSize: 14.0,
              color: Colors.black,
              fontFamily: 'Open Sans',
              fontWeight: FontWeight.w400,
            )
        ),
      //   IconButton(
      //     icon: Icon(Icons.edit),
      //     iconSize: 16,
      //     onPressed: () {
      //       Navigator.push(
      //           context,
      //           MaterialPageRoute(builder: (context) => EditItem(scan: scan)),
      //       );
      //     }
      //   )
      // ],
    );
  }

  Widget _expandedCalories() {
    return Padding(
      padding: EdgeInsets.fromLTRB(0.0, 0.0, 0.0, 0.0),
      child: Row(
        children: <Widget>[
          Container(
            height: 10.0,
            width: 200.0,
            child: LinearProgressIndicator(
              value: (scan.productCalories / macros[0]),
              backgroundColor: Color(0xffEDEDED),
              valueColor: AlwaysStoppedAnimation<Color>(Color(0xff5FA55A)),
            ),
          ),
          Text('      ' +
              ((scan.productCalories / macros[0]) * 100).toStringAsFixed(0) +
              '%'),
        ],
      ),
    );
  }

  Widget _expandedCarbs() {
    return Padding(
      padding: EdgeInsets.fromLTRB(0.0, 15.0, 0.0, 0.0),
      child: Row(
        children: <Widget>[
          Container(
            height: 10.0,
            width: 200.0,
            child: LinearProgressIndicator(
              value: (scan.productCarbs/macros[2]),
              backgroundColor: Color(0xffEDEDED),
              valueColor: AlwaysStoppedAnimation<Color>(Color(0xffFA5457)),
            ),
          ),
          Text('      ' + ((scan.productCarbs/macros[2])*100).toStringAsFixed(0) + '%'),
        ],
      ),
    );
  }

  Widget _expandedProtein() {
    return Padding(
      padding: EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 0.0),
      child: Row(
        children: <Widget>[
          Container(
            height: 10.0,
            width: 200.0,
            child: LinearProgressIndicator(
              value: (scan.productProtein/macros[1]),
              backgroundColor: Color(0xffEDEDED),
              valueColor: AlwaysStoppedAnimation<Color>(Color(0xffFA8925)),
            ),
          ),
          Text('      ' + ((scan.productProtein/macros[1])*100).toStringAsFixed(0) + '%'),
        ],
      ),
    );
  }

  Widget _expandedFat() {
    return Padding(
      padding: EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 10.0),
      child: Row(
        children: <Widget>[
          Container(
            height: 10.0,
            width: 200.0,
            child: LinearProgressIndicator(
              value: (scan.productFat/macros[3]),
              backgroundColor: Color(0xffEDEDED),
              valueColor: AlwaysStoppedAnimation<Color>(Color(0xff01B4BC)),
            ),
          ),
          Text('      ' + ((scan.productFat/macros[3])*100).toStringAsFixed(0) + '%'),
        ],
      ),
    );
  }
}
