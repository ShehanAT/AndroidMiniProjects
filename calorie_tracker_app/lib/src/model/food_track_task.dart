import 'food_model.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:calorie_tracker_app/src/utils/uuid.dart';

@JsonSerializable()
class FoodTrackTask {
  String id;
  Food food;
}
