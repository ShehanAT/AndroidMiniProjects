import 'package:flutter/material.dart';
import 'package:calorie_tracker_app/src/model/user_id_model.dart';
import 'package:flutter/material.dart';
// import 'package:calorie_tracker_app/src/model/scoped/'

import 'package:calorie_tracker_app/src/model/scoped/food_list_model.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';
import 'package:calorie_tracker_app/component/colorpicker/color_picker_builder.dart';
import 'package:calorie_tracker_app/component/iconpicker/icon_picker_builder.dart';
import 'package:calorie_tracker_app/src/model/food_model.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:calorie_tracker_app/src/utils/color_utils.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';

class AddFoodScreen extends StatefulWidget {
  final String foodItemId;
  final UserId userId;

  AddFoodScreen({
    required this.foodItemId,
    required this.userId
  })

  @override
  State<StatefulWidget> createState() {
    return _AddFoodScreenState();
  }
}

class _AddFoodScreenState extends State<AddFoodScreen> {
  late String newFood;
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  late Color taskColor;
  late IconData taskIcon;

  @override
  void initState() {
    super.initState();
    newFood = '';
    taskColor = ColorUtils.defaultColors[0];
    taskIcon = Icons.work;
  }

  @override
  Widget build(BuildContext context) {
    return ScopedModelDescendant<FoodListModel>(
      builder: (BuildContext context, Widget child, FoodListModel model) {
        return Scaffold(
          key: _scaffoldKey,
          backgroundColor: Colors.white,
          appBar: AppBar(
            title: Text(
              'New Category',
              style: TextStyle(color: Colors.black),
            ),
            centerTitle: true,
            elevation: 0,
            iconTheme: IconThemeData(color: Colors.black26),
            brightness: Brightness.light,
            backgroundColor: Colors.white,
          ),
          body: Container(
            constraints: BoxConstraints.expand(),
            padding: EdgeInsets.symmetric(horizontal: 36.0, vertical: 36.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  'Category will help you group related task!',
                  style: TextStyle(
                      color: Colors.black38,
                      fontWeight: FontWeight.w600,
                      fontSize: 16.0),
                ),
                Container(
                  height: 16.0,
                ),
                TextField(
                  onChanged: (text) {
                    setState(() => newFood = text);
                  },
                  cursorColor: taskColor,
                  autofocus: true,
                  decoration: InputDecoration(
                      border: InputBorder.none,
                      hintText: 'Category Name...',
                      hintStyle: TextStyle(
                        color: Colors.black26,
                      )),
                  style: TextStyle(
                      color: Colors.black54,
                      fontWeight: FontWeight.w500,
                      fontSize: 36.0),
                ),
                Container(
                  height: 26.0,
                ),
                Row(
                  children: [
                    ColorPickerBuilder(
                        color: taskColor,
                        onColorChanged: (newColor) =>
                            setState(() => taskColor = newColor)),
                    Container(
                      width: 22.0,
                    ),
                    IconPickerBuilder(
                        iconData: taskIcon,
                        highlightColor: taskColor,
                        action: (newIcon) =>
                            setState(() => taskIcon = newIcon)),
                  ],
                ),
              ],
            ),
          ),
          floatingActionButtonLocation:
              FloatingActionButtonLocation.centerFloat,
          floatingActionButton: Builder(
            builder: (BuildContext context) {
              return FloatingActionButton.extended(
                heroTag: 'fab_new_card',
                icon: Icon(Icons.save),
                backgroundColor: taskColor,
                label: Text('Create New Card'),
                onPressed: () {
                  if (newFood.isEmpty) {
                    final snackBar = SnackBar(
                      content: Text(
                          'Food Track Task not found!'),
                      backgroundColor: taskColor,
                    );
                    Scaffold.of(context).showSnackBar(snackBar);
                    // _scaffoldKey.currentState.showSnackBar(snackBar);
                  } else {
                    model.addFoodTrackTask(FoodTrackTask(newFood,
                        food: Food("Oatmeal")));
                    Navigator.pop(context);
                  }
                },
              );
            },
          ),
        );
      },
    );
  }
}
