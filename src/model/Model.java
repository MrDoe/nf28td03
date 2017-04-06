package model;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

public class Model {
	private ObservableList<Group> groups;

	public Model() {
		this.groups = new SimpleListProperty<Group>();
	}

	public ObservableList<Group> groupsProperty(){
		return this.groups;
	}


}
