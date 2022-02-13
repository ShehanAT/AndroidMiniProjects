import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';

@JsonSerializable()
class Task {
  String id;
  String name;
  int color;
  @JsonKey(name: 'code_point')
  int codePoint;

  Task(
    this.name, {
    required this.color,
    required this.codePoint,
    String? id,
  }) : this.id = id ?? Uuid().generateV4();

  factory Task.fromJson(Map<String, dynamic> json) => _$TaskFromJson(json);

  Map<String, dynamic> toJson() => _$TaskToJson(this);
}
