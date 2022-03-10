import 'package:firebase_database/firebase_database.dart';
import '../food_model.dart';

class FoodDao {
  FirebaseDatabase database = FirebaseDatabase.instance;

  final DatabaseReference _foodRef =
      FirebaseDatabase.instance.ref().child('food');

  void saveFood(Food food) {
    _foodRef.push().set(food.toJson());
  }

  Query getFoodQuery() {
    return _foodRef;
  }
}
