package com.example.cinemahub_looknfeel;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemahub_looknfeel.databinding.ActivityPaymentBinding;
import com.example.cinemahub_looknfeel.model.Event;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class PaymentActivity extends AppCompatActivity {

    // Add the binder:
    private ActivityPaymentBinding binding;

    // Intents variable:
    public static final String EXTRA_MOVIE = "MovieData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the actionbar:
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Payment");

        init();
    }

    private void writeCCData(String ccName, String ccNumber, String ccExpDate, String ccCVV, Context context) {
        try {
            OutputStreamWriter ccStreamWriter = new OutputStreamWriter( context.openFileOutput("credit_card_information_do_not_read_this_secret_data.txt", Context.MODE_PRIVATE));
            ccStreamWriter.write(ccName + '\n');
            ccStreamWriter.write(ccNumber + '\n');
            ccStreamWriter.write(ccExpDate + '\n');
            ccStreamWriter.write(ccCVV);
            ccStreamWriter.close();
        }
        catch (IOException e) {
            // Create the Snackbar to be displayed to the user:
            Snackbar snackbar = Snackbar.make(binding.getRoot(), "Credit Card information has not been saved...", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }

    private String readCCData(Context context) {

        String returnCCData = "";

        try {
            InputStream ccInputStream = context.openFileInput("credit_card_information_do_not_read_this_secret_data.txt");

            if ( ccInputStream != null ) {
                InputStreamReader ccInputStreamReader = new InputStreamReader(ccInputStream);
                BufferedReader bufferedReader = new BufferedReader(ccInputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                ccInputStream.close();
                returnCCData = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Snackbar snackbar = Snackbar.make(binding.getRoot(), "Credit Card information has not been found...", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } catch (IOException e) {
            Snackbar snackbar = Snackbar.make(binding.getRoot(), "Credit Card information can not be read...", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

        return returnCCData;
    }

    private void gotToTicketView(Event e) {

        // Create the intent to pass to the Movie Seating Chart Activity:
        Intent toQRCodeIntent = new Intent(PaymentActivity.this, TicketViewActivity.class);

        // Set the intent to pass the event object:
        toQRCodeIntent.putExtra( EXTRA_MOVIE, e );

        // Start the new activity and send the data at the same time:
        startActivity(toQRCodeIntent);

    }

    public void init() {
        // Get the intents from the Movie Details Activity:
        Event eventReceiving = getIntent().getParcelableExtra(PaymentMethodActivity.EXTRA_MOVIE);

        binding.btnProceed.setOnClickListener(v -> {
            // Save the credit card data if selected:
            if(binding.sSaveCC.isChecked()) {
                writeCCData(binding.etNameOnCard.getText().toString(),
                        binding.etCreditCardNumber.getText().toString(),
                        binding.etMonthYear.getText().toString(),
                        binding.etCVV.getText().toString(),
                        this);

                String returnCCData = readCCData(this);

                Snackbar snackbar =  Snackbar.make(binding.getRoot(), returnCCData, Snackbar.LENGTH_LONG).setDuration(Snackbar.LENGTH_LONG).addCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);

                        gotToTicketView(eventReceiving);
                    }
                });
                View snackbarView = snackbar.getView();
                TextView tv = (TextView) snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
                tv.setMaxLines(5);
                snackbar.show();
            }

//            // TODO: Move the next few lines to their own function so the snackbar will have time to display before proceeding to the next activity...
//
//            // Create the intent to pass to the Movie Seating Chart Activity:
//            Intent toQRCodeIntent = new Intent(PaymentActivity.this, TicketViewActivity.class);
//
//            // Set the intent to pass the event object:
//            toQRCodeIntent.putExtra( EXTRA_MOVIE, eventReceiving );
//
//            // Start the new activity and send the data at the same time:
//            startActivity(toQRCodeIntent);
        });

        // Get the view for the credit cards month and year TextInputEditText widget. Then set its
        // input type to null to prevent the keyboard from showing up:
        //TextInputEditText ccMonthYear = findViewById(R.id.etMonthYear);
        binding.etMonthYear.setInputType(InputType.TYPE_NULL);

        // Set a onTouch listener for the TextInputEditText so we can get the datePicker input:
        binding.etMonthYear.setOnTouchListener((view, motionEvent) -> {
            // Define that when the user lets go, the action is triggered:
            if(motionEvent.getAction() == MotionEvent.ACTION_UP){

                // Get an instance of a calendar:
                final Calendar cal = Calendar.getInstance();

                // Set the the day, month and year variables:
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);   // Not used

                // Creating the DatePickerDialog:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Add the month and year to the edit text:
                        binding.etMonthYear.setText((monthOfYear + 1) + " / " + year);
                    }
                }, year, month, day);

                // Display the datePicker:
                datePickerDialog.show();
            }

            return false;
        });

//        binding.btnProceed.setOnClickListener(v -> {
//            if(binding.sSaveCC.isChecked()) {
//                writeCCData(binding.etNameOnCard.getText().toString(),
//                            binding.etCreditCardNumber.getText().toString(),
//                            binding.etMonthYear.getText().toString(),
//                            binding.etCVV.getText().toString(),
//                            this);
//
//                readCCData(this);
//            }
//        });
    }

}
