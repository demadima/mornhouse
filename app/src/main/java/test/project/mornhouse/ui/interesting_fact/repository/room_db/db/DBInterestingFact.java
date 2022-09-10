package test.project.mornhouse.ui.interesting_fact.repository.room_db.db;


import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;
import test.project.mornhouse.ui.interesting_fact.contract.ContractInterestingFact;
import test.project.mornhouse.ui.interesting_fact.repository.room_db.item.Fact;

public class DBInterestingFact implements ContractInterestingFact.RepositoryRoomDB {
    private Disposable getAllDisposable = Disposables.empty();

    private RoomDB roomDB;
    private Gson g = new Gson();
    private List<Fact> facts;

    @Override
    public void insert(String body) {
        Fact fact = g.fromJson(body, Fact.class);

        roomDB.getInstance().factDao().insert(fact);
    }

    @Override
    public void getAll( OnFinishedListener onFinishedListener) {
        getAllDisposable = Completable.fromAction(() -> {
            facts = roomDB.getInstance().factDao().getAll();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                            onFinishedListener.onGetAllFinished(facts);
                        },
                        error -> {
                        }
                );

    }

    @Override
    public void onDestroy() {
        if(getAllDisposable != null && !getAllDisposable.isDisposed()) getAllDisposable.dispose();
    }

}
