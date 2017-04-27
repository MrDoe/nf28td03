package model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Model implements Externalizable{
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

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		int nbGroups = in.readInt();
		for(int i = 0; i < nbGroups; i++){
			addGroup((Group) in.readObject());
		}
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(groupsProperty().size());
	    for(Group group : groups){
	    	out.writeObject(group);
	    }
		
	}


}
