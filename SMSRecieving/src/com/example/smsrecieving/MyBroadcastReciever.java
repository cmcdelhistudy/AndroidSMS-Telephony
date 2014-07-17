package com.example.smsrecieving;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyBroadcastReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context ctx, Intent intent) {

		Bundle b = intent.getExtras();
		SmsMessage[] msgs = null;

		String str = "SMS From : ";

		String phNumber = "";
		String code = "";

		if (b != null) {
			Object[] pdus = (Object[]) b.get("pdus");

			msgs = new SmsMessage[pdus.length];

			for (int i = 0; i < msgs.length; i++) {

				msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

				if (i == 0) {
					str += msgs[i].getOriginatingAddress();
					phNumber = msgs[i].getOriginatingAddress();
				}

				str += msgs[i].getMessageBody();
				code = msgs[i].getMessageBody();

				if (code.equals("C")) {
					// Intent call = new Intent(Intent.ACTION_CALL);
					// call.setData(Uri.parse("tel:" + phNumber));
					// call.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					// ctx.startActivity(call);

					AudioManager ad = (AudioManager) ctx.getSystemService(ctx.AUDIO_SERVICE);
					ad.setRingerMode(AudioManager.RINGER_MODE_SILENT);

				}

			}

			Toast.makeText(ctx, "Sarabhjeet : " + str, Toast.LENGTH_LONG)
					.show();
		}
	}
}
