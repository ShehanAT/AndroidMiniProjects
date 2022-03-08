import 'package:flutter/material.dart';
import 'package:calorie_tracker_app/src/model/scoped/food_list_model.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';
import 'package:calorie_tracker_app/src/model/food_model.dart';
import 'package:calorie_tracker_app/component/colorpicker/color_picker_builder.dart';
import 'package:calorie_tracker_app/component/iconpicker/icon_picker_builder.dart';
import 'package:scoped_model/scoped_model.dart';

class SettingsScreen extends StatefulWidget {
  SettingsScreen();

  @override
  State<StatefulWidget> createState() {
    return _SettingsScreenState();
  }
}

class _SettingsScreenState extends State<SettingsScreen> {
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  bool _isBack = true;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
            title: Text("Settings Screen",
                style: TextStyle(
                    color: Colors.black,
                    fontSize: 20,
                    fontWeight: FontWeight.bold))),
        body: new Column(
          children: [
            new ListTile(
                leading: const Icon(Icons.settings),
                title: new Text("Settings"))
          ],
        ));
  }
}
