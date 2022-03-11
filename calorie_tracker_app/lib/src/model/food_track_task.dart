import 'package:json_annotation/json_annotation.dart';
import 'package:calorie_tracker_app/src/utils/uuid.dart';
import 'package:calorie_tracker_app/src/model/food_model.dart';
import 'package:firebase_database/firebase_database.dart';

@JsonSerializable()
class FoodTrackTask {
  String id;
  late Food food;
  String mealTime;
  DateTime createdOn;

  FoodTrackTask({
    required this.food,
    required this.mealTime,
    required this.createdOn,
    String? id,
  }) : this.id = id ?? Uuid().generateV4();

  factory FoodTrackTask.fromSnapshot(DataSnapshot snap) => FoodTrackTask(
      food: snap.child('food').value as Food,
      mealTime: snap.child('mealTime').value as String,
      createdOn: snap.child('createdOn').value as DateTime);

  Map<String, dynamic> toMap() {
    return <String, dynamic>{'mealTime': mealTime, 'food': food.toString()};
  }

  FoodTrackTask.fromJson(Map<dynamic, dynamic> json)
      : id = json['id'],
        mealTime = json['mealTime'],
        createdOn = json['createdOn'],
        food = json['food'];

  Map<dynamic, dynamic> toJson() => <dynamic, dynamic>{
        'id': id,
        'mealTime': mealTime,
        'createdOn': createdOn,
        'name': food.name,
        'calories': food.calories,
        'carbs': food.carbs,
        'fat': food.fat,
        'protein': food.protein
      };
}
