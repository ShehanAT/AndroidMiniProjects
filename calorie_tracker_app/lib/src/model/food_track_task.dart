import 'food_model.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:calorie_tracker_app/src/utils/uuid.dart';

@JsonSerializable()
class FoodTrackTask {
  String id;
  Food food;

  FoodTrackTask({required this.id, required this.food})
      : this.id = id ?? Uuid().generateV4();

  factory FoodTrackTask.fromJson(Map<String, dynamic> json) =>
      _$TaskFromJson(json);

  Map<String, dynamic> toJson() => _$TaskToJson(this);
}
