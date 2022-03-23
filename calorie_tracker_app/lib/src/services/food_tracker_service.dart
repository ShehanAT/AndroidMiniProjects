import "package:calorie_tracker_app/src/model/food_track_task.dart";
import "package:firebase_database/firebase_database.dart";
import "package:calorie_tracker_app/src/model/food_model.dart";
import "package:calorie_tracker_app/src/model/dao/food_dao.dart";
import "dart:math";

class FoodTrackerService {
  String nodeName = "foodTrack";
  FirebaseDatabase database = FirebaseDatabase.instance;
  late DatabaseReference _databaseReference;
  final foodDao = FoodDao();
  static const _chars =
      'AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890';
  Random _rnd = Random();

  String getRandomString(int length) => String.fromCharCodes(Iterable.generate(
      length, (_) => _chars.codeUnitAt(_rnd.nextInt(_chars.length))));

  void addFoodTrack(FoodTrackTask foodtrackTask) {
    foodDao.saveFood(foodtrackTask);
  }

  void deleteFoodTrack(FoodTrackTask foodtrackTask) {
    _databaseReference =
        database.reference().child('$nodeName/${foodtrackTask.id}');
    _databaseReference.remove();
  }

  void updateFoodTrack(FoodTrackTask foodtrackTask) {
    _databaseReference =
        database.reference().child('$nodeName/${foodtrackTask.id}');
    _databaseReference.update(foodtrackTask.toMap());
  }
}
