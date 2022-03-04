import 'package:flutter/material.dart';
import 'package:calorie_tracker_app/src/model/scoped/food_list_model.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';
import 'package:calorie_tracker_app/component/colorpicker/color_picker_builder.dart';
import 'package:calorie_tracker_app/component/iconpicker/icon_picker_builder.dart';
import 'package:scoped_model/scoped_model.dart';

class AddFoodScreen extends StatefulWidget {
  AddFoodScreen();

  @override
  State<StatefulWidget> createState() {
    return _AddFoodScreenState();
  }
}

class _AddFoodScreenState extends State<AddFoodScreen> {
  late String newFood;
  Color foodColor = Colors.black;
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  late IconData foodIcon;
  bool _isBack = true;
  bool _isCart = true;
  bool isButtonTapped = false;

  @override
  void initState() {
    super.initState();
    newFood = '';
    foodIcon = Icons.food_bank;
  }

  void onClickBackButton() {
    print("Back Button");
    Navigator.of(context).pop();
  }

  void onClickCart() {
    print("Cart Button");
    // Navigator.of(context).push(MaterialPageRoute(builder: (context) =>))
  }

  @override
  Widget body() {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text("HOME SCREEN BODY"),
          RaisedButton(
            onPressed: () {
              if (!isButtonTapped) {
                setState(() {
                  isButtonTapped = true;
                });
              }
            },
            child: Text(isButtonTapped ? "BUTTON TAPPED" : "BUTTON NOT TAPPED"),
          )
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          flexibleSpace: Container(
            decoration: BoxDecoration(
                gradient: LinearGradient(
                    colors: [Colors.blue.shade200, Colors.pink.shade300])),
          ),
          title: Text(
            "Calorie Tracker App",
            style: TextStyle(
                color: Colors.black, fontSize: 20, fontWeight: FontWeight.bold),
          ),
          leading: _isBack
              ? IconButton(
                  icon: Icon(
                    Icons.arrow_back_ios,
                    color: Colors.black,
                  ),
                  onPressed: () {
                    onClickBackButton();
                  },
                )
              : Container(),
          actions: [
            _isCart
                ? IconButton(
                    icon: Icon(
                      Icons.shopping_cart,
                      color: Colors.black,
                    ),
                    onPressed: () {
                      onClickCart();
                    },
                  )
                : Container()
          ],
        ),
        body: new Column(
          children: <Widget>[
            new ListTile(
                leading: const Icon(Icons.food_bank),
                title: new TextField(
                  decoration: new InputDecoration(
                    hintText: "Food Name",
                  ),
                )),
            new ListTile(
                leading: const Icon(Icons.food_bank),
                title: new TextField(
                  decoration: new InputDecoration(
                    hintText: "Carb amount(g):",
                  ),
                )),
            new ListTile(
                leading: const Icon(Icons.food_bank),
                title: new TextField(
                  decoration: new InputDecoration(
                    hintText: "Fat amount(g):",
                  ),
                )),
            new ListTile(
                leading: const Icon(Icons.food_bank),
                title: new TextField(
                  decoration: new InputDecoration(
                    hintText: "Protein amount(g):",
                  ),
                )),
          ],
        ));
    // body: Container(
    //   child: body(),
    //   color: Colors.white,
    // ));
    // return ScopedModelDescendant<FoodListModel>(
    //     builder: (BuildContext context, Widget child, FoodListModel model) {
    //   return Scaffold(
    //     key: _scaffoldKey,
    //     backgroundColor: Colors.white,
    //     appBar: AppBar(
    //       title: Text(
    //         'New Category',
    //         style: TextStyle(color: Colors.black),
    //       ),
    //       centerTitle: true,
    //       elevation: 0,
    //       iconTheme: IconThemeData(color: Colors.black26),
    //       brightness: Brightness.light,
    //       backgroundColor: Colors.white,
    //     ),
    //     body: Container(
    //       constraints: BoxConstraints.expand(),
    //       padding: EdgeInsets.symmetric(horizontal: 36.0, vertical: 36.0),
    //       child: Column(
    //         crossAxisAlignment: CrossAxisAlignment.start,
    //         children: [
    //           Text(
    //             'Category will help you group related task!',
    //             style: TextStyle(
    //                 color: Colors.black38,
    //                 fontWeight: FontWeight.w600,
    //                 fontSize: 16.0),
    //           ),
    //           Container(
    //             height: 16.0,
    //           ),
    //           TextField(
    //             onChanged: (text) {
    //               setState(() => newFood = text);
    //             },
    //             cursorColor: foodColor,
    //             autofocus: true,
    //             decoration: InputDecoration(
    //                 border: InputBorder.none,
    //                 hintText: 'Category Name...',
    //                 hintStyle: TextStyle(
    //                   color: Colors.black26,
    //                 )),
    //             style: TextStyle(
    //                 color: Colors.black54,
    //                 fontWeight: FontWeight.w500,
    //                 fontSize: 36.0),
    //           ),
    //           Container(
    //             height: 26.0,
    //           ),
    //           Row(
    //             children: [
    //               // ColorPickerBuilder(
    //               //     color: foodColor,
    //               //     onColorChanged: (newColor) =>
    //               //         setState(() => foodColor = newColor)),
    //               Container(
    //                 width: 22.0,
    //               ),
    //               IconPickerBuilder(
    //                   iconData: foodIcon,
    //                   highlightColor: foodColor,
    //                   action: (newIcon) => setState(() => foodIcon = newIcon)),
    //             ],
    //           ),
    //         ],
    //       ),
    //     ),
    //     floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
    //     floatingActionButton: Builder(
    //       builder: (BuildContext context) {
    //         return FloatingActionButton.extended(
    //           heroTag: 'fab_new_card',
    //           icon: Icon(Icons.save),
    //           backgroundColor: foodColor,
    //           label: Text('Create New Card'),
    //           onPressed: () {
    //             if (newFood.isEmpty) {
    //               final snackBar = SnackBar(
    //                 content: Text('New food not found'),
    //                 backgroundColor: foodColor,
    //               );
    //               Scaffold.of(context).showSnackBar(snackBar);
    //               // _scaffoldKey.currentState.showSnackBar(snackBar);
    //             } else {
    //               print("New food found!");
    //               // model.a(Task(newFood,
    //               //     codePoint: taskIcon.codePoint, color: foodColor.value));
    //               // Navigator.pop(context);
    //             }
    //           },
    //         );
    //       },
    //     ),
    //   );
    // });
  }
}
