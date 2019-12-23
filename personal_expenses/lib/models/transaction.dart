import 'package:flutter/foundation.dart';

class Transaction {
  final String uuid;
  String title;
  final num amount;
  DateTime date;
  String orgName;
  String psAddress;
  String psName;

  Transaction({
    @required this.uuid,
    @required this.title,
    @required this.amount,
    @required this.date,
    @required this.orgName,
    @required this.psAddress,
    @required this.psName,
  });

  factory Transaction.fromJson(Map<String, dynamic> json) {
    return Transaction(
      amount: json['amount'],
      orgName: json['orgName'],
      psAddress: json['psAddress'],
      psName: json['psName'],
      date: DateTime.parse(json['date']),
      uuid: json['uuid'],
      title: json['title'],
    );
  }

  static Transaction fromMap(Map map) => Transaction(
      uuid: map['uuid'],
      title: map['title'],
      amount: map['amount'],
      orgName: map['orgName'],
      psAddress: map['psAddress'],
      psName: map['psName'],
      date: DateTime.parse(map['date']));

  Map<String, dynamic> toMap() => {
        'amount': this.amount,
        'date': this.date.toString(),
        'uuid': this.uuid,
        'orgName': this.orgName,
        'psAddress': this.psAddress,
        'psName': this.psName,
        'title': this.title,
      };

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['amount'] = this.amount;
    data['date'] = this.date.toString();
    data['uuid'] = this.uuid;
    data['orgName'] = this.orgName;
    data['psAddress'] = this.psAddress;
    data['psName'] = this.psName;
    data['title'] = this.title;
    return data;
  }

  @override
  String toString() {
    return 'Transaction{id: $uuid, title: $title}';
  }
}
