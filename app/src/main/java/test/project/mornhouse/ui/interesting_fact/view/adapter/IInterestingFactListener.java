package test.project.mornhouse.ui.interesting_fact.view.adapter;


import test.project.mornhouse.ui.interesting_fact.repository.room_db.item.Fact;

public interface IInterestingFactListener {
    void itemSelected(Fact item);
}
