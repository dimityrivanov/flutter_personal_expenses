import 'package:personal_expenses/models/Result.dart';

class BarcodeServerResponse {
  int status;
  Result result;

  BarcodeServerResponse({this.result, this.status});

  factory BarcodeServerResponse.fromJson(Map<String, dynamic> json) {
    return BarcodeServerResponse(
      result: json['result'] != null ? Result.fromJson(json['result']) : null,
      status: json['status'],
    );
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['status'] = this.status;
    if (this.result != null) {
      data['result'] = this.result.toJson();
    }
    return data;
  }
}
