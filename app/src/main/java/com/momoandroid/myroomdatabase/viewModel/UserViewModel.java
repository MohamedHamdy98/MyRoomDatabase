package com.momoandroid.myroomdatabase.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.momoandroid.myroomdatabase.data.UserDatabase;
import com.momoandroid.myroomdatabase.models.User;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends ViewModel {
    private static final String TAG = "UserViewModel";
    public MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();

    public void insertData(User user) {
        UserDatabase.instance.userDao().insertData(user)
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"insertError:"+e);
                    }
                });
    }

    public MutableLiveData<List<User>> getData(User user) {
        UserDatabase.instance.userDao().getAllDataUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<User> users) {
                        mutableLiveData.setValue(users);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"getError:"+e);
                    }
                });
        return mutableLiveData;
    }

    public void updateData(User user){
        UserDatabase.instance.userDao().updateData(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"updateError:"+e);
                    }
                });
    }

    public void delteData(){
        UserDatabase.instance.userDao().deleteData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }


            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
