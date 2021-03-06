package model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Group implements Externalizable {
	private StringProperty name;
	private ObservableList<Contact> contacts;
	public final static String DEFAULT_GROUP_NAME = "Nouveau Groupe";
	public Group(){
		this.name = new SimpleStringProperty();
		this.contacts = FXCollections.observableArrayList();
	}

	public ObservableList<Contact> contactsProperty(){
		return this.contacts;
	}
	public StringProperty nameProperty(){
		return name;
	}

	public void addContact(Contact c){
		if(c == null)
			throw new NullPointerException();
		this.contacts.add(c);
	}

	public void removeContact(Contact c){
		if(c == null)
			throw new NullPointerException();
		this.contacts.remove(c);
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
	public Object getId(){
		return name.getValueSafe();
	}


	@Override
	public boolean equals(Object group){
		if(group instanceof Group)
		{
			if(((Group) group).getId().equals(this.getId()))
				return true;
		}
		return false;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name.set(in.readUTF());
		int nbContacts = in.readInt();
		for(int i = 0; i < nbContacts; i++){
			Contact contact = (Contact) in.readObject();
			contact.groupProperty().set(this);
			addContact(contact);
		}
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name.get());
		out.writeInt(contacts.size());
		for (Contact contact : contacts) {
			out.writeObject(contact);
		}
	}

}
