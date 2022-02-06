

import 'package:flutter/material.dart';

import 'auth.dart';


class CalorieTracker extends StatefulWidget {
  const CalorieTracker({Key? key}) : super(key: key);

  @override
  _CalorieTracker createState() => _CalorieTracker();
}

class _CalorieTrackerState extends State<CalorieTracker> {
  final _auth = CalorieTrackerAuth();
  final _navigatorKey = GlobalKey<NavigatorState>();
  late final RouteState _routeState;

  @override
  void initState(){
    // Configure the parser with all the app's allowed path templates
    _routeParser = TemplateRouteParser(
      allowedPaths: [
        '/signin',
        '/authors',
        '/settings',
        '/books/new',
        '/books/all',
        '/books/popular',
        '/books/:bookId',
        '/author/:authorId',
      ],
      guard: _guard,
      initialRoute: '/signin',
    );
  }

  Future<ParsedRoute> _guard(ParsedRoute from) async {
    final signedId = _auth.signedIn;
    final signInRoute = ParsedRoute('/signin', '/signin', {}, {});

    // Go to /signin if the user is not signed in
    if(!signedIn && from != signInRoute){
      return signInRoute;
    }

    else if(signedIn && from == signInRoute){
      return ParsedRoute("/books/popular", "/books/popular", {}, {});
    }
    return from;
  }
}