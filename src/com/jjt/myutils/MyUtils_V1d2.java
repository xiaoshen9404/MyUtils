package com.jjt.myutils;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;

import com.jjt.myutils.domain.Contact;

/**
 * @version V1.02
 * @author JTom ������
 */
public class MyUtils_V1d2 {

	/**
	 * ## �� ��! ## ���ҵ�ǰ�ֻ�����ϵ��,������һ��list����
	 * 
	 * @uses-permission android.permission.READ_CONTACTS //��ϵ�� ��Ȩ��
	 * @uses-permission android.permission.WRITE_CONTACTS //��ϵ�� дȨ��
	 * @param context
	 * @return ArrayList<Contact>����
	 */
	public static ArrayList<Contact> getContacts(Context context) {
		ArrayList<Contact> list = new ArrayList<Contact>();
		ContentResolver resolver = context.getContentResolver();
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		Cursor cursor = resolver.query(uri, new String[] { "contact_id" },
				null, null, null);
		while (cursor.moveToNext()) {
			Contact cont = new Contact();
			int id = cursor.getInt(0);
			Cursor dataCs = resolver.query(dataUri, new String[] { "data1",
					"mimetype" }, "contact_id=?", new String[] { id + "" },
					null);
			while (dataCs.moveToNext()) {
				String type = dataCs.getString(1);
				String data = dataCs.getString(0);
				if ("vnd.android.cursor.item/name".equals(type)) {
					cont.setName(data);
				} else if ("vnd.android.cursor.item/im".equals(type)) {
					cont.setQQ(data);
				} else if ("vnd.android.cursor.item/email_v2".equals(type)) {
					cont.setEmail(data);
				} else if ("vnd.android.cursor.item/phone_v2".equals(type)) {
					cont.setPhone(data);
				}
			}
			cont.setId(id);
			list.add(cont);
		}

		return list;
	}

	/**
	 * 
	 * ��XML��ʽ���ݱ��ֻ����ŵ�ĳ��·��
	 * 
	 * @param context
	 *            ������
	 * @param LocalPath
	 *            Ҫ�������ݵ�·��
	 */
	public static void smsCopy(Context context, String LocalPath) {
		// TODO Auto-generated method stub
	}

	/**
	 * ��XML��ʽ���ݱ��ֻ����ŵ�ĳ��·��
	 * 
	 * @param context
	 *            ������
	 * @param LocalPath
	 *            �������ַ��ȡxml�ļ�
	 */
	public static void smsParser(Context context, String LocalPath) {
		// TODO Auto-generated method stub
	}

	/**
	 * ## �� ��! ## ��ʾ֪ͨ����Ϣ
	 * 
	 * @param Context
	 *            context ������
	 * @param int resource ֪ͨ����ʾ��ͼƬ��Դ,����:R.drawable.xxx
	 * @param String
	 *            content ֪ͨ��δչ��ʱ��֪ͨ��Ϣ�ı�
	 * @param String
	 *            content2 ֪ͨ��չ������ʾ������
	 * @param String
	 *            title ֪ͨ��չ����ı�����Ϣ
	 * @param long date ��ĳ��ʱ����ʾ һ����System.currentTimeMillis() ��ǰϵͳʱ��
	 * @param Intent
	 *            intent ����֪ͨ�������¼� Ҫִ�е���ͼ����
	 * @param int requestCode ����֪ͨ���󵥻�ʱ��������� û������0
	 * 
	 */
	public static void MyNotification(Context context, int resource,
			String content, String content2, String title, long date,
			Intent intent, int requestCode) {
		// ����ϵͳ֪ͨ������,��ȡ֪ͨ���������,������binder����
		NotificationManager nm = (NotificationManager) context
				.getSystemService(context.NOTIFICATION_SERVICE);
		// ����֪ͨ����
		Notification notification = new Notification(resource, content, date);
		// ����֪ͨ�ĵ���¼�����ͼ����
		PendingIntent pintent = PendingIntent.getActivity(context, requestCode,
				intent, 0);
		// ����֪ͨ��չ��������ݺ���ͼ
		notification.setLatestEventInfo(context, title, content2, pintent);
		// ��ʾһ��֪ͨ
		nm.notify(0, notification);
	}

	/**
	 * ## ��ȫ���Ʋ�����ʹ�ô˹��� ## ����ͨ������ͨ����¼���浽ָ��λ��
	 * 
	 * @param Context
	 *            context ������
	 * @param String
	 *            localPath ��ͨ����¼����Ƶ�ļ��洢�����λ��
	 * @param
	 */
	public static void listenPhoneCall(Context context, String localPath) {
		// context.getSystemService(name);
	}

	/**## �� ��! ##
	 * @param Context context ������
	 * @param ArrayList
	 *            <String> list ��Ҫ�����л����ֻ��ڴ�ļ���
	 * @param String fileName ���浽�ֻ�ʱ���ļ���,ע�ⲻҪд��׺,sp������ļ�ΪXML
	 * @param int mode �ļ������ķ���Ȩ��,Ҫ˽�л�����д0
	 * @return boolean
	 */
	public static boolean copy4SharedPreference(Context context,String fileName,int mode,
			ArrayList<String> list) {
		SharedPreferences sp= context.getSharedPreferences(fileName, 0);
		Editor edit =sp.edit();
		for (String string : list) {
			
			
		}
		
		return false;
	}
	/**
	 * ## �� ��! ##
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static HashMap<String, String> read4SharedPreference(Context context,String fileName){
		HashMap<String, String> hm=new HashMap<String, String>();
		SharedPreferences sp=context.getSharedPreferences(fileName, 0);
		
		return hm;
	}
	
	

}
