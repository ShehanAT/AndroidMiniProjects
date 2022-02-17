part of 'food_track_task.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

FoodTrackTask _$TaskFromJson(Map<String, dynamic> json) {
  return FoodTrackTask(json['name'] as String,
      id: json['id'] as String, food: json['food'] as Food);
}

Map<String, dynamic> _$TaskToJson(FoodTrackTask instance) => <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'food': instance.food,
    };
