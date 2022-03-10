import 'package:calorie_tracker_app/src/model/food_track_task.dart';
import 'package:firebase_database/firebase_database.dart';
import '../food_model.dart';

class FoodDao {
  FirebaseDatabase database = FirebaseDatabase.instance;

  final DatabaseReference _foodRef =
      FirebaseDatabase.instance.ref().child('food');
  final DatabaseReference _foodTrackRef =
      FirebaseDatabase.instance.ref().child('foodTrack');

  void saveFood(FoodTrackTask foodTrackTask) {
    _foodTrackRef.push().set(foodTrackTask.toJson());
  }

  Query getFoodQuery() {
    return _foodRef;
  }
}
