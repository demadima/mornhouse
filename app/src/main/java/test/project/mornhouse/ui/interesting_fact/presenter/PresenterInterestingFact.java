package test.project.mornhouse.ui.interesting_fact.presenter;

import java.io.IOException;
import java.util.List;

import test.project.mornhouse.ui.interesting_fact.contract.ContractInterestingFact;
import test.project.mornhouse.ui.interesting_fact.repository.network.NetworkInterestingFact;
import test.project.mornhouse.ui.interesting_fact.repository.room_db.db.DBInterestingFact;
import test.project.mornhouse.ui.interesting_fact.repository.room_db.item.Fact;

public class PresenterInterestingFact implements ContractInterestingFact.Presenter, ContractInterestingFact.RepositoryNetwork.OnFinishedListener, ContractInterestingFact.RepositoryRoomDB.OnFinishedListener{
    private ContractInterestingFact.View mView;
    private ContractInterestingFact.RepositoryNetwork mRepositoryNetwork;
    private ContractInterestingFact.RepositoryRoomDB mRepositoryRoomDB;

    public PresenterInterestingFact(ContractInterestingFact.View mView) {
        this.mView = mView;
        this.mRepositoryNetwork = new NetworkInterestingFact();
        this.mRepositoryRoomDB = new DBInterestingFact();
    }


    @Override
    public void onBFactWasClicked(String s) {
        mRepositoryNetwork.loadInfoFromUserNumber(this,s);
    }

    @Override
    public void onBRandomFactWasClicked() {
        mRepositoryNetwork.loadInfoFromRandomNumber(this);
    }

    @Override
    public void getFactsFromDB() {
        mRepositoryRoomDB.getAll(this);
    }

    @Override
    public void onDestroy() {
        mRepositoryRoomDB.onDestroy();
    }

    @Override
    public void onFinished(String body) {
        mRepositoryRoomDB.insert(body);

        mRepositoryRoomDB.getAll(this);
    }

    @Override
    public void onFailure(IOException t) {
        mView.onResponseFailure(t);
    }

    @Override
    public void onGetAllFinished(List<Fact> facts) {
        mView.setDataToRecyclerview(facts);
    }
}
