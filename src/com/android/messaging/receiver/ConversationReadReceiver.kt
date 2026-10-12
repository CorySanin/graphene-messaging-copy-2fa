package com.android.messaging.receiver

import android.content.ClipData
import android.content.ClipboardManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.android.messaging.datamodel.action.MarkAsReadAction
import com.android.messaging.ui.UIIntents

class ConversationReadReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == UIIntents.ACTION_MESSAGE_READ) {
            val conversationId = intent.getStringExtra(UIIntents.UI_INTENT_EXTRA_CONVERSATION_ID)
            MarkAsReadAction.markAsRead(conversationId, true)
        }
        else if (intent?.action == UIIntents.ACTION_MESSAGE_COPY_OTP) {
            val conversationId = intent.getStringExtra(UIIntents.UI_INTENT_EXTRA_CONVERSATION_ID)
            val otp = intent.getStringExtra(UIIntents.UI_INTENT_EXTRA_OTP)
            val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("SMS 2FA code", otp)
            clipboard.setPrimaryClip(clip)
            MarkAsReadAction.markAsRead(conversationId, true)
        }
    }
}
