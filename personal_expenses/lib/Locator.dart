import 'package:get_it/get_it.dart';
import 'package:personal_expenses/NavigationService.dart';

GetIt locator = GetIt.instance;

void setupLocator() {
  locator.registerLazySingleton(() => NavigationService());
}
