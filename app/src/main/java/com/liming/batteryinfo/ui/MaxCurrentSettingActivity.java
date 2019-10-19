package com.liming.batteryinfo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.liming.batteryinfo.R;
import com.liming.batteryinfo.utils.BatteryInfo;
import com.liming.batteryinfo.utils.BatteryUtil;
import com.liming.batteryinfo.utils.ViewInject;

public class MaxCurrentSettingActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.battery_text)
    EditText battery_text;
    @ViewInject(R.id.btn_resert)
    Button btn_reset;
    @ViewInject(R.id.btn_submit)
    Button btn_submit;

    BatteryInfo batteryInfo;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_resert:
                finish();
                break;
            case R.id.btn_submit:
                String obj = this.battery_text.getText().toString().trim();
                if (obj.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please enter the current value", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer shell = new StringBuffer();
                if (BatteryUtil.setChargeCurrentMax(Integer.valueOf(obj))) {
                    Toast.makeText(getBaseContext(), String.format("Successfully modified the charging current to%1$smA", obj), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Modify charging current failed", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_current_setting);
        this.btn_reset.setOnClickListener(this);
        this.btn_submit.setOnClickListener(this);
        batteryInfo = BatteryInfo.getInstance(getBaseContext());
        battery_text.setText(String.valueOf(batteryInfo.getChargeCurrentMax()));
    }

}