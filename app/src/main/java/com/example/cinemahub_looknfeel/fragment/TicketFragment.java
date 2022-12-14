//package com.example.cinemahub_looknfeel.fragment;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.cinemahub_looknfeel.R;
//import com.example.movieticketapp.R;
//import com.example.movieticketapp.adapter.TicketAdapter;
//import com.example.movieticketapp.model.Ticket;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.example.movieticketapp.login.Login.username;
//
//public class TicketFragment extends Fragment {
//
//    private static final String TAG = "Ticket";
//    private TicketAdapter mAdapter;
//    private RecyclerView recyclerView;
//    private FirebaseFirestore db;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_ticket, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        List<Ticket> items = new ArrayList<>();
//
//        //get the database
//        db = FirebaseFirestore.getInstance();
//
//        //read the tickets from the database where username is equal to user's username
//        db.collection("tickets").whereEqualTo("username", username).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        if (document.exists()) {
//                            //add the tickets to the list
//                            Ticket ticket = document.toObject(Ticket.class);
//                            items.add(ticket);
//
//                            //notify changes
//                            mAdapter.notifyDataSetChanged();
//                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                        }
//                        else {
//                            Log.d(TAG, "No such document");
//                        }
//                    }
//                }
//                else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//
//        mAdapter = new TicketAdapter(getActivity(), items);
//
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewTickets);
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(mAdapter);
//    }
//}