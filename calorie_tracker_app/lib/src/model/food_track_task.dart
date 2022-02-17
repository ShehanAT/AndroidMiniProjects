import 'package:json_annotation/json_annotation.dart';
import 'package:calorie_tracker_app/src/utils/uuid.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';
import 'package:calorie_tracker_app/src/model/food_model.dart';

part 'food_track_task.g.dart';

@JsonSerializable()
class FoodTrackTask {
  String id;
  String name;
  Food food;

  FoodTrackTask(
    this.name, {
    required this.food,
    String? id,
  }) : this.id = id ?? Uuid().generateV4();

  factory FoodTrackTask.fromJson(Map<String, dynamic> json) =>
      _$TaskFromJson(json);

  Map<String, dynamic> toJson() => _$TaskToJson(this);
}
