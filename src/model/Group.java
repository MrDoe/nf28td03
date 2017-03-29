package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Group {
	private StringProperty name;
	
	public Group(){
		this.name = new SimpleStringProperty();
	}

	public StringProperty nameProperty(){
		return name;
	}
	
	public static List<Group> getGroups(){
		ArrayList<Group> groupList = new ArrayList<>();
		Group group;
		group = new Group();
		group.nameProperty().setValue("Groupe 1");		
		groupList.add(group);
		group = new Group();
		group.nameProperty().setValue("Groupe 2");		
		groupList.add(group);
		group = new Group();
		group.nameProperty().setValue("Groupe 3");		
		groupList.add(group);
		group = new Group();
		group.nameProperty().setValue("Groupe 4");		
		groupList.add(group);

		return groupList;
	}
	
	public String toString(){
		return name.getValue();
	}
}
