package test.project.mornhouse.ui.interesting_fact.contract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import test.project.mornhouse.ui.interesting_fact.repository.room_db.item.Fact;

public interface ContractInterestingFact {
    interface View {
        void setDataToRecyclerview(List<Fact> facts);
        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onBFactWasClicked(String number);
        void onBRandomFactWasClicked();
        void getFactsFromDB();
        void onDestroy();
    }

    interface RepositoryNetwork {
        interface OnFinishedListener {
            void onFinished(String body);
            void onFailure(IOException t);
        }

       void loadInfoFromUserNumber(OnFinishedListener onFinishedListener, String number);
        void loadInfoFromRandomNumber(OnFinishedListener onFinishedListener);
    }

    interface RepositoryRoomDB{
        interface OnFinishedListener {
            void onGetAllFinished(List<Fact> facts);
        }

        void insert(String body);
        void getAll(OnFinishedListener onFinishedListener);
        void onDestroy();
    }
}
