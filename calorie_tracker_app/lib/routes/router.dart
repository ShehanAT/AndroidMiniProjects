import 'package:calorie_tracker_app/src/page/food-track/add_food_track.dart';
import 'package:flutter/cupertino.dart';
import 'route_constants.dart';
import 'package:calorie_tracker_app/main.dart';
import 'package:calorie_tracker_app/views/undefined_view.dart';

class RoutePage {
  static Route<dynamic> generateRoute(RouteSettings settings) {
    switch (settings.name) {
      case RouteConstant.ROOT:
        return PageRouteBuilder<dynamic>(
            settings: settings,
            pageBuilder: (_, __, ___) => Homepage(),
            transitionsBuilder: (_, Animation<double> a, __, Widget c) =>
                FadeTransition(opacity: a, child: c));
      case RouteConstant.ADD_FOODTRACK:
        return PageRouteBuilder<dynamic>(
            settings: settings,
            pageBuilder: (_, __, ___) => AddFoodScreen(),
            transitionsBuilder: (_, Animation<double> a, __, Widget c) =>
                FadeTransition(opacity: a, child: c));
      default:
        return PageRouteBuilder<dynamic>(
            settings: settings,
            pageBuilder: (_, __, ___) => UndefinedView(
                  routeName: settings.name!,
                ),
            transitionsBuilder: (_, Animation<double> a, __, Widget c) =>
                FadeTransition(opacity: a, child: c));
    }
  }
}
