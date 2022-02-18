import 'package:scoped_model/scoped_model.dart';
import 'package:flutter/material.dart';
import 'package:calorie_tracker_app/src/model/test_model.dart';

class TestScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ScopedModel<TestModel>(child: Scaffold());
  }
}
