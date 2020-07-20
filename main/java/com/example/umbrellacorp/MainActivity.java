package com.example.umbrellacorp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bn = findViewById(R.id.bottomMenu);
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task)
            {
                if(task.isSuccessful())
                {
                    String token  =  task.getResult().getToken();
                }
            }
        });



        bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String itemName = menuItem.getTitle().toString();

                if(menuItem.getItemId()==R.id.page_home)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentArea, new FragHome()).commit();
                }
                if(menuItem.getItemId()==R.id.page_company_history)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentArea,new FragHistory()).commit();
                }
                if(menuItem.getItemId()==R.id.page_support)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentArea,new FragSupport()).commit();
                }
                if (menuItem.getItemId()==R.id.page_contact)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentArea,new FragContact()).commit();
                }
                if(menuItem.getItemId()==R.id.page_vision)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentArea,new FragVision()).commit();
                }
                getSupportActionBar().setTitle(itemName);
                return true;
            }
        });
        bn.setSelected(true);
        bn.setSelectedItemId(R.id.page_home);
    }
}
