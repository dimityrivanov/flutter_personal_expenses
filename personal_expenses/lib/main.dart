import 'package:flutter/material.dart';
import 'package:flutter_realm/flutter_realm.dart';
import 'package:personal_expenses/Locator.dart';
import 'package:personal_expenses/NavigationService.dart';
import 'package:personal_expenses/QRScanner.dart';
import 'package:personal_expenses/helpers/local_realm_provider.dart';
import 'package:personal_expenses/helpers/server_client.dart';
import 'package:personal_expenses/models/BarcodeServerResponse.dart';
import 'package:personal_expenses/models/barcodedata.dart';
import 'package:personal_expenses/models/transaction.dart';
import 'package:personal_expenses/widgets/chart.dart';
import 'package:personal_expenses/widgets/new_transaction.dart';
import 'package:personal_expenses/widgets/transaction_list.dart';
import 'package:uuid/uuid.dart';

void main() {
  setupLocator();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      navigatorKey: locator<NavigationService>().navigatorKey,
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: RealmProvider(
          builder: (realm) => MyHomePage(
                realm: realm,
              )),
    );
  }
}

class MyHomePage extends StatefulWidget {
  final Realm realm;

  const MyHomePage({Key key, this.realm}) : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final List<Transaction> _userTransactions = [
    // Transaction(
    //   id: 't1',
    //   title: 'New Shoes',
    //   amount: 69.99,
    //   date: DateTime.now(),
    // ),
    // Transaction(
    //   id: 't2',
    //   title: 'Weekly Groceries',
    //   amount: 16.53,
    //   date: DateTime.now(),
    // ),
  ];
  bool isFilterActive = false;
  DateTime filterDateTime;

  @override
  void initState() {
    super.initState();
    _fetchAll();
  }

  _fetchAll() async {
    final List all = await widget.realm.allObjects('Transaction');

    setState(() {
      final all_transactions =
          all.cast<Map>().map(Transaction.fromMap).toList();

      all_transactions.forEach((transaction) {
        _userTransactions.add(transaction);
      });
    });
  }

  Future _onAdd(Transaction transaction) async {
    await widget.realm.createObject('Transaction', transaction.toMap());
  }

  List<Transaction> get _recentTransactions {
    return _userTransactions.where((tx) {
      return tx.date.isAfter(
        DateTime.now().subtract(
          Duration(days: 7),
        ),
      );
    }).toList();
  }

  void _addNewTransaction(Transaction transaction) {
    setState(() {
      _userTransactions.add(transaction);
    });

    _onAdd(transaction);
  }

  List<Transaction> get _getFilteredList {
    return _userTransactions.where((tx) {
      return tx.date.day == filterDateTime.day;
    }).toList();
  }

  void _barChartSelected(DateTime timeFrame) {
    setState(() {
      //same date is selected twice
      if (filterDateTime != null &&
          (timeFrame.day == filterDateTime.day) &&
          (timeFrame.month == filterDateTime.month)) {
        isFilterActive = false;
        filterDateTime = null;
      } else {
        isFilterActive = true;
        filterDateTime = DateTime.parse(timeFrame.toString());
      }
    });
  }

  void _prepareNewTransaction(String txTitle, double txAmount,
      DateTime chosenDate, BarcodeServerResponse serverResponse) {
    final newTx = Transaction(
        title: txTitle,
        amount: txAmount,
        date: chosenDate,
        uuid: Uuid().v4(),
        psName: serverResponse.result.psName,
        psAddress: serverResponse.result.psAddress,
        orgName: serverResponse.result.orgName);

    _startAddNewTransaction(context, newTx);
  }

  void _startAddNewTransaction(BuildContext ctx, Transaction transaction) {
    showModalBottomSheet(
      context: ctx,
      builder: (_) {
        return GestureDetector(
          onTap: () {},
          child: NewTransaction(_addNewTransaction, transaction),
          behavior: HitTestBehavior.opaque,
        );
      },
    );
  }

  void _deleteTransaction(String id) {
    setState(() {
      widget.realm.delete('Transaction', primaryKey: id);
      _userTransactions.removeWhere((tx) => tx.uuid == id);
    });
  }

  _displayQRCode(BuildContext context) async {
    BarcodeData result = await Navigator.push(
      context,
      // Create the SelectionScreen in the next step.
      MaterialPageRoute(builder: (context) => QRCode()),
    );

    if (result == null) {
      return;
    }

    var timeDiffInMinutes = DateTime.now().difference(result.xd).inMinutes;

    //this the ETA for data to arrive in NAP servers!
    if (timeDiffInMinutes >= 10) {
      final server = ServerClient();
      server.getServerBarcodeData(result).then((barcodeServerResponse) {
        _prepareNewTransaction(barcodeServerResponse.result.psName,
            double.parse(result.sum), result.xd, barcodeServerResponse);
      }).catchError((error) {
        var mitko = error;
      });
    } else {
      //add it and retrive data after 10 minutes
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Personal Expenses',
        ),
      ),
      body: SingleChildScrollView(
        child: Column(
          // mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            Chart(_recentTransactions, _barChartSelected),
            TransactionList(
                isFilterActive ? _getFilteredList : _userTransactions,
                _deleteTransaction),
          ],
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
      floatingActionButton: FloatingActionButton(
        child: Center(
          child: Image.asset(
            'assets/images/qr.png',
            width: 30,
            height: 30,
            fit: BoxFit.cover,
          ),
        ),
        onPressed: () => _displayQRCode(context),
      ),
    );
  }
}
