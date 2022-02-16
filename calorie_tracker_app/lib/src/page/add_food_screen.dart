import 'package:flutter/material.dart';
// import 'package:calorie_tracker_app/src/model/scoped/'

import 'package:calorie_tracker_app/src/model/scoped/food_list_model.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';
// import 'package:calorie_tracker_app/src/c'


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


  @override
  void initState() {
    super.initState();
    newFood = '';
  }

  @override 
  Widget build(BuildContext context){
    return ScopedModelDescendant<FoodListModel>{
      builder: (Builder context, Widget child, FoodListModel model){
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
                  cursorColor: foodColor,
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
                        color: foodColor,
                        onColorChanged: (newColor) =>
                            setState(() => foodColor = newColor)),
                    Container(
                      width: 22.0,
                    ),
                    IconPickerBuilder(
                        iconData: taskIcon,
                        highlightColor: foodColor,
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
                  backgroundColor: foodColor,
                  label: Text('Create New Card'),
                  onPressed: () {
                    if (newFood.isEmpty) {
                      final snackBar = SnackBar(
                        content: Text(
                            'Ummm... It seems that you are trying to add an invisible task which is not allowed in this realm.'),
                        backgroundColor: foodColor,
                      );
                      Scaffold.of(context).showSnackBar(snackBar);
                      // _scaffoldKey.currentState.showSnackBar(snackBar);
                    } else {
                      model.addTask(Task(newFood,
                          codePoint: taskIcon.codePoint, color: foodColor.value));
                      Navigator.pop(context);
                    }
                  },
                );
            )
        }
    }
  }
}
