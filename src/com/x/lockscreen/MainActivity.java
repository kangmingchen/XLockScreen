package com.x.lockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	private DevicePolicyManager devicePolicyManager = null;

	private static final int REQUEST_CODE_ADD_DEVICE_ADMIN = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 获取设备管理服务
		devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
		if (devicePolicyManager.isAdminActive(Dar.getCn(this))) {
			devicePolicyManager.lockNow();
			devicePolicyManager.lockNow();
			finish();
		} else {
			startAddDeviceAdminAty();
		}
	}

	private void startAddDeviceAdminAty() {
		// 启动设备管理（隐式Intent） - 在AndroidManifest.xml中设定相应过滤器
		Intent i = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		// 权限列表
		i.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, Dar.getCn(this));
		// 描述
		i.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "需要注册该组件后才能拥有锁屏功能哦^_^");
		startActivityForResult(i, REQUEST_CODE_ADD_DEVICE_ADMIN);
	}

	/**
	 * 退出程序
	 */
	private void exitActivity() {
		try {
			android.os.Process.killProcess(android.os.Process.myPid());
		} catch (Exception e) {
			finish();
		}
	}

	@Override
	protected void onDestroy() {
		devicePolicyManager.lockNow();
		devicePolicyManager = null;

		// 结束程序
		exitActivity();

		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			devicePolicyManager.lockNow();
			devicePolicyManager.lockNow();
			finish();
		}
	}
}
