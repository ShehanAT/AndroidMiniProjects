import 'package:calorie_tracker_app/src/model/food_track_task.dart';
import 'package:calorie_tracker_app/src/page/settings_screen.dart';
import 'package:calorie_tracker_app/widgets/foodtrack_card.dart';
import 'package:firebase_database/ui/firebase_animated_list.dart';
import 'package:flutter/material.dart';
import 'package:scoped_model/scoped_model.dart';
import 'src/app.dart';
import 'src/page/food-track/add_food_track.dart';
import 'src/page/history_screen.dart';
import 'src/services/service_locator.dart';
import 'src/model/test_model.dart';
import 'package:calorie_tracker_app/src/utils/enums/view_states.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:provider/provider.dart';
import 'package:calorie_tracker_app/src/providers/theme_notifier.dart';
import 'package:calorie_tracker_app/src/services/shared_preference_service.dart';
import 'package:calorie_tracker_app/helpers/theme.dart';
import 'package:calorie_tracker_app/helpers/colors.dart';
import 'package:calorie_tracker_app/routes/router.dart';
import 'package:calorie_tracker_app/src/model/food_model.dart';
import 'package:firebase_database/firebase_database.dart';

Future<void> main() async {
  setupLocator();
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  await SharedPreferencesService().init();
  // runApp(const MyApp());
  runApp(CalorieTrackerApp()
      // MultiProvider(
      //   providers: <ChangeNotifierProvider<ChangeNotifier>>[
      //     ChangeNotifierProvider<>(create: create)
      //   ],
      //   child: CalorieTrackerApp(),)
      );
}

// class TestView extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     var app = MaterialApp(
//       title: 'Todo',
//       debugShowCheckedModeBanner: false,
//       theme: ThemeData(
//         primarySwatch: Colors.deepPurple,
//         textTheme: TextTheme(
//           headline1: TextStyle(fontSize: 32.0, fontWeight: FontWeight.w400),
//           titleMedium: TextStyle(fontSize: 28.0, fontWeight: FontWeight.w500),
//           bodyMedium: TextStyle(
//             fontSize: 14.0,
//             fontFamily: 'Hind',
//           ),
//         ),
//       ),
//       home: MyHomePage(title: ''),
//     );
//     return ScopedModel<TestModel>(model: TestModel(), child: app);
//   }

//   Widget _getBodyUi(ViewState state) {
//     switch (state) {
//       case ViewState.Busy:
//         return CircularProgressIndicator();
//       case ViewState.Retrieved:
//       default:
//         return Text('Done');
//     }
//   }
// }

class CalorieTrackerApp extends StatefulWidget {
  @override
  _CalorieTrackerAppState createState() => _CalorieTrackerAppState();
}

class _CalorieTrackerAppState extends State<CalorieTrackerApp> {
  // const MyApp({Key? key}) : super(key: key);
  DarkThemeProvider themeChangeProvider = DarkThemeProvider();
  late Widget homeWidget;
  late bool signedIn;

  @override
  void initState() {
    super.initState();
    checkFirstSeen();
  }

  // void getCurrentAppTheme(){
  //   // theme
  // }

  void checkFirstSeen() {
    final bool _firstLaunch = true;
// final bool _firstLaunch = SharedPreferencesService.getFirstLaunch();

    if (_firstLaunch) {
      homeWidget = Homepage();
    } else {
      homeWidget = Homepage();
    }
    setState(() {});
  }

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<DarkThemeProvider>(
      create: (_) {
        return themeChangeProvider;
      },
      child: Consumer<DarkThemeProvider>(
        builder:
            (BuildContext context, DarkThemeProvider value, Widget? child) {
          return GestureDetector(
              onTap: () => hideKeyboard(context),
              child: MaterialApp(
                  debugShowCheckedModeBanner: false,
                  builder: (_, Widget? child) => ScrollConfiguration(
                      behavior: MyBehavior(), child: child!),
                  theme: themeChangeProvider.darkTheme ? darkTheme : lightTheme,
                  home: homeWidget,
                  onGenerateRoute: RoutePage.generateRoute));
        },
      ),
    );
    // const MyHomePage(title: 'Flutter Demo Home Page'),
  }

  void hideKeyboard(BuildContext context) {
    final FocusScopeNode currentFocus = FocusScope.of(context);
    if (!currentFocus.hasPrimaryFocus && currentFocus.focusedChild != null) {
      FocusManager.instance.primaryFocus!.unfocus();
    }
  }
}

// class

class Homepage extends StatefulWidget {
  const Homepage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // appBar: AppBar(
      //   title: const Text('Second Route'),
      // ),
      body: Center(
        child: FlatButton(
          onPressed: () {
            // Navigate back to homepage
          },
          child: const Text('Go Back!'),
        ),
      ),
    );
  }

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _Homepage();
  }
}

class _Homepage extends State<Homepage> with SingleTickerProviderStateMixin {
  final FirebaseDatabase _database = FirebaseDatabase.instance;
  String nodeName = 'foodTrack';
  List<FoodTrackTask> foodTrackList = <FoodTrackTask>[];
  final GlobalKey<ScaffoldState> _globalKey = GlobalKey<ScaffoldState>();
  late Query foodTrackQuery;
  @override
  void initState() {
    super.initState();
    _database.ref().child(nodeName).onChildAdded.listen(_childAdded);
    foodTrackQuery = _database.ref().child('foodTrack');
  }

  void _childAdded(dynamic event) {
    setState(() {
      foodTrackList.add(FoodTrackTask(
          mealTime: "Lunch", food: Food("apple", "200", "20", "20", "20")));
      foodTrackList.add(FoodTrackTask(
          mealTime: "Lunch", food: Food("banana", "300", "20", "20", "20")));
      foodTrackList.add(FoodTrackTask(
          mealTime: "Lunch",
          food: Food("grapefruit", "100", "20", "20", "20")));
      foodTrackList.add(FoodTrackTask(
          mealTime: "Lunch", food: Food("grapes", "150", "20", "20", "20")));
    });
  }

  void onClickAddFoodButton(BuildContext context) {
    Navigator.of(context)
        .push(MaterialPageRoute(builder: (context) => AddFoodScreen()));
  }

  void onClickHistoryScreenButton(BuildContext context) {
    Navigator.of(context)
        .push(MaterialPageRoute(builder: (context) => HistoryScreen()));
  }

  void onClickSettingsScreenButton(BuildContext context) {
    Navigator.of(context)
        .push(MaterialPageRoute(builder: (context) => SettingsScreen()));
  }

  @override
  Widget build(BuildContext context) {
    final ButtonStyle buttonStyle =
        ElevatedButton.styleFrom(textStyle: const TextStyle(fontSize: 20));

    return Scaffold(
        appBar: AppBar(
          title: Text(
            "Homepage",
            style: TextStyle(
                color: Colors.black, fontSize: 20, fontWeight: FontWeight.bold),
          ),
        ),
        body: new Column(
          children: <Widget>[
            Visibility(
                visible: foodTrackList.isNotEmpty,
                child: Flexible(
                  child: FirebaseAnimatedList(
                      query: foodTrackQuery,
                      itemBuilder: (_, DataSnapshot snap,
                          Animation<double> animation, int index) {
                        return FoodTrackCard(
                            foodTrackTask: foodTrackList[index]);
                      }),
                )),
            new ListTile(
                leading: const Icon(Icons.food_bank),
                title: new Text("Welcome to Calorie Tracker App!",
                    textAlign: TextAlign.center,
                    style: const TextStyle(fontWeight: FontWeight.bold))),
            new ElevatedButton(
              onPressed: () {
                onClickAddFoodButton(context);
              },
              child: Text("Add Food"),
            ),
            new ElevatedButton(
                onPressed: () {
                  onClickHistoryScreenButton(context);
                },
                child: Text("History Screen")),
            new ElevatedButton(
                onPressed: () {
                  onClickSettingsScreenButton(context);
                },
                child: Text("Settings Screen"))
          ],
        ));
  }
}

class MyBehavior extends ScrollBehavior {
  @override
  Widget buildViewportChrome(
      BuildContext context, Widget child, AxisDirection axisDirection) {
    return child;
  }
}
