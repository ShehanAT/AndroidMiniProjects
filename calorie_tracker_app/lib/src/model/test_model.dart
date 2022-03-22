import 'package:scoped_model/scoped_model.dart';
import 'package:calorie_tracker_app/src/services/storage_service.dart';
import 'package:calorie_tracker_app/src/services/service_locator.dart';

// class TestModel extends Model {
//   // StorageService storageService = locator<StorageService>();

//   String title = "TestModel";

//   late ViewState _state;
//   ViewState get state => _state;

//   Future saveData() async {
//     _setState(ViewState.Busy);
//     title = "Saving Data";
//     await storageService.saveData();
//     title = "Data Saved";
//     _setState(ViewState.Retrieved);
//   }

//   void _setState(ViewState newState) {
//     _state = newState;
//     notifyListeners();
//   }
// }
