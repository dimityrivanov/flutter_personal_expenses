package models

class ServerError {
    Result result;
    int status;

    ServerError({this.result, this.status});

    factory ServerError.fromJson(Map<String, dynamic> json) {
        return ServerError(
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