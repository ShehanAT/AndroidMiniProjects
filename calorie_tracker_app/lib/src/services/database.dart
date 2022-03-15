import 'package:calorie_tracker_app/src/model/food_track_task.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class DatabaseService {
  final String uid;
  final DateTime currentDate;
  DatabaseService({required this.uid, required this.currentDate});

  final DateTime today =
      DateTime(DateTime.now().year, DateTime.now().month, DateTime.now().day);
  final DateTime weekStart = DateTime(2020, 09, 07);
  // collection reference
  final CollectionReference foodTrackCollection =
      Firestore.instance.collection('foodTracks');

  Future newFoodTrackData(String name, double calories, double carbs,
      double fat, double protein, String mealTime, double grams) async {
    return await foodTrackCollection
        .document(uid)
        .collection('foodTracks')
        .document(DateTime.now().millisecondsSinceEpoch.toString())
        .setData({
      'food_name': name,
      'calories': calories,
      'carbs': carbs,
      'fat': fat,
      'protein': protein,
      'mealTime': mealTime,
      'created_on': DateTime.now(),
      'grams': grams,
    });
  }

  Future<void> deleteScan(String productID) async {
    await foodTrackCollection
        .document(uid)
        .collection('foodTracks')
        .document(productID)
        .delete();
  }

  List<FoodTrackTask> _scanListFromSnapshot(QuerySnapshot snapshot) {
    return snapshot.documents.map((doc) {
      return FoodTrackTask(
        id: doc.documentID,
        food_name: doc.data['food_name'] ?? '',
        calories: doc.data['calories'] ?? 0,
        carbs: doc.data['carbohydrates'] ?? 0,
        fat: doc.data['fat'] ?? 0,
        protein: doc.data['protein'] ?? 0,
        mealTime: doc.data['mealTime'] ?? 0,
        createdOn: doc.data['createdOn'] ?? null,
        grams: doc.data['amount_had'] ?? 0,
      );
    }).toList();
  }

  Stream<List<FoodTrackTask>> get scans {
    return foodTrackCollection
        .document(uid)
        .collection('foodTracks')
        .snapshots()
        .map(_scanListFromSnapshot);
  }
}
