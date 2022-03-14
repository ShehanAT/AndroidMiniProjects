import 'package:get_it/get_it.dart';
import 'package:calorie_tracker_app/src/model/test_model.dart';

GetIt locator = GetIt();

void setupLocator() {
  // Register services

  // Register models
  locator.registerFactory<TestModel>(() => TestModel());
}
