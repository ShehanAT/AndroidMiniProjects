import 'package:calorie_tracker_app/src/model/food_model.dart';
import 'package:calorie_tracker_app/src/model/food_track_task.dart';
// import 'package:blog_app/routes/route_constants.dart';
import 'package:calorie_tracker_app/src/services/food_tracker_service.dart';
import 'package:flutter/material.dart';

class ViewFoodTrack extends StatefulWidget {
  const ViewFoodTrack(this.foodTrack);

  final FoodTrackTask foodTrack;

  @override
  _ViewFoodTrackState createState() => _ViewFoodTrackState();
}

class _ViewFoodTrackState extends State<ViewFoodTrack> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Food Track Entry",
        ),
        leading: IconButton(
          icon: const Icon(
            Icons.arrow_back_ios,
          ),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
      ),
      // backgroundColor: Color(0xffc8d9ff),
      body: Padding(
        padding: const EdgeInsets.only(left: 8.0, right: 8.0),
        child: SingleChildScrollView(
          child: Column(
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: SizedBox(
                  width: MediaQuery.of(context).size.width,
                  child: Card(
                      child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Text(
                      "Name: " +
                          widget.foodTrack.food.name +
                          "\n" +
                          "Mealtime: " +
                          widget.foodTrack.mealTime +
                          "\n" +
                          "Calories: " +
                          widget.foodTrack.food.calories +
                          "\n" +
                          "Carbs: " +
                          widget.foodTrack.food.carbs +
                          "\n" +
                          "Fat: " +
                          widget.foodTrack.food.fat +
                          "\n" +
                          "Protein: " +
                          widget.foodTrack.food.protein,
                      style: const TextStyle(fontSize: 16.0),
                    ),
                  )),
                ),
              ),
              const Divider(),
              Row(
                children: <Widget>[
                  // Expanded(
                  //   child: Padding(
                  //     padding: const EdgeInsets.fromLTRB(12, 4, 4, 4),
                  //     child: Text(
                  //       'Published:${timeago.format(DateTime.fromMillisecondsSinceEpoch(widget.post.date))}',
                  //       style: const TextStyle(
                  //         fontSize: 14.0,
                  //         // color: Color(0xff133337),
                  //       ),
                  //     ),
                  //   ),
                  // ),
                  IconButton(
                    icon: const Icon(Icons.delete),
                    onPressed: () async {
                      FoodTrackerService().deleteFoodTrack(widget.foodTrack);
                      Navigator.pop(context);
                    },
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Navigator.pushNamed(context, RouteConstant.EDIT_POST,
          //     arguments: widget.post);
        },
        child: const Icon(Icons.edit),
      ),
    );
  }
}
