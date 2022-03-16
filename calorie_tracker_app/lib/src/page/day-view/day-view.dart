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
import 'package:calorie_tracker_app/src/services/database.dart';
import 'package:openfoodfacts/model/Product.dart';
import 'package:openfoodfacts/openfoodfacts.dart';

class DayViewScreen extends StatefulWidget {
  DayViewScreen();

  @override
  State<StatefulWidget> createState() {
    return _DayViewState();
  }
}

class _DayViewState extends State<DayViewScreen> {
  String productName = 'Loading...';
  Product newResult;
  double servingSize = 0;
  String dropdownValue = 'grams';
  DateTime _value = DateTime.now();
  DateTime today = DateTime.now();
  Color _rightArrowColor = Color(0xffC1C1C1);

  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  bool _isBack = true;
  final List<GalleryScaffold> lineGallery = buildGallery();
  DatabaseService databaseService = new DatabaseService(
      uid: "calorie-tracker-b7d17", currentDate: DateTime.now());

  @override
  void initState() {
    super.initState();
    databaseService.getFoodTrackData("calorie-tracker-b7d17");
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
            ))),
        height: 220,
        child: Row(
          children: <Widget>[
            CalorieStats(datePicked: DateTime.now()),
          ],
        ),
      ),
    );
  }

  Widget _addFoodButton() {
    return IconButton(
      icon: Icon(Icons.add_box),
      iconSize: 25,
      color: Colors.white,
      onPressed: () async {
        // dynamic result = await _scan.barcodeScan();
        ProductQueryConfiguration configuration = ProductQueryConfiguration(
            barcode,
            language: OpenFoodFactsLanguage.ENGLISH,
            fields: [ProductField.ALL]);
        ProductResult result =
            await OpenFoodAPIClient.getProduct(configuration);
        setState(() {
          // newResult = result;
          newResult = result.product;
          // productName = newResult.productName;
        });
        // _showFoodToAdd(context);
      },
    );
  }

  Future _selectDate() async {
    DateTime? picked = await showDatePicker(
      context: context,
      initialDate: _value,
      firstDate: new DateTime(2019),
      lastDate: new DateTime.now(),
      builder: (BuildContext context, Widget child) {
        return Theme(
          data: ThemeData.light().copyWith(
              primaryColor: const Color(0xff5FA55A), //Head background
              accentColor: const Color(0xFF5FA55A) //selection color
              //dialogBackgroundColor: Colors.white,//Background color
              ),
          child: child,
        );
      },
    );
    if (picked != null) setState(() => _value = picked);
    _stateSetter();
  }

  void _stateSetter() {
    if (today.difference(_value).compareTo(Duration(days: 1)) == -1) {
      setState(() => _rightArrowColor = Color(0xffEDEDED));
    } else
      setState(() => _rightArrowColor = Colors.white);
  }

  // _showFoodToAdd(BuildContext context) {
  //   return showDialog(
  //       context: context,
  //       builder: (context) {
  //         return AlertDialog(
  //           title: Text(productName),
  //           content: _showAmountHad(),
  //           actions: <Widget>[
  //             FlatButton(
  //               onPressed: () => Navigator.pop(context), // passing false
  //               child: Text('Cancel'),
  //             ),
  //             FlatButton(
  //               onPressed: () async {
  //                 Navigator.pop(context);
  //                 await showDialog(
  //                     context: context,
  //                     builder: (context) {
  //                       List<List> questionArray = [
  //                         [
  //                           newResult.nutriments.energyKcal100g *
  //                               servingSize /
  //                               100,
  //                           'many calories are',
  //                           ''
  //                         ],
  //                         [
  //                           newResult.nutriments.fat * servingSize / 100,
  //                           'much fat is',
  //                           'g'
  //                         ],
  //                         [
  //                           newResult.nutriments.proteins * servingSize / 100,
  //                           'much protein is',
  //                           'g'
  //                         ],
  //                         [
  //                           newResult.nutriments.carbohydrates *
  //                               servingSize /
  //                               100,
  //                           'much carbohydrate is',
  //                           'g'
  //                         ]
  //                       ];
  //                       questionArray.shuffle();
  //                       return QuestionAlert(value: questionArray[0]);
  //                     });
  //                 _scan.storeProduct(newResult, servingSize, dropdownValue);
  //               },
  //               child: Text('Ok'),
  //             ),
  //           ],
  //         );
  //       });
  // }

  Widget _showDatePicker() {
    return Container(
      width: 250,
      child: Row(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          IconButton(
            icon: Icon(Icons.arrow_left, size: 25.0),
            color: Colors.white,
            onPressed: () {
              setState(() {
                _value = _value.subtract(Duration(days: 1));
                _rightArrowColor = Colors.white;
              });
            },
          ),
          TextButton(
            // textColor: Colors.white,
            onPressed: () => {print("Next Date Button Pressed")},
            // _selectDate()},
            child: Text(DateTime.now().toString(),
                style: TextStyle(
                  fontFamily: 'Open Sans',
                  fontSize: 18.0,
                  fontWeight: FontWeight.w700,
                )),
          ),
          IconButton(
              icon: Icon(Icons.arrow_right, size: 25.0),
              // color: _rightArrowColor,
              onPressed: () {
                // print(today.difference(_value).compareTo(Duration(days: 1)));
                // if (today.difference(_value).compareTo(Duration(days: 1)) ==
                //     -1) {
                //   setState(() {
                //     _rightArrowColor = Color(0xffC1C1C1);
                //   });
                // } else {
                //   setState(() {
                //     _value = _value.add(Duration(days: 1));
                //   });
                //   if (today.difference(_value).compareTo(Duration(days: 1)) ==
                //       -1) {
                //     setState(() {
                //       _rightArrowColor = Color(0xffC1C1C1);
                //     });
                //   }
                // }
              }),
        ],
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
            elevation: 0,
            bottom: PreferredSize(
              preferredSize: const Size.fromHeight(5.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget>[
                  _showDatePicker(),
                  _addFoodButton(),
                ],
              ),
            )),
        body: StreamProvider<List<FoodTrackTask>>.value(
          initialData: [],
          value: new DatabaseService(
                  uid: "calorie-tracker-b7d17", currentDate: DateTime.now())
              .foodTracks,
          child: new Column(children: <Widget>[
            _calorieCounter(),
            Expanded(
                child: ListView(
              children: <Widget>[FoodTrackList(datePicked: DateTime.now())],
            ))
          ]),
        ));
  }
}

class FoodTrackList extends StatelessWidget {
  final DateTime datePicked;
  FoodTrackList({required this.datePicked});

  @override
  Widget build(BuildContext context) {
    final DateTime curDate =
        new DateTime(datePicked.year, datePicked.month, datePicked.day);

    final foodTracks = Provider.of<List<FoodTrackTask>>(context);

    List findCurScans(List scansFeed) {
      print(scansFeed);
      List curScans = [];
      scansFeed.forEach((scan) {
        // DateTime scanDate = DateTime(scan.dateTime.toDate().year,
        //     scan.dateTime.toDate().month, scan.dateTime.toDate().day);
        // if (scanDate.compareTo(curDate) == 0) {
        //   curScans.add(scan);
        // }
        curScans.add(scan);
      });
      return curScans;
    }

    List curScans = findCurScans(foodTracks);

    return ListView.builder(
      scrollDirection: Axis.vertical,
      physics: ClampingScrollPhysics(),
      shrinkWrap: true,
      itemCount: curScans.length + 1,
      itemBuilder: (context, index) {
        if (index < curScans.length) {
          return FoodTrackTile(scan: curScans[index]);
        } else {
          return SizedBox(height: 5);
        }
      },
    );
  }
}

class FoodTrackTile extends StatelessWidget {
  final FoodTrackTask scan;
  FoodTrackTile({required this.scan});

  List macros = CalorieStats.macroData;

  @override
  Widget build(BuildContext context) {
    return ExpansionTile(
      leading: CircleAvatar(
        radius: 25.0,
        backgroundColor: Color(0xff5FA55A),
        child: _itemCalories(),
      ),
      title: Text(scan.food_name,
          style: TextStyle(
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
        Text(scan.calories.toStringAsFixed(0),
            style: TextStyle(
              fontSize: 16.0,
              color: Colors.white,
              fontFamily: 'Open Sans',
              fontWeight: FontWeight.w500,
            )),
        Text('kcal',
            style: TextStyle(
              fontSize: 10.0,
              color: Colors.white,
              fontFamily: 'Open Sans',
              fontWeight: FontWeight.w500,
            )),
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
                  Text(' ' + scan.carbs.toStringAsFixed(1) + 'g    ',
                      style: TextStyle(
                        fontSize: 12.0,
                        color: Colors.white,
                        fontFamily: 'Open Sans',
                        fontWeight: FontWeight.w400,
                      )),
                  Container(
                    height: 8,
                    width: 8,
                    decoration: BoxDecoration(
                      color: Color(0xffFA8925),
                      shape: BoxShape.circle,
                    ),
                  ),
                  Text(' ' + scan.protein.toStringAsFixed(1) + 'g    ',
                      style: TextStyle(
                        fontSize: 12.0,
                        color: Colors.white,
                        fontFamily: 'Open Sans',
                        fontWeight: FontWeight.w400,
                      )),
                  Container(
                    height: 8,
                    width: 8,
                    decoration: BoxDecoration(
                      color: Color(0xff01B4BC),
                      shape: BoxShape.circle,
                    ),
                  ),
                  Text(' ' + scan.fat.toStringAsFixed(1) + 'g',
                      style: TextStyle(
                        fontSize: 12.0,
                        color: Colors.white,
                        fontFamily: 'Open Sans',
                        fontWeight: FontWeight.w400,
                      )),
                ],
              ),
              Text(scan.grams.toString() + 'g',
                  style: TextStyle(
                    fontSize: 12.0,
                    color: Colors.white,
                    fontFamily: 'Open Sans',
                    fontWeight: FontWeight.w300,
                  )),
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
              color: Colors.white,
              fontFamily: 'Open Sans',
              fontWeight: FontWeight.w400,
            )),
        IconButton(
            icon: Icon(Icons.edit),
            iconSize: 16,
            onPressed: () {
              // Navigator.push(
              //   context,

              //   MaterialPageRoute(builder: (context) => EditItem(scan: scan)),
              // );
            })
      ],
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
              value: (scan.calories / macros[0]),
              backgroundColor: Color(0xffEDEDED),
              valueColor: AlwaysStoppedAnimation<Color>(Color(0xff5FA55A)),
            ),
          ),
          Text('      ' +
              ((scan.calories / macros[0]) * 100).toStringAsFixed(0) +
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
              value: (scan.carbs / macros[2]),
              backgroundColor: Color(0xffEDEDED),
              valueColor: AlwaysStoppedAnimation<Color>(Color(0xffFA5457)),
            ),
          ),
          Text('      ' +
              ((scan.carbs / macros[2]) * 100).toStringAsFixed(0) +
              '%'),
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
              value: (scan.protein / macros[1]),
              backgroundColor: Color(0xffEDEDED),
              valueColor: AlwaysStoppedAnimation<Color>(Color(0xffFA8925)),
            ),
          ),
          Text('      ' +
              ((scan.protein / macros[1]) * 100).toStringAsFixed(0) +
              '%'),
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
              value: (scan.fat / macros[3]),
              backgroundColor: Color(0xffEDEDED),
              valueColor: AlwaysStoppedAnimation<Color>(Color(0xff01B4BC)),
            ),
          ),
          Text('      ' +
              ((scan.fat / macros[3]) * 100).toStringAsFixed(0) +
              '%'),
        ],
      ),
    );
  }
}
