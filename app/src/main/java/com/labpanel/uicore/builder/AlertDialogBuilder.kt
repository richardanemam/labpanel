package com.labpanel.uicore.builder

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent

class AlertDialogBuilder private constructor(val builder: AlertDialog.Builder) {

    class Builder(context: Context) {

        private val builder = AlertDialog.Builder(context)

        fun title(title: Int): Builder {
            builder.apply { setTitle(title) }
            return this
        }

        fun message(message: Int): Builder {
            builder.apply { setMessage(message) }
            return this
        }

        fun positiveButton(text: Int, intent: Intent): Builder {
            builder.apply {
                setPositiveButton(text) { _: DialogInterface, _: Int ->
                    context.startActivity(intent)
                }
            }
            return this
        }

        fun negativeButton(text: Int, intent: Intent): Builder {
            builder.apply {
                setNegativeButton(text) { _: DialogInterface, _: Int ->
                    context.startActivity(intent)
                }
            }
            return this
        }

        fun neutralButton(text: Int): Builder {
            builder.apply {
                setNeutralButton(text) { dialogInterface: DialogInterface, _: Int ->
                    dialogInterface.cancel()
                }
            }
            return this
        }

        fun build() = AlertDialogBuilder(builder)
    }
}

/*
 var title: String? = null,
                       var message: String? = null, var positiveBtnText: String? = null,
                       var negativeBtnText: String? = null, var neutralBtnText: String? = null
 */