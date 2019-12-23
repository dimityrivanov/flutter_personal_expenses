import 'package:flutter/material.dart';
import 'package:personal_expenses/Locator.dart';
import 'package:personal_expenses/NavigationService.dart';
import 'package:personal_expenses/models/barcodedata.dart';
import 'package:qr_code_scanner/qr_code_scanner.dart';

class QRCode extends StatefulWidget {
  @override
  _QRViewExampleState createState() => _QRViewExampleState();
}

class _QRViewExampleState extends State<QRCode> {
  final GlobalKey qrKey = GlobalKey(debugLabel: 'QR');
  QRViewController controller;
  bool once = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: <Widget>[
          Expanded(
            flex: 5,
            child: QRView(
              key: qrKey,
              onQRViewCreated: _onQRViewCreated,
            ),
          ),
        ],
      ),
    );
  }

  void _onQRViewCreated(QRViewController controller) {
    this.controller = controller;
    controller.scannedDataStream.listen((scanData) {
      if (!once) {
        var qrCodeData = scanData.split("*");

        if (qrCodeData.length == 5) {
          DateTime dateTime =
              DateTime.parse("${qrCodeData[2]} ${qrCodeData[3]}");
          print(dateTime);
          print("Цена: ${qrCodeData[4]}");

          var qrCodeObj = BarcodeData(
              fiid: qrCodeData[0],
              chn: qrCodeData[1],
              xd: DateTime.parse("${qrCodeData[2]} ${qrCodeData[3]}"),
              sum: qrCodeData[4]);

          locator<NavigationService>().goBackWithData(qrCodeObj);
          once = !once;
        } else {
          locator<NavigationService>().goBackWithData(null);
          once = !once;
        }
      }
    });
  }

  @override
  void dispose() {
    controller?.dispose();
    super.dispose();
  }
}
