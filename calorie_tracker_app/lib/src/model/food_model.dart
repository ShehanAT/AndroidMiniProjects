import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';

import 'package:calorie_tracker_app/src/utils/uuid.dart';

@JsonSerializable()
class Food {
  String id;
  String name;
  String color;
  @JsonKey(name: 'code_point')
  int codePoint;

  Food(
    this.name, {
    required this.color,
    required this.codePoint,
    String? id,
  }) : this.id = id ?? Uuid().generateV4();
}
