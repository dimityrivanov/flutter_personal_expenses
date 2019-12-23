import 'package:dio/dio.dart';
import 'package:intl/intl.dart';
import 'package:personal_expenses/models/BarcodeServerResponse.dart';
import 'package:personal_expenses/models/barcodedata.dart';

class ServerClient {
  final String baseUrl;
  final Dio dio;

  ServerClient({
    this.baseUrl = "https://nramobile.uslugi.io/api/rpc/nra/receiptcheck",
  }) : this.dio = new Dio();

  Future<BarcodeServerResponse> getServerBarcodeData(
      BarcodeData barcodeData) async {
    String formattedDate = DateFormat('yyyyMMddTHHmmss').format(barcodeData.xd);

    FormData formData = FormData.fromMap({
      "fiid": "${barcodeData.fiid}",
      "chn": "${barcodeData.chn}",
      "xd": "$formattedDate",
      "sum": "${barcodeData.sum}"
    });

    Dio dio = new Dio();
    Response response = await dio.post(baseUrl, data: formData);

    BarcodeServerResponse responseObj = BarcodeServerResponse.fromJson(response.data);

    if (responseObj.result.eik == null) {
      throw NullThrownError();
    }

    return responseObj;
  }
}
