import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
// import 'package:food_app/locator.dart';
// import 'package:food_app/screens/home/Pages/profile/profile.dart';
// import 'package:food_app/services/question_controller.dart';
// import 'package:food_app/services/user_controller.dart';

class QuestionAlert extends StatefulWidget {
  final List value;
  QuestionAlert({required this.value});

  @override
  _QuestionAlertState createState() => _QuestionAlertState();
}

class _QuestionAlertState extends State<QuestionAlert> {
  bool pressable;
  QuestionController _question;
  List<int> choices;

  void initState() {
    super.initState();
    pressable = true;
    _question = QuestionController(correctValue: widget.value[0]);
    choices = _question.getChoices(widget.value[0]);
  }

  void disableButton() {
    setState(() {
      pressable = false;
    });
  }
}
