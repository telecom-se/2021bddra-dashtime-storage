package tse.projects.utils;

import tse.projects.models.DatabaseModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseTable<T extends DatabaseModel> {
    public LinkedList<T> items;

    public DatabaseTable() {
        this.items = new LinkedList<>();
    }

    public List<DatabaseModel> where(List<Condition> conditions) throws NoSuchFieldException, NumberFormatException, IllegalAccessException {
        List<DatabaseModel> items = new ArrayList<>();

        for (int i = 0; i < this.items.size(); i++) {
            T item = this.items.get(i);

            if (item.passConditions(conditions)) {
                items.add(item);
            }
        }
        return items;
    }

    public void addItem(T item) {
        this.items.add(item);
    }

    public void addItems(List<T> items) {
        this.items.addAll(items);
    }

}
