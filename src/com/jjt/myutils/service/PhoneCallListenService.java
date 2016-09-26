package com.jjt.myutils.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**##  �� ��!
 * ͨ��¼��
 * @author JTom
 *  <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
 *  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
 *  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 *  <uses-permission android:name="android.permission.RECORD_AUDIO"/>
 *
 */
public class PhoneCallListenService extends Service {
	private TelephonyManager tm;
	private MediaRecorder mRecorder;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {

		super.onCreate();
	}

	private class MyPhoneListener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:// ����,�޵绰�¼�
				System.out.println("����״̬");
				if (mRecorder != null) {
					stopRecording();
				}
				break;

			case TelephonyManager.CALL_STATE_RINGING:// ����
				System.out.println("�绰����");
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:// ͨ��״̬
				System.out.println("��ʼͨ��");
				startRecording();
				break;
			}

		}

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		tm.listen(new MyPhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	/**
	 * ��ʼ¼��
	 */
	private void startRecording() {
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(Environment.getExternalStorageDirectory() + "/"
				+ System.currentTimeMillis() + ".3gp");
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			mRecorder.prepare();
		} catch (Exception e) {
			System.out.println("prepare() failed");
		}

		mRecorder.start();
	}

	/**
	 * ֹͣ¼��
	 */
	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

}
