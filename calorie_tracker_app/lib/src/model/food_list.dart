import 'food_model.dart';
import 'dao/food_dao.dart';
import 'dart:math';

class FoodList {
  final foodDao = FoodDao();
  bool _canSendMessage = true;
  static const _chars =
      'AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890';
  Random _rnd = Random();

  String getRandomString(int length) => String.fromCharCodes(Iterable.generate(
      length, (_) => _chars.codeUnitAt(_rnd.nextInt(_chars.length))));

  void _sendMessage() {
    if (_canSendMessage) {
      final food = Food(getRandomString(5));
      foodDao.saveFood(food);
    }
  }
}
