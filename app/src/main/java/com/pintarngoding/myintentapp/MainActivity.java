package com.pintarngoding.myintentapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE = 75;
    private static final int PICK_CONTACT_REQUEST = 1;
    private Button buttonPindahActivity, buttonPindahActivityDenganData, buttonMoveWithObject, buttonDialNumber, buttonMaps, buttonShare, buttonSMS, buttonGetContactPhone, buttonPindahActivityUntukResult;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPindahActivity = findViewById(R.id.buttonPindahActivity);
        buttonPindahActivity.setOnClickListener(this);

        buttonPindahActivityDenganData = findViewById(R.id.buttonPindahActivityDenganData);
        buttonPindahActivityDenganData.setOnClickListener(this);

        buttonMoveWithObject = findViewById(R.id.buttonPindahActivityDenganObject);
        buttonMoveWithObject.setOnClickListener(this);

        buttonDialNumber = findViewById(R.id.buttonDialNumber);
        buttonDialNumber.setOnClickListener(this);

        buttonMaps = findViewById(R.id.buttonMaps);
        buttonMaps.setOnClickListener(this);

        buttonShare = findViewById(R.id.buttonShare);
        buttonShare.setOnClickListener(this);

        buttonSMS = findViewById(R.id.buttonSMS);
        buttonSMS.setOnClickListener(this);

        buttonPindahActivityUntukResult = findViewById(R.id.buttonPindahActivityUntukResult);
        buttonPindahActivityUntukResult.setOnClickListener(this);

        buttonGetContactPhone = findViewById(R.id.buttonGetContachPhone);
        buttonGetContactPhone.setOnClickListener(this);

        textViewResult = findViewById(R.id.textViewResult);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonPindahActivity:
                Intent moveIntent = new Intent(this, MoveActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.buttonPindahActivityDenganData:
                Intent moveWithDataIntent = new Intent(this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra("extra_name", "PintarNgoding Academy Boy");
                moveWithDataIntent.putExtra("extra_age", 5);
                startActivity(moveWithDataIntent);
                break;
            case R.id.buttonPindahActivityDenganObject:

                break;
            case R.id.buttonDialNumber:
                String phoneNumber = "082302228538";
                Intent dialphone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialphone);
                break;
            case R.id.buttonMaps:
                Intent maps = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-8.208222, 113.686913"));
                startActivity(maps);
                break;
            case R.id.buttonShare:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT, "Mari Belajar Android di Politeknik Negeri Jember");
                share.setType("text/plain");
                startActivity(Intent.createChooser(share, "share link"));
                break;
            case R.id.buttonSMS:
                Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto: 082302228538"));
                sms.putExtra("sms_body", "Assalamualaykum");
                startActivity(sms);
                break;
            case R.id.buttonPindahActivityUntukResult:
                Intent moveForResultIntent = new Intent(this, MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
            case R.id.buttonGetContachPhone:
                Intent pickContact = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                pickContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(pickContact, PICK_CONTACT_REQUEST);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CONTACT_REQUEST){
            if (resultCode == RESULT_OK){
                Uri contactUri = data.getData();
                String []projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);
                textViewResult.setText(number);
            }
        }
        if (requestCode == REQUEST_CODE){
            if (resultCode == MoveForResultActivity.RESULT_CODE){
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_VALUE, 0);
                textViewResult.setText("Hasil: "+selectedValue);
            }
        }
    }
}
