package project.com.isly.fragments;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import project.com.isly.R;
import project.com.isly.adapter.RecyclerAdapter;
import project.com.isly.helpers.DBH;
import project.com.isly.models.Student;

/**
 * Created by juan on 13/10/15.
 * Activity which is able to show the easiest way to take list
 */
public class Take extends Fragment implements View.OnClickListener {
    private BluetoothAdapter bluetoothAdapter;
    RecyclerView rv;
    List<Student> items;
    private static final int REQUEST_ENABLE_BT = 1;
    private Button btn,btnCancel;
    RecyclerAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.take, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);
        items= new ArrayList<>();
        adapter=new RecyclerAdapter(items);
        rv.setAdapter(adapter);

        btn=(Button)v.findViewById(R.id.btn);
        btn.setOnClickListener(this);

        btnCancel=(Button)v.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        bluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter==null){
            //If the bluetooth adapter is null or the mobile phone doesn't support bluetooth
            Toast.makeText(getActivity().getApplicationContext(),R.string.unexpected,Toast.LENGTH_SHORT).show();
        }else{
            if (bluetoothAdapter.isEnabled()){
                //If the bluetooth is enabled (ON)
            }else{
                //if the bluetooth is disabled (OFF) then turn on!
                on();
            }
        }
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                find();
                btn.setEnabled(false);
                break;
            case R.id.btnCancel:
                bluetoothAdapter.cancelDiscovery();
                btn.setEnabled(true);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == REQUEST_ENABLE_BT) {
            if (bluetoothAdapter.isEnabled()) {
                //text.setText("Status: Habilitado");
                //mySwitch.setChecked(true);
            } else {
//                text.setText("Status: Desabilitado");
  //              mySwitch.setChecked(false);
            }
        }
    }
    final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction(), name;
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name and the MAC address of the object to the arrayAdapter
                if(device.getName()==null) name= "No available";
                else name= device.getName();
                items.add(new Student(name,name, device.getAddress()));
                adapter.notifyItemInserted(items.size());

                if(!(DBH.checkIfExists(getActivity().getApplicationContext(),new Student(name,name, device.getAddress())))){
                    DBH.addNewStudent(getActivity().getApplicationContext(),new Student(name,name, device.getAddress()));
                }
            }
        }
    };

    //Turn the bluetooth on
    private void on(){
        Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOnIntent, REQUEST_ENABLE_BT);
    }

    private void off(){
       bluetoothAdapter.disable();
    }

    private void find(){
        if(bluetoothAdapter.isDiscovering()){

        }else{
            bluetoothAdapter.startDiscovery();
            Toast.makeText(getActivity().getApplicationContext(), R.string.taking, Toast.LENGTH_SHORT).show();
            getActivity().registerReceiver(broadcastReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));

        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        try {
            getActivity().unregisterReceiver(broadcastReceiver);
        } catch (Exception ex) {
        }
    }
}
