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
 * @author JTom 季江涛
 */
public class MyUtils_V1d2 {

	/**
	 * ## 稳 定! ## 查找当前手机的联系人,并返回一个list集合
	 * 
	 * @uses-permission android.permission.READ_CONTACTS //联系人 读权限
	 * @uses-permission android.permission.WRITE_CONTACTS //联系人 写权限
	 * @param context
	 * @return ArrayList<Contact>集合
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
	 * 以XML格式备份本手机短信到某个路径
	 * 
	 * @param context
	 *            上下文
	 * @param LocalPath
	 *            要保存数据的路径
	 */
	public static void smsCopy(Context context, String LocalPath) {
		// TODO Auto-generated method stub
	}

	/**
	 * 以XML格式备份本手机短信到某个路径
	 * 
	 * @param context
	 *            上下文
	 * @param LocalPath
	 *            从这个地址读取xml文件
	 */
	public static void smsParser(Context context, String LocalPath) {
		// TODO Auto-generated method stub
	}

	/**
	 * ## 稳 定! ## 显示通知栏信息
	 * 
	 * @param Context
	 *            context 上下文
	 * @param int resource 通知栏显示的图片资源,例如:R.drawable.xxx
	 * @param String
	 *            content 通知栏未展开时的通知消息文本
	 * @param String
	 *            content2 通知栏展开后显示的正文
	 * @param String
	 *            title 通知栏展开后的标题信息
	 * @param long date 在某个时间显示 一般填System.currentTimeMillis() 当前系统时间
	 * @param Intent
	 *            intent 设置通知栏单击事件 要执行的意图对象
	 * @param int requestCode 设置通知对象单机时间的请求码 没有请填0
	 * 
	 */
	public static void MyNotification(Context context, int resource,
			String content, String content2, String title, long date,
			Intent intent, int requestCode) {
		// 连接系统通知栏服务,获取通知栏管理对象,类似于binder对象
		NotificationManager nm = (NotificationManager) context
				.getSystemService(context.NOTIFICATION_SERVICE);
		// 创建通知对象
		Notification notification = new Notification(resource, content, date);
		// 创建通知的点击事件的意图对象
		PendingIntent pintent = PendingIntent.getActivity(context, requestCode,
				intent, 0);
		// 设置通知栏展开后的内容和意图
		notification.setLatestEventInfo(context, title, content2, pintent);
		// 显示一个通知
		nm.notify(0, notification);
	}

	/**
	 * ## 安全机制不允许使用此功能 ## 监听通话并将通话记录保存到指定位置
	 * 
	 * @param Context
	 *            context 上下文
	 * @param String
	 *            localPath 将通话记录的音频文件存储到这个位置
	 * @param
	 */
	public static void listenPhoneCall(Context context, String localPath) {
		// context.getSystemService(name);
	}

	/**## 开 发! ##
	 * @param Context context 上下文
	 * @param ArrayList
	 *            <String> list 需要被序列化到手机内存的集合
	 * @param String fileName 保存到手机时的文件名,注意不要写后缀,sp保存的文件为XML
	 * @param int mode 文件保存后的访问权限,要私有化请填写0
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
	 * ## 开 发! ##
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
