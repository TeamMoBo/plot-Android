package com.project.mobo.common

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference

fun DatabaseReference.addChildEventListener(
    onCancelled : (DatabaseError) -> Unit = {},
    onChildMoved : (DataSnapshot, String?) -> Unit = { _, _ -> Unit },
    onChildChanged : (DataSnapshot, String?) -> Unit = { _, _ -> Unit },
    onChildAdded : (DataSnapshot, String?) -> Unit = { _, _ -> Unit },
    onChildRemoved: (DataSnapshot) -> Unit = {}
) {
    this.addChildEventListener(object : ChildEventListener {
        override fun onCancelled(p0: DatabaseError) { onCancelled(p0) }

        override fun onChildMoved(p0: DataSnapshot, p1: String?) { onChildMoved(p0, p1) }

        override fun onChildChanged(p0: DataSnapshot, p1: String?) { onChildChanged(p0, p1) }

        override fun onChildAdded(p0: DataSnapshot, p1: String?) { onChildAdded(p0, p1) }

        override fun onChildRemoved(p0: DataSnapshot) { onChildRemoved(p0) }
    })
}
