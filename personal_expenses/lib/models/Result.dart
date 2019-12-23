import 'package:uuid/uuid.dart';

class Result {
  String uuid;
  String eik;
  String orgName;
  String psAddress;
  String psName;
  double sum;

  Result(
      {this.uuid,
      this.eik,
      this.orgName,
      this.psAddress,
      this.psName,
      this.sum});

  factory Result.fromJson(Map<String, dynamic> json) {
    return Result(
      uuid: Uuid().v4(),
      eik: json['eik'],
      orgName: json['orgName'],
      psAddress: json['psAddress'],
      psName: json['psName'],
      sum: json['sum'],
    );
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['uuid'] = this.uuid;
    data['eik'] = this.eik;
    data['orgName'] = this.orgName;
    data['psAddress'] = this.psAddress;
    data['psName'] = this.psName;
    data['sum'] = this.sum;
    return data;
  }
}
