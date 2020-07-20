package com.example.umbrellacorp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragSupport extends Fragment
{
    View view;
    EditText name,email,phoneNumber,message;
    Button btn;
    FirebaseDatabase db;
    DatabaseReference ref;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_support,container,false);
        btn = view.findViewById(R.id.submit);
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Support");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = getActivity().findViewById(R.id.name);
                email = getActivity().findViewById(R.id.email);
                phoneNumber = getActivity().findViewById(R.id.phoneNumber);
                message = getActivity().findViewById(R.id.message);
                    String userName = name.getText().toString();
                    String userEmail = email.getText().toString();
                    String userPhoneNumber = phoneNumber.getText().toString();
                    String userMessage = message.getText().toString();
                    if(userName.isEmpty() && userEmail.isEmpty() && userPhoneNumber.isEmpty()&& userMessage.isEmpty())
                    {
                        Toast.makeText(getActivity(), "Enter all values",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                {
                    SupportObj spt = new SupportObj(userName,userEmail,userPhoneNumber,userMessage);
                    ref.push().setValue(spt);
                    Toast.makeText(getActivity(),"Message Sent", Toast.LENGTH_SHORT).show();
                    name.setText(null);
                    email.setText(null);
                    phoneNumber.setText(null);
                    message.setText(null);
                }

            }
        });
        return view;
    }
}
