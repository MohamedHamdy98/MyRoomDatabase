package com.momoandroid.myroomdatabase.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.momoandroid.myroomdatabase.R;
import com.momoandroid.myroomdatabase.adapter.AdapterRoomDatabase;
import com.momoandroid.myroomdatabase.data.UserDatabase;
import com.momoandroid.myroomdatabase.databinding.ActivityMainBinding;
import com.momoandroid.myroomdatabase.models.User;
import com.momoandroid.myroomdatabase.viewModel.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserViewModel userViewModel;
    private AdapterRoomDatabase adapterRoomDatabase;
    private UserDatabase userDatabase;
    private User user;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userDatabase = UserDatabase.getInstance(this);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        user = new User();
        userViewModel.mutableLiveData.observe(this,
                new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapterRoomDatabase.setList(users);
            }
        });
        adapterRoomDatabase = new AdapterRoomDatabase();
        binding.recyclerViewRoom.setHasFixedSize(true);
        binding.recyclerViewRoom.setAdapter(adapterRoomDatabase);
        binding.setUser(user);
        handler = new Handler(binding.editTextFirstName, binding.editTextLastName);
        binding.setHandler(handler);
    }

    public class Handler {
        public EditText editTextFirstName, editTextLastName;

        public Handler(EditText editTextFirstName, EditText editTextLastName) {
            this.editTextFirstName = editTextFirstName;
            this.editTextLastName = editTextLastName;
        }

        public void saveData(View view) {
            String firstName = editTextFirstName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userViewModel.insertData(user);
        }

        public void getData(View view) {
            userViewModel.getData(user);
        }

        public void updateData(View view){
            String firstName = editTextFirstName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userViewModel.updateData(user);
        }

        public void deleteData(View view) {
            userViewModel.delteData();
        }
    }
}