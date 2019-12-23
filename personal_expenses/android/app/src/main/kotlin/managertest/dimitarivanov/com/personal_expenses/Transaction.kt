package managertest.dimitarivanov.com.personal_expenses

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

//import io.realm.annotations.RealmClass

//@RealmClass
open class Transaction(
        @PrimaryKey
        open var uuid: String? = null,
        open var title: String? = null,
        open var amount: Double? = null,
        open var date: String? = null,
        open var orgName: String? = null,
        open var psAddress: String? = null,
        open var psName: String? = null
) : RealmObject()
