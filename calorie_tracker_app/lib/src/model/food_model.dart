import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';

import 'package:calorie_tracker_app/src/utils/uuid.dart';
part 'food_model.g.dart';

@JsonSerializable()
class Food {
  String id;
  String name;
  late String color;

  Food(
    this.name, {
    String? color,
    String? id,
  }) : this.id = id ?? Uuid().generateV4();

  factory Food.fromJson(Map<String, dynamic> json) => _$FoodFromJson(json);

  Map<String, dynamic> toJson() => _$FoodToJson(this);
}
