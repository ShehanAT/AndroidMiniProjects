import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';

import 'package:calorie_tracker_app/src/utils/uuid.dart';

@JsonSerializable()
class Food {
  String id;
  String name;
  String color;

  Food(
    this.name, {
    required this.color,
    String? id,
  }) : this.id = id ?? Uuid().generateV4();

  factory Food.fromJson(Map<String, dynamic> json) => _$TaskFromJson(json);

  Map<String, dynamic> toJson() => _$TaskToJson(this);
}
