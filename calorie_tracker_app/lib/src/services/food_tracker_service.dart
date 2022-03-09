import "package:calorie_tracker_app/src/model/food_track_task.dart";
import "package:firebase_database/firebase_database.dart";

class FoodTrackerService {
  String nodeName = "foodTrack";
  FirebaseDatabase database = FirebaseDatabase.instance;
  late DatabaseReference _databaseReference;

  void addFoodTrack(FoodTrackTask foodtrackTask) {
    _databaseReference = database.reference().child(nodeName);
    _databaseReference.push().set(foodtrackTask.toMap());
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
