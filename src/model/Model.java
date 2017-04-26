package model;


import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	private ObservableList<Group> groups;

	public Model() {
		this.groups = FXCollections.observableArrayList();
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
	
	public String toString(){
		return "Liste de contacts";
	}

	public void debug() {
		for (Group group : groups) {
			System.out.println("Group Name : " + group.nameProperty().getValueSafe());
			for (Contact contact : group.contactsProperty()) {
				System.out.println("Contact :");
				contact.debug();
			}
		}
		
	}


}
