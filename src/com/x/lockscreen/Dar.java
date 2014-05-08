package com.x.lockscreen;

import android.app.admin.DeviceAdminReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class Dar extends DeviceAdminReceiver {
	
	public static ComponentName getCn(Context context){
		return new ComponentName(context, Dar.class);
	}

	@Override
	public void onEnabled(Context context, Intent intent) {
		super.onEnabled(context, intent);
	}

	@Override
	public void onDisabled(Context context, Intent intent) {
		super.onDisabled(context, intent);
	}
	
	
}
