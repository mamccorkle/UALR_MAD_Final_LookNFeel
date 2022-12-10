//package com.example.cinemahub_looknfeel.fragment;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.DialogFragment;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.example.cinemahub_looknfeel.R;
//
//public class TicketDialogFragment extends DialogFragment {
//
//    private static final String TITLE = "Title";
//    private static final String THEATER = "Theater";
//    private static final String TIME = "Time";
//    private static final String ID = "ID";
//
//    public interface TicketDialogListener {
//        public void onDialogYesClick(boolean yes);
//    }
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        //get data from Movie
//        Bundle args = getArguments();
//        String title = args.getString(TITLE);
//        String theater = args.getString(THEATER);
//        String time = args.getString(TIME);
//        int id = args.getInt(ID);
//
//        //create the dialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage(R.string.dialog_msg).setTitle(R.string.dialog_title);
//
//        //on yes, start the SeatFragment
//        builder.setPositiveButton(R.string.yes_btn_label, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                SeatFragment fragment = new SeatFragment();
//
//                //put the data for the movie
//                Bundle args = new Bundle();
//                args.putString(TITLE, title);
//                args.putString(TIME, time);
//                args.putString(THEATER, theater);
//                args.putInt(ID, id);
//
//                fragment.setArguments(args);
//
//                //start the SeatFragment
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.nav_host_fragment, fragment).addToBackStack(null);
//                ft.commit();
//            }
//        });
//
//        //return to movies on no
//        builder.setNegativeButton(R.string.no_btn_label, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        return builder.create();
//    }
//}
