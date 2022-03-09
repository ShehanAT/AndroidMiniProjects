import 'package:json_annotation/json_annotation.dart';
import 'package:calorie_tracker_app/src/utils/uuid.dart';
import 'package:calorie_tracker_app/src/model/food_model.dart';
import 'package:firebase_database/firebase_database.dart';

@JsonSerializable()
class FoodTrackTask {
  String id;
  String name;
  Food food;

  FoodTrackTask({
    required this.name,
    required this.food,
    String? id,
  }) : this.id = id ?? Uuid().generateV4();

  factory FoodTrackTask.fromSnapshot(DataSnapshot snap) => FoodTrackTask(
      name: snap.child('name').value as String,
      food: snap.child('food').value as Food);

  Map<String, dynamic> toMap() {
    return <String, dynamic>{'name': name, 'food': food};
  }

  // factory FoodTrackTask.fromJson(Map<String, dynamic> json) =>
  //     _$TaskFromJson(json);

  // Map<String, dynamic> toJson() => _$TaskToJson(this);
}
