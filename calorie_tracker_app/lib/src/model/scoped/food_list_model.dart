import 'package:flutter/material.dart';
import 'package:scoped_model/scoped_model.dart';

import 'package:calorie_tracker_app/src/model/food_model.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';
import 'package:calorie_tracker_app/src/db/db_provider.dart';

class FoodListModel extends Model {
  var _db = DBProvider.db;

  List<FoodTrackTask> get foodTrackTasks => _foodTrackTasks.toList();

  bool get isLoading => _isLoading;

  bool _isLoading = false;
  List<FoodTrackTask> _foodTrackTasks = [];

  static FoodListModel of(BuildContext context) =>
      ScopedModel.of<FoodListModel>(context);

  @override
  void addListener(listener) {
    super.addListener(listener);
    // update data for every subscriber, especially for the first one
    _isLoading = true;
    notifyListeners();
  }
}
