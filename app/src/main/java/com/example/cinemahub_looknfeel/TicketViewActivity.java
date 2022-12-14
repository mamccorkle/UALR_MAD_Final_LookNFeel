package com.example.cinemahub_looknfeel;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemahub_looknfeel.databinding.ActivityOrderSummaryBinding;
import com.example.cinemahub_looknfeel.databinding.ActivityTicketViewBinding;
import com.example.cinemahub_looknfeel.model.Event;
import com.example.cinemahub_looknfeel.model.Seat;

import java.util.Locale;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class TicketViewActivity extends AppCompatActivity {

    // Variables to hold the qr code bitmap and the encoder itself:
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    // Add the binder:
    private ActivityTicketViewBinding binding;

    // Intents variable:
    public static final String EXTRA_MOVIE = "MovieData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityTicketViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the actionbar:
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();
    }

    public void init() {
        // Get the intents from the previous screen:
        Event eventReceiving = getIntent().getParcelableExtra(PaymentActivity.EXTRA_MOVIE);

        // below line is for getting the windowmanager service.
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // generating dimension from width and height.
        int dimen = Math.min(point.x, point.y);

        String data = "";
        //  Event
        //        Movie:
        //                private String       title;
        //                private String       date;
        //                private Rating       rating;
        //                            G, PG, PG13, R, NC17
        //                private double       score;
        //                private int          runtime;
        //                private String       synopsis;
        //                private String       director;
        //                private List<String> cast;
        //                private Genre        genre;
        //
        //                private List<String> showTimes;
        //                private int          imageIndex;
        //                private String       youtubeLink;
        //        Theater:
        //                String name;
        //                String address;
        //                String phone;
        //                String website;
        //        Seat:
        //                Boolean handicap;
        //                Boolean reclinable;
        //                int id;
        //        int          persons;
        //        List<String> concessions;
        //        String       showTime;

        // Epoch time as id:
        long epochTime = System.currentTimeMillis();

        // Date in the form: yyyyMMddHHmmss
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);

        // TODO: REMOVE...
        String showTime = "7:30 PM";

        // Concatenate the data to be sent to the QR Code:
        data = "[START]\n" +
               "id: " + String.valueOf(epochTime) + '\n' +
               "title: " + eventReceiving.getMovie().getTitle() + '\n' +
               "date: " + String.valueOf(dateFormat) + '\n' +
//               "showTime: " + eventReceiving.getShowTime() + '\n' +
                "showTime: " + showTime + '\n' +
               "theater: " + eventReceiving.getTheater().getName() + '\n';

        for ( Seat seat : eventReceiving.getSeat() ) {
            data += "seat: " + seat.getId() + '\n';
        }

        data += "persons: " + eventReceiving.getPersons() + '\n';

        for ( String c : eventReceiving.getConcessions() ) {
            data += "concessions: " + c + '\n';
        }

        data += "[END]";

        Log.d("OUTPUT_DATA", data);

        // setting this dimensions inside our qr code encoder to generate our qr code.
        qrgEncoder = new QRGEncoder(data, QRGContents.Type.TEXT, dimen);

        // getting our qrcode in the form of bitmap. bitmap = qrgEncoder.encodeAsBitmap();
        bitmap = qrgEncoder.getBitmap();

        // the bitmap is set inside our image view using .setimagebitmap method.
        binding.ivQRcode.setImageBitmap(bitmap);
    }

}