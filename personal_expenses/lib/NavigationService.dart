import 'package:flutter/material.dart';
import 'package:personal_expenses/models/barcodedata.dart';

class NavigationService {
  final GlobalKey<NavigatorState> navigatorKey =
      new GlobalKey<NavigatorState>();

  Future<dynamic> navigateTo(String routeName) {
    return navigatorKey.currentState.pushNamed(routeName);
  }

  bool goBack() {
    return navigatorKey.currentState.pop();
  }

  bool goBackWithData(BarcodeData data) {
    return navigatorKey.currentState.pop(data);
  }
}
