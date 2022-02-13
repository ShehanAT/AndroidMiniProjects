import 'package:flutter/material.dart';

class AddFoodScreen extends StatefulWidget {
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
    newTask = '';
    taskColor = ColorUtils.defaultColors[0];
    taskIcon = Icons.work;
  }
}
