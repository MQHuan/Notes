# Get all the bluetooth service uuids of the devices

## Step 1: Add Broadcast Action *ACTION_UUID*
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    ...

    // Register for broadcasts when a device is discovered.
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
    filter.addAction(BluetoothDevice.ACTION_UUID);
    registerReceiver(mReceiver, filter);
}

// Create a BroadcastReceiver for ACTION_FOUND.
private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            // Discovery has found a device. Get the BluetoothDevice
            // object and its info from the Intent.
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            String deviceName = device.getName();
            String deviceHardwareAddress = device.getAddress(); // MAC address
        }
    }
};

@Override
protected void onDestroy() {
    super.onDestroy();
    ...

    // Don't forget to unregister the ACTION_FOUND receiver.
    unregisterReceiver(mReceiver);
}
```

## Step 2: Wait for BluetoothAdapter.ACTION_DISCOVERY_FINISHED ,Call fetchUuidsWithSdp()

Example:
```
ArrayList<BluetoothDevice> mDeviceList = new ArrayList<BluetoothDevice>();

private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            mDeviceList.add(device);
        } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            // discovery has finished, give a call to fetchUuidsWithSdp on first device in list.
            if (!mDeviceList.isEmpty()) {
                BluetoothDevice device = mDeviceList.remove(0);
                boolean result = device.fetchUuidsWithSdp();
            }
        } else if (BluetoothDevice.ACTION_UUID.equals(action)) {
            // This is when we can be assured that fetchUuidsWithSdp has completed.
            // So get the uuids and call fetchUuidsWithSdp on another device in list

            BluetoothDevice deviceExtra = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Parcelable[] uuidExtra = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_UUID);
            System.out.println("DeviceExtra address - " + deviceExtra.getAddress());
            if (uuidExtra != null) {
                for (Parcelable p : uuidExtra) {
                    System.out.println("uuidExtra - " + p);
                }
            } else {
                System.out.println("uuidExtra is still null");
            }
            if (!mDeviceList.isEmpty()) {
                BluetoothDevice device = mDeviceList.remove(0);
                boolean result = device.fetchUuidsWithSdp();
            }
        }
    }
}
```

example address: https://stackoverflow.com/questions/14812326/android-bluetooth-get-uuids-of-discovered-devices


reference link: https://developer.android.com/guide/topics/connectivity/bluetooth#java
