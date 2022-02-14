import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';

@JsonSerializable()
class Food {
  String id;
  String name;
  int color;
  @JsonKey(name: 'code_point')
  int codePoint;

  Food(
    this.name, {
    required this.color,
    required this.codePoint,
    String? id,
  }) : this.id = id ?? Uuid().generateV4();

  factory Food.fromJson(Map<String, dynamic> json) => _$TaskFromJson(json);

  Map<String, dynamic> toJson() => _$TaskToJson(this);
}
