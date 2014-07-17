package com.example.sarabhjeettelephonymanager;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView tvBasic;
	TextView tvCellStrength;

	String basicInfo = "";
	String signalStrengthInfo = "";

	TelephonyManager telmgr;

	PhoneStateListener callStateListener;
	PhoneStateListener signalStrengthStateListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvBasic = (TextView) findViewById(R.id.basicInfo);
		tvCellStrength = (TextView) findViewById(R.id.tvCell);

		telmgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		basicInfo += "Ph Number : " + telmgr.getLine1Number();
		basicInfo += "\n Net Op Name : " + telmgr.getNetworkOperatorName();
		basicInfo += "\n Sim No : " + telmgr.getSimSerialNumber();
		basicInfo += "\n Sim Op Name  : " + telmgr.getSimOperatorName();
		basicInfo += "\n Device ID/IMEI No : " + telmgr.getDeviceId();
		basicInfo += "\n Ph Type : " + telmgr.getPhoneType();

		tvBasic.setText("Basic Info : \n" + basicInfo);

		callStateListener = new PhoneStateListener() {
			@Override
			public void onCallStateChanged(int state, String incomingNumber) {

				switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:
					Toast.makeText(getBaseContext(),
							"Phone IDLE " + incomingNumber, Toast.LENGTH_LONG)
							.show();
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					Toast.makeText(getBaseContext(),
							"Phone OFFHOOK  " + incomingNumber,
							Toast.LENGTH_LONG).show();
					break;
				case TelephonyManager.CALL_STATE_RINGING:
					Toast.makeText(getBaseContext(),
							"Ringing  " + incomingNumber, Toast.LENGTH_LONG)
							.show();
					break;

				default:
					break;
				}

				super.onCallStateChanged(state, incomingNumber);
			}

		};

		telmgr.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);

		signalStrengthStateListener = new PhoneStateListener() {

			@Override
			public void onSignalStrengthsChanged(SignalStrength signalStrength) {

				signalStrengthInfo = "Signal Strength : "
						+ signalStrength.getGsmSignalStrength();

				tvCellStrength.setText(signalStrengthInfo);
				
				super.onSignalStrengthsChanged(signalStrength);
			}

		};

		telmgr.listen(signalStrengthStateListener,
				PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

	}
}
