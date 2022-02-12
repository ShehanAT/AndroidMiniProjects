import 'package:flutter/material.dart';

class AddFoodScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _AddTaskScreenState();
  }
}

class _AddTaskScreenState extends State<AddFoodScreen> {
  late String newFood;
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  late Color taskColor;
  late IconData taskIcon;
}
