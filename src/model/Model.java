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
	public void addGroup(Group g){
		if(g==null)
			throw new NullPointerException();
		this.groups.add(g);
	}

	public void removeGroup(Group g){
		if(g==null)
			throw new NullPointerException();
		this.groups.remove(g);
	}


}
