//
//  Transaction.swift
//  Runner
//
//  Created by Dimitar Ivanov on 7.11.19.
//  Copyright © 2019 The Chromium Authors. All rights reserved.
//
import Realm

class Transaction : RLMObject {
    // Optional string property, defaulting to nil
    @objc dynamic var uuid = ""
    @objc dynamic var title = ""
    @objc dynamic var amount = 0.0
    @objc dynamic var date = ""
    
    override class func primaryKey() -> String? {
        return "uuid"
    }
}
