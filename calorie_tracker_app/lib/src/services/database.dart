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
      FirebaseFirestore.instance.collection('foodTracks');

  Future newFoodTrackData(String name, double calories, double carbs,
      double fat, double protein, String mealTime, double grams) async {
    return await foodTrackCollection
        .doc(uid)
        .collection('foodTracks')
        .doc(DateTime.now().millisecondsSinceEpoch.toString())
        .set({
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
        .doc(uid)
        .collection('foodTracks')
        .doc(productID)
        .delete();
  }

  List<FoodTrackTask> _scanListFromSnapshot(QuerySnapshot snapshot) {
    return snapshot.docs.map((doc) {
      print(doc.toString());
      return FoodTrackTask(
        id: doc.id,
        food_name: doc['food_name'] ?? '',
        calories: doc['calories'] ?? 0,
        carbs: doc['carbohydrates'] ?? 0,
        fat: doc['fat'] ?? 0,
        protein: doc['protein'] ?? 0,
        mealTime: doc['mealTime'] ?? 0,
        createdOn: doc['createdOn'] ?? null,
        grams: doc['grams'] ?? 0,
      );
    }).toList();
  }

  Stream<List<FoodTrackTask>> get foodTracks {
    // print("get foodtracks: ");
    return foodTrackCollection.snapshots().map(_scanListFromSnapshot);
    // print(foodTrackCollection.doc(uid).collection('foodTracks').snapshots());
    // return foodTrackCollection
    //     .doc(uid)
    //     .collection('foodTracks')
    //     .snapshots()
    //     .map(_scanListFromSnapshot);
  }

  Future<String> getFoodTrackData(String uid) async {
    DocumentSnapshot snapshot = await foodTrackCollection.doc(uid).get();
    print("getFoodTrackData" + snapshot.toString());
    return snapshot.toString();
  }
}
